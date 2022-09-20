package com.example.cft_test_task.service;

import com.example.cft_test_task.model.rest.request.TechnicsRequest;
import com.example.cft_test_task.model.rest.response.TechnicsResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechnicsService {
    public Boolean add(TechnicsRequest technicsRequest) {
        return true;
    }

    public Boolean edit(Long id, String field, String variable) {
        return true;
    }

    public Boolean delete(Long id) {
        return true;
    }

    public TechnicsResponse getById(Long id) {
        return null;
    }

    public List<TechnicsResponse> getByType(String type) {
        return null;
    }
}
