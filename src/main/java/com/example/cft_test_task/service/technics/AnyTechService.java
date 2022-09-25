package com.example.cft_test_task.service.technics;

import com.example.cft_test_task.model.entities.TechnicsEntity;
import com.example.cft_test_task.model.entities.technics.*;
import com.example.cft_test_task.model.enums.tech.TechnicFields;
import com.example.cft_test_task.model.enums.tech.TechnicTypes;
import com.example.cft_test_task.model.enums.tech.details.desktop.PCFormFactor;
import com.example.cft_test_task.model.enums.tech.details.laptop.LaptopDiagonal;
import com.example.cft_test_task.model.rest.request.TechnicsRequest;
import com.example.cft_test_task.model.rest.response.TechnicsResponse;
import com.example.cft_test_task.repos.TechnicsRepo;

import java.math.BigDecimal;
import java.util.List;

public abstract class AnyTechService {
    protected final TechnicsRepo technicsRepo;

    public AnyTechService(TechnicsRepo technicsRepo){
        this.technicsRepo = technicsRepo;
    }
    protected void initTechEntity(TechnicsEntity technicsEntity, TechnicsRequest technicsRequest){
        technicsEntity.countOfElems = technicsRequest.countOfElements;
        technicsEntity.price = technicsRequest.price;
        technicsEntity.producer = technicsRequest.producer;
        technicsRepo.save(technicsEntity);
        technicsRepo.flush();
    }

    protected static boolean editTechEntity(TechnicsEntity technicsEntity, TechnicFields field, String value) throws IllegalArgumentException{
        switch (field){
            case PRODUCER -> technicsEntity.producer = value;
            case PRICE -> technicsEntity.price = new BigDecimal(value);
            case COUNT -> technicsEntity.countOfElems = Integer.parseInt(value);
            default -> {return false;}
        }

        return true;
    }

    protected static void castToTechnicsResponse(TechnicsResponse technicsResponse, TechnicsEntity technicsEntity){
        technicsResponse.countOfElements = technicsEntity.countOfElems;
        technicsResponse.price = technicsEntity.price;
        technicsResponse.producer = technicsEntity.producer;
        technicsResponse.serialNumber = technicsEntity.serialNumber;
    }

    protected static void setSpecificParam(DesktopEntity desktopEntity, String specificParam, TechnicTypes technicType) {
        desktopEntity.setFormFactor(PCFormFactor.valueOf(specificParam.toUpperCase()));

        TechnicsEntity technicsEntity = desktopEntity.getTechnicsEntity();
        technicsEntity.technicType = TechnicTypes.DESKTOP;
        technicType = TechnicTypes.DESKTOP;

        desktopEntity.setTechnicsEntity(technicsEntity);
    }

    protected static void setSpecificParam(DisplayEntity displayEntity, String specificParam, TechnicTypes technicType) {
        displayEntity.setDiagonal(Float.parseFloat(specificParam));

        TechnicsEntity technicsEntity = displayEntity.getTechnicsEntity();
        technicsEntity.technicType = TechnicTypes.DISPLAY;
        technicType = TechnicTypes.DISPLAY;

        displayEntity.setTechnicsEntity(technicsEntity);
    }

    protected static void setSpecificParam(StorageEntity storageEntity, String specificParam, TechnicTypes technicType) {
        storageEntity.setVolume(Integer.parseInt(specificParam));

        TechnicsEntity technicsEntity = storageEntity.getTechnicsEntity();
        technicsEntity.technicType = TechnicTypes.STORAGE;
        technicType = TechnicTypes.STORAGE;

        storageEntity.setTechnicsEntity(technicsEntity);
    }

    protected static void setSpecificParam(LaptopEntity laptopEntity, String specificParam, TechnicTypes technicType) {
        laptopEntity.setDiagonal(LaptopDiagonal.valueOf(specificParam));

        TechnicsEntity technicsEntity = laptopEntity.getTechnicsEntity();
        technicsEntity.technicType = TechnicTypes.LAPTOP;
        technicType = TechnicTypes.LAPTOP;

        laptopEntity.setTechnicsEntity(technicsEntity);
    }

