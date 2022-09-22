package com.example.cft_test_task.service.technics;

import com.example.cft_test_task.model.entities.computers.TechnicsEntity;
import com.example.cft_test_task.model.entities.computers.technics.DisplayEntity;
import com.example.cft_test_task.model.enums.tech.TechnicFields;
import com.example.cft_test_task.model.rest.request.TechnicsRequest;
import com.example.cft_test_task.model.rest.response.TechnicsResponse;
import com.example.cft_test_task.repos.DisplaysRepo;
import com.example.cft_test_task.repos.TechnicsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DisplayService extends AnyTechService{
    DisplaysRepo displaysRepo;

    @Autowired
    public DisplayService(DisplaysRepo displaysRepo, TechnicsRepo technicsRepo){
        super(technicsRepo);
        this.displaysRepo = displaysRepo;
    }

    @Override
    public boolean add(TechnicsRequest technicsRequest) {
        try {
            DisplayEntity displayEntity = new DisplayEntity();
            TechnicsEntity technicsEntity = new TechnicsEntity();
            initTechEntity(technicsEntity, technicsRequest);
            displayEntity.technicsEntity = technicsEntity;
            displayEntity.diagonal = Float.parseFloat(technicsRequest.specificParam);
            displaysRepo.save(displayEntity);
            displaysRepo.flush();
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
    @Override
    public boolean edit(Long id, String field, String value) {
        DisplayEntity displayEntity = displaysRepo.findFirstByTechnicsEntity(technicsRepo.findFirstBySerialNumber(id));

        try {
            TechnicFields technicField = TechnicFields.valueOf(field.toUpperCase());

            return switch (technicField) {
                case DISPLAY_DIAGONAL -> editDiagonal(displayEntity, value);
                default -> editTechEntity(displayEntity.technicsEntity, technicField, value);
            };
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    private boolean editDiagonal(DisplayEntity displayEntity, String variable) throws IllegalArgumentException{
        displayEntity.diagonal = Float.parseFloat(variable);
        return true;
    }

    @Override
    public boolean delete(Long id) {
        DisplayEntity displayEntity = displaysRepo.findFirstByTechnicsEntity(technicsRepo.findFirstBySerialNumber(id));
        displaysRepo.delete(displayEntity);
        technicsRepo.deleteFirstBySerialNumber(id);
        return true;
    }
    @Override
    public TechnicsResponse getById(Long id) {
        DisplayEntity displayEntity = displaysRepo.findFirstByTechnicsEntity(technicsRepo.findFirstBySerialNumber(id));
        return castToDisplayResponse(displayEntity);
    }

    private TechnicsResponse castToDisplayResponse(DisplayEntity displayEntity) {
        TechnicsResponse technicsResponse = new TechnicsResponse();

        technicsResponse.specificFieldType = "diagonal";
        technicsResponse.specificFieldValue = String.valueOf(displayEntity.diagonal);

        castToTechnicsResponse(technicsResponse, displayEntity.technicsEntity);

        return technicsResponse;
    }

    @Override
    public List<TechnicsResponse> getAll() {
        List<TechnicsResponse> technicsResponseList = new ArrayList<>();
        List<DisplayEntity> displayEntityList = displaysRepo.findAll();

        displayEntityList.forEach(displayEntity -> technicsResponseList.add(castToDisplayResponse(displayEntity)));;
        return technicsResponseList;
    }
}
