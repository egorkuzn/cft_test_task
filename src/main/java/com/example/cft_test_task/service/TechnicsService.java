package com.example.cft_test_task.service;

import com.example.cft_test_task.model.entities.computers.TechnicsEntity;
import com.example.cft_test_task.model.enums.tech.TechnicTypes;
import com.example.cft_test_task.model.rest.request.TechnicsRequest;
import com.example.cft_test_task.model.rest.response.TechnicsResponse;
import com.example.cft_test_task.repos.TechnicsRepo;
import com.example.cft_test_task.service.technics.DesktopService;
import com.example.cft_test_task.service.technics.DisplayService;
import com.example.cft_test_task.service.technics.LaptopService;
import com.example.cft_test_task.service.technics.StorageService;
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
    public Boolean add(TechnicsRequest technicsRequest) {
        switch (TechnicTypes.valueOf(technicsRequest.type.toUpperCase())){
            case DESKTOP -> desktopService.add(technicsRequest);
            case DISPLAY -> displayService.add(technicsRequest);
            case LAPTOP -> laptopService.add(technicsRequest);
            case STORAGE -> storageService.add(technicsRequest);
            default -> {return false;}
        }

        return true;
    }

    public Boolean edit(Long id, String field, String variable) {
        TechnicTypes type = technicsRepo.findTypeById(id);

        switch (type){
            case DESKTOP -> desktopService.edit(id, field, variable);
            case DISPLAY -> displayService.edit(id, field, variable);
            case LAPTOP -> laptopService.edit(id, field, variable);
            case STORAGE -> storageService.edit(id, field, variable);
            default -> {return false;}
        }

        return true;
    }

    public Boolean delete(Long id) {
        TechnicTypes type = technicsRepo.findTypeById(id);

        switch (type){
            case DESKTOP -> desktopService.delete(id);
            case DISPLAY -> displayService.delete(id);
            case LAPTOP -> laptopService.delete(id);
            case STORAGE -> storageService.delete(id);
            default -> {return false;}
        }

        return true;
    }

    public TechnicsResponse getById(Long id) {
        return null;
    }

    public List<TechnicsResponse> getByType(String type) {
        return null;
    }
}