    protected static void setSpecificParam(TechEntityBase laptopEntity, String specificParam, TechnicTypes technicType) {}

    protected static boolean editFormFactor(TechEntityBase desktopEntity, String value) throws IllegalArgumentException{
        if(desktopEntity instanceof DesktopEntity) {
            ((DesktopEntity)desktopEntity).setFormFactor(PCFormFactor.valueOf(value));
            return true;
        }

        return false;
    }

    protected static boolean editLaptopDiagonal(TechEntityBase laptopEntity, String value){
        if(laptopEntity instanceof LaptopEntity){
            ((LaptopEntity)laptopEntity).setDiagonal(LaptopDiagonal.valueOf(value));
            return true;
        }

        return false;
    }

    protected static boolean editDisplayDiagonal(TechEntityBase displayEntity, String value){
        if(displayEntity instanceof DisplayEntity){
            ((DisplayEntity)displayEntity).setDiagonal(Float.parseFloat(value));
            return true;
        }

        return false;
    }

    protected static boolean editVolume(TechEntityBase storageEntity, String value){
        if(storageEntity instanceof StorageEntity){
            ((StorageEntity)storageEntity).setVolume(Integer.parseInt(value));
        }

        return false;
    }

    protected static TechnicsResponse castToTypeResponse(DesktopEntity desktopEntity){
        TechnicsResponse technicsResponse = new TechnicsResponse();

        technicsResponse.specificFieldType = "form_factor";
        technicsResponse.specificFieldValue = desktopEntity.getFormFactor().name().toLowerCase();
        technicsResponse.technicType = TechnicTypes.DESKTOP.name().toLowerCase();

        castToTechnicsResponse(technicsResponse, desktopEntity.getTechnicsEntity());

        return technicsResponse;

    }

    protected static TechnicsResponse castToTypeResponse(DisplayEntity displayEntity){
        TechnicsResponse technicsResponse = new TechnicsResponse();

        technicsResponse.specificFieldType = "diagonal";
        technicsResponse.specificFieldValue = String.valueOf(displayEntity.getDiagonal());
        technicsResponse.technicType = TechnicTypes.DISPLAY.name().toLowerCase();

        castToTechnicsResponse(technicsResponse, displayEntity.getTechnicsEntity());

        return technicsResponse;
    }

    protected static TechnicsResponse castToTypeResponse(StorageEntity storageEntity){
        TechnicsResponse technicsResponse = new TechnicsResponse();

        technicsResponse.specificFieldType = "volume";
        technicsResponse.specificFieldValue = String.valueOf(storageEntity.getVolume());
        technicsResponse.technicType = TechnicTypes.STORAGE.name().toLowerCase();

        castToTechnicsResponse(technicsResponse, storageEntity.getTechnicsEntity());

        return technicsResponse;
    }

    protected static TechnicsResponse castToTypeResponse(LaptopEntity laptopEntity){
        TechnicsResponse technicsResponse = new TechnicsResponse();

        technicsResponse.specificFieldType = "laptop_diagonal";
        technicsResponse.specificFieldValue = laptopEntity.getDiagonal().name();
        technicsResponse.technicType = TechnicTypes.LAPTOP.name().toLowerCase();

        castToTechnicsResponse(technicsResponse, laptopEntity.getTechnicsEntity());

        return technicsResponse;
    }

    protected static TechnicsResponse castToTypeResponse(TechEntityBase entityBase){
        TechnicsResponse technicsResponse = new TechnicsResponse();
        castToTechnicsResponse(technicsResponse, entityBase.getTechnicsEntity());

        return technicsResponse;
    }


    protected TechnicsEntity getTechnicEntityById(Long id){
        return technicsRepo.findFirstBySerialNumber(id);
    }

    public abstract boolean edit(Long id, String field, String value);
    public abstract boolean delete(Long id);
    public abstract TechnicsResponse getById(Long id);
    public abstract List<TechnicsResponse> getAll();
}
