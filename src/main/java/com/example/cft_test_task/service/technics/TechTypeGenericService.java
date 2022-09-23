package com.example.cft_test_task.service.technics;

import com.example.cft_test_task.model.entities.TechnicsEntity;
import com.example.cft_test_task.model.entities.technics.*;
import com.example.cft_test_task.model.enums.tech.TechnicFields;
import com.example.cft_test_task.model.enums.tech.TechnicTypes;
import com.example.cft_test_task.model.enums.tech.details.desktop.PCFormFactor;
import com.example.cft_test_task.model.rest.request.TechnicsRequest;
import com.example.cft_test_task.model.rest.response.TechnicsResponse;
import com.example.cft_test_task.repos.TechTypeRepo;
import com.example.cft_test_task.repos.TechnicsRepo;
import com.example.cft_test_task.service.technics.AnyTechService;
import com.fasterxml.classmate.GenericType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

@Service
public class TechTypeGenericService<TypeEntity extends TechEntityBase> extends AnyTechService {
    final TechTypeRepo<TypeEntity> typeRepo;

    @Autowired
    public TechTypeGenericService(TechTypeRepo<TypeEntity> typeRepo, TechnicsRepo technicsRepo){
        super(technicsRepo);
        this.typeRepo = typeRepo;
    }

    @Override
    public boolean add(TechnicsRequest technicsRequest) {
        try {
            TypeEntity typeEntity = new_TypeEntity();

            if(typeEntity == null)
                return false;

            TechnicsEntity technicsEntity = new TechnicsEntity();
            initTechEntity(technicsEntity, technicsRequest);
            typeEntity.technicsEntity = technicsEntity;
            setSpecificParam(typeEntity, technicsRequest.specificParam);
            typeRepo.save(typeEntity);
            typeRepo.flush();
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    private TypeEntity new_TypeEntity() {
        ParameterizedType superClass = (ParameterizedType) getClass().getGenericSuperclass();
        Class<TypeEntity> type = (Class<TypeEntity>) superClass.getActualTypeArguments()[0];

        try {
            return (TypeEntity) type.getDeclaredConstructor().newInstance();
            //Do whatever with t
        } catch (Exception e) {
            // Oops, no default constructor
            return null;
        }
    }

    @Override
    public boolean edit(Long id, String field, String value) {
        TypeEntity typeEntity = typeRepo.findFirstByTechnicsEntity(getTechnicEntityById(id));

        try {
            TechnicFields technicField = TechnicFields.valueOf(field.toUpperCase());

            return switch (technicField) {
                case FORM_FACTOR -> editFormFactor(typeEntity, value);
                case LAPTOP_DIAGONAL -> editLaptopDiagonal(typeEntity, value);
                case DISPLAY_DIAGONAL -> editDisplayDiagonal(typeEntity, value);
                case VOLUME -> editVolume(typeEntity, value);
                default -> editTechEntity(typeEntity.technicsEntity, technicField, value);
            };
        } catch (IllegalArgumentException e){
            return false;
        }
    }

    @Override
    public boolean delete(Long id) {
        TypeEntity typeEntity = typeRepo.findFirstByTechnicsEntity(getTechnicEntityById(id));
        typeRepo.delete(typeEntity);
        technicsRepo.deleteFirstBySerialNumber(id);
        return true;
    }

    @Override
    public TechnicsResponse getById(Long id) {
        TypeEntity typeEntity = typeRepo.findFirstByTechnicsEntity(technicsRepo.findFirstBySerialNumber(id));
        return castToTypeResponse(typeEntity);
    }

    @Override
    public List<TechnicsResponse> getAll() {
        List<TechnicsResponse> technicsResponseList = new ArrayList<>();
        List<TypeEntity> desktopEntityList = typeRepo.findAll();

        desktopEntityList.forEach(typeEntity -> technicsResponseList.add(castToTypeResponse(typeEntity)));
        return technicsResponseList;
    }
}
