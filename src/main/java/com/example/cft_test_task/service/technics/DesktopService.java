package com.example.cft_test_task.service.technics;

import com.example.cft_test_task.model.entities.computers.TechnicsEntity;
import com.example.cft_test_task.model.entities.computers.technics.DesktopEntity;
import com.example.cft_test_task.model.rest.request.TechnicsRequest;
import com.example.cft_test_task.model.rest.response.TechnicsResponse;
import com.example.cft_test_task.repos.DesktopsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DesktopService extends AnyTechService{
    DesktopsRepo desktopsRepo;
    @Autowired
    public DesktopService(DesktopsRepo desktopsRepo){
        this.desktopsRepo = desktopsRepo;
    }
    @Override
    public boolean add(TechnicsRequest technicsRequest) {
        DesktopEntity desktopEntity = new DesktopEntity();
        TechnicsEntity technicsEntity = new TechnicsEntity();
        initTechEntity(technicsEntity, technicsRequest);
        desktopsRepo.save(desktopEntity);
        desktopsRepo.flush();
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
        return desktopsRepo.findAll();
    }
}
