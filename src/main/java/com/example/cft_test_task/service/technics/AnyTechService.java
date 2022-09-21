package com.example.cft_test_task.service.technics;

import com.example.cft_test_task.model.entities.computers.TechnicsEntity;
import com.example.cft_test_task.model.rest.request.TechnicsRequest;
import com.example.cft_test_task.model.rest.response.TechnicsResponse;

import java.util.List;

public abstract class AnyTechService {
    protected void initTechEntity(TechnicsEntity technicsEntity, TechnicsRequest technicsRequest){

    }

    public abstract boolean add(TechnicsRequest technicsRequest);
    public abstract boolean edit(Long id, String field, String variable);
    public abstract boolean delete(Long id);
    public abstract TechnicsResponse getById(Long id);
    public abstract List<TechnicsResponse> getAll();
}
