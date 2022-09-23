package com.example.cft_test_task.service;

import com.example.cft_test_task.model.entities.technics.DesktopEntity;
import com.example.cft_test_task.model.entities.technics.DisplayEntity;
import com.example.cft_test_task.model.entities.technics.LaptopEntity;
import com.example.cft_test_task.model.entities.technics.StorageEntity;
import com.example.cft_test_task.model.enums.tech.TechnicTypes;
import com.example.cft_test_task.model.rest.request.TechnicsRequest;
import com.example.cft_test_task.model.rest.response.TechnicsResponse;
import com.example.cft_test_task.repos.TechTypeRepo;
import com.example.cft_test_task.repos.TechnicsRepo;
import com.example.cft_test_task.service.technics.TechTypeGenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechnicsService {

    private final TechTypeGenericService<DesktopEntity> desktopService;
    private final TechTypeGenericService<DisplayEntity> displayService;
    private final TechTypeGenericService<LaptopEntity> laptopService;
    private final TechTypeGenericService<StorageEntity> storageService;
    private final TechnicsRepo technicsRepo;

    @Autowired
    public TechnicsService(TechTypeGenericService<DesktopEntity> desktopService,
                           TechTypeGenericService<DisplayEntity> displayService,
                           TechTypeGenericService<LaptopEntity> laptopService,
                           TechTypeGenericService<StorageEntity> storageService,
                           TechnicsRepo technicsRepo){
        this.desktopService = desktopService;
        this.displayService = displayService;
        this.laptopService = laptopService;
        this.storageService = storageService;
        this.technicsRepo = technicsRepo;
    }
    public boolean add(TechnicsRequest technicsRequest) {
        switch (TechnicTypes.valueOf(technicsRequest.type.toUpperCase())){
            case DESKTOP: return desktopService.add(technicsRequest);
            case DISPLAY: return displayService.add(technicsRequest);
            case LAPTOP: return laptopService.add(technicsRequest);
            case STORAGE: return storageService.add(technicsRequest);
            default: return false;
        }
    }

    public boolean edit(Long id, String field, String variable) {
        TechnicTypes type = technicsRepo.findTypeBySerialNumber(id);

        switch (type){
            case DESKTOP: return desktopService.edit(id, field, variable);
            case DISPLAY: return displayService.edit(id, field, variable);
            case LAPTOP: return laptopService.edit(id, field, variable);
            case STORAGE: return storageService.edit(id, field, variable);
            default: return false;
        }
    }

    public boolean delete(Long id) {
        TechnicTypes type = technicsRepo.findTypeBySerialNumber(id);

        switch (type){
            case DESKTOP: return desktopService.delete(id);
            case DISPLAY: return displayService.delete(id);
            case LAPTOP: return laptopService.delete(id);
            case STORAGE: return storageService.delete(id);
            default: return false;
        }
    }

    public TechnicsResponse getById(Long id) {
        TechnicTypes type = technicsRepo.findTypeBySerialNumber(id);

        switch (type){
            case DESKTOP: return desktopService.getById(id);
            case DISPLAY: return displayService.getById(id);
            case LAPTOP: return laptopService.getById(id);
            case STORAGE: return storageService.getById(id);
            default: return null;
        }
    }

    public List<TechnicsResponse> getByType(String type) {
        switch (TechnicTypes.valueOf(type.toUpperCase())){
            case DESKTOP: return desktopService.getAll();
            case DISPLAY: return displayService.getAll();
            case LAPTOP: return laptopService.getAll();
            case STORAGE: return storageService.getAll();
            default: return null;
        }
    }
}
