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

    protected static void setSpecificParam(DesktopEntity desktopEntity, String specificParam) {
        desktopEntity.setFormFactor(PCFormFactor.valueOf(specificParam.toUpperCase()));

        TechnicsEntity technicsEntity = desktopEntity.getTechnicsEntity();
        technicsEntity.technicType = TechnicTypes.DESKTOP;

        desktopEntity.setTechnicsEntity(technicsEntity);
    }

    protected static void setSpecificParam(DisplayEntity displayEntity, String specificParam) {
        displayEntity.setDiagonal(Float.parseFloat(specificParam));

        TechnicsEntity technicsEntity = displayEntity.getTechnicsEntity();
        technicsEntity.technicType = TechnicTypes.DISPLAY;

        displayEntity.setTechnicsEntity(technicsEntity);
    }

    protected static void setSpecificParam(StorageEntity storageEntity, String specificParam) {
        storageEntity.setVolume(Integer.parseInt(specificParam));

        TechnicsEntity technicsEntity = storageEntity.getTechnicsEntity();
        technicsEntity.technicType = TechnicTypes.STORAGE;

        storageEntity.setTechnicsEntity(technicsEntity);
    }

    protected static void setSpecificParam(LaptopEntity techEntityBase, String specificParam) {
        techEntityBase.setDiagonal(LaptopDiagonal.valueOf(specificParam));

        TechnicsEntity technicsEntity = techEntityBase.getTechnicsEntity();
        technicsEntity.technicType = TechnicTypes.LAPTOP;

        techEntityBase.setTechnicsEntity(technicsEntity);
    }

    //TODO:
    //Я вообще не понимаю: почему все улетает блин в этот метод!!!
    //Почему не перегружаются функции setSpecificParam()???
    protected static void setSpecificParam(TechEntityBase techEntityBase, String specificParam) {
        if(techEntityBase instanceof DesktopEntity)
            setSpecificParam((DesktopEntity) techEntityBase, specificParam);
        else if(techEntityBase instanceof DisplayEntity)
            setSpecificParam((DisplayEntity) techEntityBase, specificParam);
        else if (techEntityBase instanceof StorageEntity)
            setSpecificParam((StorageEntity) techEntityBase, specificParam);
        else if (techEntityBase instanceof LaptopEntity)
            setSpecificParam((LaptopEntity) techEntityBase, specificParam);
    }

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
        if(entityBase instanceof DesktopEntity)
            return castToTypeResponse((DesktopEntity) entityBase);
        else if(entityBase instanceof DisplayEntity)
            return castToTypeResponse((DisplayEntity) entityBase);
        else if (entityBase instanceof StorageEntity)
            return castToTypeResponse((StorageEntity) entityBase);
        else if (entityBase instanceof LaptopEntity)
            return castToTypeResponse((LaptopEntity) entityBase);
        else {
            TechnicsResponse technicsResponse = new TechnicsResponse();
            castToTechnicsResponse(technicsResponse, entityBase.getTechnicsEntity());
            return technicsResponse;
        }
    }


    protected TechnicsEntity getTechnicEntityById(Long id){
        return technicsRepo.findFirstBySerialNumber(id);
    }

    public abstract boolean edit(Long id, String field, String value);
    public abstract boolean delete(Long id);
    public abstract TechnicsResponse getById(Long id);
    public abstract List<TechnicsResponse> getAll(TechnicTypes technicType);
}
