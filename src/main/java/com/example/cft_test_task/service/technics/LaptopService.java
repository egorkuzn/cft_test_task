package com.example.cft_test_task.service.technics;

import com.example.cft_test_task.model.entities.computers.TechnicsEntity;
import com.example.cft_test_task.model.entities.computers.technics.LaptopEntity;
import com.example.cft_test_task.model.enums.tech.TechnicFields;
import com.example.cft_test_task.model.enums.tech.details.laptop.LaptopDiagonal;
import com.example.cft_test_task.model.rest.request.TechnicsRequest;
import com.example.cft_test_task.model.rest.response.TechnicsResponse;
import com.example.cft_test_task.repos.LaptopsRepo;
import com.example.cft_test_task.repos.TechnicsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LaptopService extends AnyTechService{
    LaptopsRepo laptopsRepo;
    @Autowired
    public LaptopService(LaptopsRepo laptopsRepo, TechnicsRepo technicsRepo){
        super(technicsRepo);
        this.laptopsRepo = laptopsRepo;
    }
    @Override
    public boolean add(TechnicsRequest technicsRequest) {
        try {
            LaptopEntity laptopEntity = new LaptopEntity();
            TechnicsEntity technicsEntity = new TechnicsEntity();
            initTechEntity(technicsEntity, technicsRequest);
            laptopEntity.technicsEntity = technicsEntity;
            laptopEntity.diagonal = LaptopDiagonal.valueOf(technicsRequest.specificParam);
            laptopsRepo.save(laptopEntity);
            laptopsRepo.flush();
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }

    }
    @Override
    public boolean edit(Long id, String field, String value) {
        LaptopEntity laptopEntity = laptopsRepo.findFirstByTechnicsEntity(technicsRepo.findFirstBySerialNumber(id));

        try {
            TechnicFields technicFields = TechnicFields.valueOf(field.toUpperCase());

            return switch (technicFields) {
                case LAPTOP_DIAGONAL -> editDiagonal(laptopEntity, value);
                default -> editTechEntity(laptopEntity.technicsEntity, technicFields, value);
            };
        }
        catch (IllegalArgumentException e) {
            return false;
        }
    }

    private boolean editDiagonal(LaptopEntity laptopEntity, String variable) throws IllegalArgumentException{
        laptopEntity.diagonal = LaptopDiagonal.valueOf(variable);
        return true;
    }

    @Override
    public boolean delete(Long id) {
        LaptopEntity laptopEntity = laptopsRepo.findFirstByTechnicsEntity(technicsRepo.findFirstBySerialNumber(id));
        laptopsRepo.delete(laptopEntity);
        technicsRepo.deleteFirstBySerialNumber(id);
        return false;
    }
    @Override
    public TechnicsResponse getById(Long id) {
        LaptopEntity laptopEntity = laptopsRepo.findFirstByTechnicsEntity(technicsRepo.findFirstBySerialNumber(id));
        return castToTLaptopResponse(laptopEntity);
    }

    private TechnicsResponse castToTLaptopResponse(LaptopEntity laptopEntity) {
        TechnicsResponse technicsResponse = new TechnicsResponse();

        technicsResponse.specificFieldType = "laptop_diagonal";
        technicsResponse.specificFieldValue = laptopEntity.diagonal.name();

        castToTechnicsResponse(technicsResponse, laptopEntity.technicsEntity);

        return technicsResponse;
    }

    @Override
    public List<TechnicsResponse> getAll() {
        List<TechnicsResponse> technicsResponseList = new ArrayList<>();
        List<LaptopEntity> laptopEntityList = laptopsRepo.findAll();

        laptopEntityList.forEach(laptopEntity -> technicsResponseList.add(castToTLaptopResponse(laptopEntity)));
        return technicsResponseList;
    }
}
