package com.example.cft_test_task.service.technics;

import com.example.cft_test_task.model.rest.request.TechnicsRequest;
import com.example.cft_test_task.model.rest.response.TechnicsResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LaptopService extends AnyTechService{
    @Override
    public boolean add(TechnicsRequest technicsRequest) {
        return false;
    }
    @Override
    public boolean edit(Long id, String field, String variable) {
        return false;
    }
    @Override
    public boolean delete(Long id) {
        return false;
    }
    @Override
    public TechnicsResponse getById(Long id) {
    }
    @Override
    public List<TechnicsResponse> getAll() {
    }
}
