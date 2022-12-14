package com.example.cft_test_task.service.technics;

import com.example.cft_test_task.model.entities.TechnicsEntity;
import com.example.cft_test_task.model.entities.technics.*;
import com.example.cft_test_task.model.enums.tech.TechnicFields;
import com.example.cft_test_task.model.enums.tech.TechnicTypes;
import com.example.cft_test_task.model.rest.request.TechnicsRequest;
import com.example.cft_test_task.model.rest.response.TechnicsResponse;
import com.example.cft_test_task.repos.TechTypeRepo;
import com.example.cft_test_task.repos.TechnicsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Service
public class ServiceByTechType<TypeEntity extends TechEntityBase> extends AnyTechService {
    final TechTypeRepo<TypeEntity> typeRepo;

    @Autowired
    public ServiceByTechType(TechTypeRepo<TypeEntity> typeRepo, TechnicsRepo technicsRepo){
        super(technicsRepo);
        this.typeRepo = typeRepo;
    }

    public boolean add(TechnicsRequest technicsRequest, TypeEntity typeEntity) {
        try {
            if(typeEntity == null)
                return false;

            TechnicsEntity technicsEntity = new TechnicsEntity();
            initTechEntity(technicsEntity, technicsRequest);
            typeEntity.setTechnicsEntity(technicsEntity);
            setSpecificParam(typeEntity, technicsRequest.specificParam);
            technicsRepo.save(technicsEntity);
            technicsRepo.flush();
            typeRepo.save(typeEntity);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public boolean edit(Long id, String field, String value) {
        TypeEntity typeEntity = typeRepo.findFirstByTechnicsEntity(getTechnicEntityById(id));

        try {
            TechnicFields technicField = TechnicFields.valueOf(field.toUpperCase());

            return switch (technicField) {
                case FORM_FACTOR -> editFormFactor(typeEntity, value, (TechTypeRepo<DesktopEntity>) typeRepo);
                case LAPTOP_DIAGONAL -> editLaptopDiagonal(typeEntity, value, (TechTypeRepo<LaptopEntity>) typeRepo);
                case DISPLAY_DIAGONAL -> editDisplayDiagonal(typeEntity, value, (TechTypeRepo<DisplayEntity>) typeRepo);
                case VOLUME -> editVolume(typeEntity, value, (TechTypeRepo<StorageEntity>) typeRepo);
                default -> editTechEntity(typeEntity.getTechnicsEntity(), technicField, value);
            };
        } catch (IllegalArgumentException | NullPointerException e){
            return false;
        }
    }

    @Override
    public boolean delete(Long id) {
        try {
            TypeEntity typeEntity = typeRepo.findFirstByTechnicsEntity(getTechnicEntityById(id));
            typeRepo.delete(typeEntity);
            technicsRepo.deleteById(id);

            return true;
        } catch (NullPointerException e){
            return false;
        }
    }

    @Override
    public TechnicsResponse getById(Long id) {
        try {
            TypeEntity typeEntity = typeRepo.findFirstByTechnicsEntity(technicsRepo.findFirstBySerialNumber(id));
            return castToTypeResponse(typeEntity);
        } catch (NullPointerException e){
            return null;
        }
    }

    @Override
    public List<TechnicsResponse> getAll(TechnicTypes technicType) {
        try {
            List<TechnicsResponse> technicsResponseList = new ArrayList<>();
            List<TypeEntity> desktopEntityList = typeRepo.findAll(technicType);

            desktopEntityList.forEach(typeEntity -> technicsResponseList.add(castToTypeResponse(typeEntity)));
            return technicsResponseList;
        } catch (NullPointerException e){
            return null;
        }
    }
}
