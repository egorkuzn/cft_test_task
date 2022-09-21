package com.example.cft_test_task.service.technics;

import com.example.cft_test_task.model.entities.computers.TechnicsEntity;
import com.example.cft_test_task.model.enums.tech.TechnicFields;
import com.example.cft_test_task.model.rest.request.TechnicsRequest;
import com.example.cft_test_task.model.rest.response.TechnicsResponse;
import com.example.cft_test_task.repos.TechnicsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

public abstract class AnyTechService {
    TechnicsRepo technicsRepo;

    public AnyTechService(TechnicsRepo technicsRepo){
        this.technicsRepo = technicsRepo;
    }
    protected void initTechEntity(TechnicsEntity technicsEntity, TechnicsRequest technicsRequest){
        technicsEntity.countOfElems = technicsRequest.countOfElements;
        technicsEntity.price = technicsRequest.price;
        technicsEntity.producer = technicsRequest.producer;
    }

    protected boolean editTechEntity(TechnicsEntity technicsEntity, TechnicFields field, String value) throws IllegalArgumentException{
        switch (field){
            case PRODUCER -> technicsEntity.producer = value;
            case PRICE -> technicsEntity.price = BigDecimal.valueOf(Double.parseDouble(value));
            case COUNT -> technicsEntity.countOfElems = Integer.parseInt(value);
            default -> {return false;}
        }

        return true;
    }

    public abstract boolean add(TechnicsRequest technicsRequest);
    public abstract boolean edit(Long id, String field, String variable);
    public abstract boolean delete(Long id);
    public abstract TechnicsResponse getById(Long id);
    public abstract List<TechnicsResponse> getAll();
}
