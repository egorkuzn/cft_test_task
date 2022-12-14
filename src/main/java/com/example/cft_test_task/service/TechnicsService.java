package com.example.cft_test_task.service;

import com.example.cft_test_task.model.entities.technics.DesktopEntity;
import com.example.cft_test_task.model.entities.technics.DisplayEntity;
import com.example.cft_test_task.model.entities.technics.LaptopEntity;
import com.example.cft_test_task.model.entities.technics.StorageEntity;
import com.example.cft_test_task.model.enums.tech.TechnicTypes;
import com.example.cft_test_task.model.rest.request.TechnicsRequest;
import com.example.cft_test_task.model.rest.response.TechnicsResponse;
import com.example.cft_test_task.repos.TechnicsRepo;
import com.example.cft_test_task.service.technics.ServiceByTechType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechnicsService {

    private final ServiceByTechType<DesktopEntity> desktopService;
    private final ServiceByTechType<DisplayEntity> displayService;
    private final ServiceByTechType<LaptopEntity> laptopService;
    private final ServiceByTechType<StorageEntity> storageService;
    private final TechnicsRepo technicsRepo;

    @Autowired
    public TechnicsService(ServiceByTechType<DesktopEntity> desktopService,
                           ServiceByTechType<DisplayEntity> displayService,
                           ServiceByTechType<LaptopEntity> laptopService,
                           ServiceByTechType<StorageEntity> storageService,
                           TechnicsRepo technicsRepo){
        this.desktopService = desktopService;
        this.displayService = displayService;
        this.laptopService = laptopService;
        this.storageService = storageService;
        this.technicsRepo = technicsRepo;
    }
    public boolean add(TechnicsRequest technicsRequest) {
        try {
            switch (TechnicTypes.valueOf(technicsRequest.type.toUpperCase())){
                case DESKTOP: return desktopService.add(technicsRequest, new DesktopEntity());
                case DISPLAY: return displayService.add(technicsRequest, new DisplayEntity());
                case LAPTOP: return laptopService.add(technicsRequest, new LaptopEntity());
                case STORAGE: return storageService.add(technicsRequest, new StorageEntity());
                default: return false;
            }
        } catch (IllegalArgumentException e){
            return false;
        }
    }

    public boolean edit(Long id, String field, String variable) {
        try {
            TechnicTypes type = technicsRepo.findFirstBySerialNumber(id).technicType;

            switch (type) {
                case DESKTOP:
                    return desktopService.edit(id, field, variable);
                case DISPLAY:
                    return displayService.edit(id, field, variable);
                case LAPTOP:
                    return laptopService.edit(id, field, variable);
                case STORAGE:
                    return storageService.edit(id, field, variable);
                default:
                    return false;
            }
        } catch(NullPointerException e){
            return false;
        }
    }

    public boolean delete(Long id) {
        try{
            TechnicTypes type = technicsRepo.findFirstBySerialNumber(id).technicType;

            switch (type){
                case DESKTOP: return desktopService.delete(id);
                case DISPLAY: return displayService.delete(id);
                case LAPTOP: return laptopService.delete(id);
                case STORAGE: return storageService.delete(id);
                default: return false;
            }
        } catch (NullPointerException e){
            return false;
        }
    }

    public TechnicsResponse getById(Long id) {
        try {
            TechnicTypes type = technicsRepo.findFirstBySerialNumber(id).technicType;

            switch (type){
                case DESKTOP: return desktopService.getById(id);
                case DISPLAY: return displayService.getById(id);
                case LAPTOP: return laptopService.getById(id);
                case STORAGE: return storageService.getById(id);
                default: return null;
            }
        } catch (NullPointerException e){
          return null;
        }
    }

    public List<TechnicsResponse> getByType(String type) {
        try {
            switch (TechnicTypes.valueOf(type.toUpperCase())){
                case DESKTOP: return desktopService.getAll(TechnicTypes.DESKTOP);
                case DISPLAY: return displayService.getAll(TechnicTypes.DISPLAY);
                case LAPTOP: return laptopService.getAll(TechnicTypes.LAPTOP);
                case STORAGE: return storageService.getAll(TechnicTypes.STORAGE);
                default: return null;
            }
        } catch (IllegalArgumentException e){
            return null;
        }
    }
}
