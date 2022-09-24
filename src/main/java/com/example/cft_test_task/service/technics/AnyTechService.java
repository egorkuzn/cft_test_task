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

    protected boolean editTechEntity(TechnicsEntity technicsEntity, TechnicFields field, String value) throws IllegalArgumentException{
        switch (field){
            case PRODUCER -> technicsEntity.producer = value;
            case PRICE -> technicsEntity.price = new BigDecimal(value);
            case COUNT -> technicsEntity.countOfElems = Integer.parseInt(value);
            default -> {return false;}
        }

        return true;
    }

    protected void castToTechnicsResponse(TechnicsResponse technicsResponse, TechnicsEntity technicsEntity){
        technicsResponse.countOfElements = technicsEntity.countOfElems;
        technicsResponse.price = technicsEntity.price;
        technicsResponse.producer = technicsEntity.producer;
        technicsResponse.serialNumber = technicsEntity.serialNumber;
    }

    protected static void setSpecificParam(DesktopEntity desktopEntity, String formFactor) throws IllegalArgumentException{
        desktopEntity.formFactor = PCFormFactor.valueOf(formFactor.toUpperCase());
    }

    protected static void setSpecificParam(DisplayEntity displayEntity, String diagonal) throws IllegalArgumentException{
        displayEntity.diagonal = Float.parseFloat(diagonal);
    }

    protected static void setSpecificParam(StorageEntity storageEntity, String volume) throws IllegalArgumentException{
        storageEntity.volume = Integer.parseInt(volume);
    }

    protected static void setSpecificParam(LaptopEntity laptopEntity, String diagonal) throws IllegalArgumentException{
        laptopEntity.diagonal = LaptopDiagonal.valueOf(diagonal);
    }

    protected static void setSpecificParam(TechEntityBase techEntityBase, String something){

    }

    protected boolean editFormFactor(TechEntityBase desktopEntity, String value) throws IllegalArgumentException{
        if(desktopEntity instanceof DesktopEntity) {
            ((DesktopEntity)desktopEntity).formFactor = PCFormFactor.valueOf(value);
            return true;
        }

        return false;
    }

    protected boolean editLaptopDiagonal(TechEntityBase laptopEntity, String value){
        if(laptopEntity instanceof LaptopEntity){
            ((LaptopEntity)laptopEntity).diagonal = LaptopDiagonal.valueOf(value);
            return true;
        }

        return false;
    }

    protected boolean editDisplayDiagonal(TechEntityBase displayEntity, String value){
        if(displayEntity instanceof DisplayEntity){
            ((DisplayEntity)displayEntity).diagonal = Float.parseFloat(value);
            return true;
        }

        return false;
    }

    protected boolean editVolume(TechEntityBase storageEntity, String value){
        if(storageEntity instanceof StorageEntity){
            ((StorageEntity)storageEntity).volume = Integer.parseInt(value);
        }

        return false;
    }

    protected TechnicsResponse castToTypeResponse(DesktopEntity desktopEntity){
        TechnicsResponse technicsResponse = new TechnicsResponse();

        technicsResponse.specificFieldType = "form_factor";
        technicsResponse.specificFieldValue = desktopEntity.formFactor.name().toLowerCase();
        technicsResponse.technicType = TechnicTypes.DESKTOP.name().toLowerCase();

        castToTechnicsResponse(technicsResponse, desktopEntity.technicsEntity);

        return technicsResponse;

    }

    protected TechnicsResponse castToTypeResponse(DisplayEntity displayEntity){
        TechnicsResponse technicsResponse = new TechnicsResponse();

        technicsResponse.specificFieldType = "diagonal";
        technicsResponse.specificFieldValue = String.valueOf(displayEntity.diagonal);
        technicsResponse.technicType = TechnicTypes.DISPLAY.name().toLowerCase();

        castToTechnicsResponse(technicsResponse, displayEntity.technicsEntity);

        return technicsResponse;
    }

    protected TechnicsResponse castToTypeResponse(StorageEntity storageEntity){
        TechnicsResponse technicsResponse = new TechnicsResponse();

        technicsResponse.specificFieldType = "volume";
        technicsResponse.specificFieldValue = String.valueOf(storageEntity.volume);
        technicsResponse.technicType = TechnicTypes.STORAGE.name().toLowerCase();

        castToTechnicsResponse(technicsResponse, storageEntity.technicsEntity);

        return technicsResponse;
    }

    protected TechnicsResponse castToTypeResponse(LaptopEntity laptopEntity){
        TechnicsResponse technicsResponse = new TechnicsResponse();

        technicsResponse.specificFieldType = "laptop_diagonal";
        technicsResponse.specificFieldValue = laptopEntity.diagonal.name();
        technicsResponse.technicType = TechnicTypes.LAPTOP.name().toLowerCase();

        castToTechnicsResponse(technicsResponse, laptopEntity.technicsEntity);

        return technicsResponse;
    }

    protected TechnicsResponse castToTypeResponse(TechEntityBase entityBase){
        TechnicsResponse technicsResponse = new TechnicsResponse();
        castToTechnicsResponse(technicsResponse, entityBase.technicsEntity);

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
