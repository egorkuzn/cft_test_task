package com.example.cft_test_task.service;

import com.example.cft_test_task.model.enums.tech.TechnicTypes;
import com.example.cft_test_task.model.rest.request.TechnicsRequest;
import com.example.cft_test_task.model.rest.response.TechnicsResponse;
import com.example.cft_test_task.repos.TechnicsRepo;
import com.example.cft_test_task.service.technics.implementations.DesktopService;
import com.example.cft_test_task.service.technics.implementations.DisplayService;
import com.example.cft_test_task.service.technics.implementations.LaptopService;
import com.example.cft_test_task.service.technics.implementations.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechnicsService {

    private final DesktopService desktopService;
    private final DisplayService displayService;
    private final LaptopService laptopService;
    private final StorageService storageService;
    private final TechnicsRepo technicsRepo;

    @Autowired
    public TechnicsService(DesktopService desktopService,
                           DisplayService displayService,
                           LaptopService laptopService,
                           StorageService storageService,
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
