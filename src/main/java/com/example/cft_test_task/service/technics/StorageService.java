package com.example.cft_test_task.service.technics;

import com.example.cft_test_task.model.entities.computers.TechnicsEntity;
import com.example.cft_test_task.model.entities.computers.technics.StorageEntity;
import com.example.cft_test_task.model.enums.tech.TechnicFields;
import com.example.cft_test_task.model.rest.request.TechnicsRequest;
import com.example.cft_test_task.model.rest.response.TechnicsResponse;
import com.example.cft_test_task.repos.StoragesRepo;
import com.example.cft_test_task.repos.TechnicsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StorageService extends AnyTechService{
    final StoragesRepo storagesRepo;
    @Autowired
    public StorageService(StoragesRepo storagesRepo, TechnicsRepo technicsRepo){
        super(technicsRepo);
        this.storagesRepo = storagesRepo;
    }
    @Override
    public boolean add(TechnicsRequest technicsRequest) {
        try {
            StorageEntity storageEntity = new StorageEntity();
            TechnicsEntity technicsEntity = new TechnicsEntity();
            initTechEntity(technicsEntity, technicsRequest);
            storageEntity.technicsEntity = technicsEntity;
            storageEntity.volume = Integer.parseInt(technicsRequest.specificParam);
            storagesRepo.save(storageEntity);
            storagesRepo.flush();
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
    @Override
    public boolean edit(Long id, String field, String value) {
        StorageEntity storageEntity = storagesRepo.findFirstByTechnicsEntity(technicsRepo.findFirstBySerialNumber(id));

        try {
            TechnicFields technicFields = TechnicFields.valueOf(field.toUpperCase());

            return switch (technicFields) {
                case VOLUME -> editVolume(storageEntity, value);
                default -> editTechEntity(storageEntity.technicsEntity, technicFields, value);
            };
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    private boolean editVolume(StorageEntity storageEntity, String value) {
        storageEntity.volume = Integer.parseInt(value);
        return true;
    }

    @Override
    public boolean delete(Long id) {
        StorageEntity storageEntity = storagesRepo.findFirstByTechnicsEntity(technicsRepo.findFirstBySerialNumber(id));
        storagesRepo.delete(storageEntity);
        technicsRepo.deleteFirstBySerialNumber(id);
        return false;
    }
    @Override
    public TechnicsResponse getById(Long id) {
        StorageEntity storageEntity = storagesRepo.findFirstByTechnicsEntity(technicsRepo.findFirstBySerialNumber(id));
        return castToStorageResponse(storageEntity);
    }

    private TechnicsResponse castToStorageResponse(StorageEntity storageEntity) {
        TechnicsResponse technicsResponse = new TechnicsResponse();

        technicsResponse.specificFieldType = "volume";
        technicsResponse.specificFieldValue = String.valueOf(storageEntity.volume);

        castToTechnicsResponse(technicsResponse, storageEntity.technicsEntity);

        return technicsResponse;
    }

    @Override
    public List<TechnicsResponse> getAll() {
        List<TechnicsResponse> technicsResponseList = new ArrayList<>();
        List<StorageEntity> storageEntityList = new ArrayList<>();

        storageEntityList.forEach(storageEntity -> technicsResponseList.add(castToStorageResponse(storageEntity)));
        return technicsResponseList;
    }
}
