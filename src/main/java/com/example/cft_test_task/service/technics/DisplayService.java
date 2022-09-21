package com.example.cft_test_task.service.technics;

import com.example.cft_test_task.model.rest.request.TechnicsRequest;
import com.example.cft_test_task.model.rest.response.TechnicsResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisplayService {
    public boolean add(TechnicsRequest technicsRequest) {
        return false;
    }

    public boolean edit(Long id, String field, String variable) {
        return false;
    }

    public boolean delete(Long id) {
        return false;
    }

    public TechnicsResponse getById(Long id) {
    }

    public List<TechnicsResponse> getAll() {
    }
}
