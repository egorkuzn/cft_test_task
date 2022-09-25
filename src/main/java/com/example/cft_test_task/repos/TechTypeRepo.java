package com.example.cft_test_task.repos;

import com.example.cft_test_task.model.entities.TechnicsEntity;
import com.example.cft_test_task.model.entities.technics.*;
import com.example.cft_test_task.model.enums.tech.TechnicTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TechTypeRepo<TypeEntity extends TechEntityBase>{
    final DesktopRepo desktopRepo;
    final DisplayRepo displayRepo;
    final LaptopRepo laptopRepo;
    final StorageRepo storageRepo;
    
    @Autowired
    public TechTypeRepo(DesktopRepo desktopRepo, DisplayRepo displayRepo, LaptopRepo laptopRepo, StorageRepo storageRepo){
        this.desktopRepo = desktopRepo;
        this.displayRepo = displayRepo;
        this.laptopRepo = laptopRepo;
        this.storageRepo = storageRepo;
    }
    
    public TypeEntity findFirstByTechnicsEntity(TechnicsEntity technicsEntity){ 
        try {
            return switch (technicsEntity.technicType){
                case DESKTOP -> (TypeEntity) desktopRepo.findFirstByTechnicsEntity(technicsEntity);
                case DISPLAY -> (TypeEntity) displayRepo.findFirstByTechnicsEntity(technicsEntity);
                case STORAGE -> (TypeEntity) storageRepo.findFirstByTechnicsEntity(technicsEntity);
                case LAPTOP -> (TypeEntity) laptopRepo.findFirstByTechnicsEntity(technicsEntity);
            };
        } catch (ClassCastException e){
            e.printStackTrace();
            return null;
        }
    }

    public void save(TypeEntity typeEntity) {
        try {
            switch (typeEntity.technicsEntity.technicType){
                case DESKTOP -> desktopRepo.save((DesktopEntity) typeEntity);
                case DISPLAY -> displayRepo.save((DisplayEntity) typeEntity);
                case STORAGE -> storageRepo.save((StorageEntity) typeEntity);
                case LAPTOP -> laptopRepo.save((LaptopEntity) typeEntity);
            };
        } catch (ClassCastException e){
            e.printStackTrace();
        }
    }

    public void delete(TypeEntity typeEntity) {
        try {
            switch (typeEntity.technicsEntity.technicType){
                case DESKTOP -> desktopRepo.delete((DesktopEntity) typeEntity);
                case DISPLAY -> displayRepo.delete((DisplayEntity) typeEntity);
                case STORAGE -> storageRepo.delete((StorageEntity) typeEntity);
                case LAPTOP -> laptopRepo.delete((LaptopEntity) typeEntity);
            }
        } catch (ClassCastException e){
            e.printStackTrace();
        }
    }

    public List<TypeEntity> findAll(TechnicTypes technicType) {
        try {
            return switch (technicType){
                case DESKTOP -> (List<TypeEntity>)desktopRepo.findAll();
                case DISPLAY -> (List<TypeEntity>)displayRepo.findAll();
                case STORAGE -> (List<TypeEntity>)storageRepo.findAll();
                case LAPTOP -> (List<TypeEntity>)laptopRepo.findAll();
            };
        } catch (ClassCastException e){
            e.printStackTrace();
            return null;
        }
    }
}
