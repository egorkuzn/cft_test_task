package com.example.cft_test_task.service.technics;

import com.example.cft_test_task.model.entities.computers.TechnicsEntity;
import com.example.cft_test_task.model.entities.computers.technics.DesktopEntity;
import com.example.cft_test_task.model.enums.tech.TechnicFields;
import com.example.cft_test_task.model.enums.tech.TechnicTypes;
import com.example.cft_test_task.model.enums.tech.details.desktop.PCFormFactor;
import com.example.cft_test_task.model.rest.request.TechnicsRequest;
import com.example.cft_test_task.model.rest.response.TechnicsResponse;
import com.example.cft_test_task.repos.DesktopsRepo;
import com.example.cft_test_task.repos.TechnicsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DesktopService extends AnyTechService{
    DesktopsRepo desktopsRepo;

    @Autowired
    public DesktopService(DesktopsRepo desktopsRepo, TechnicsRepo technicsRepo){
        super(technicsRepo);
        this.desktopsRepo = desktopsRepo;
    }
    @Override
    public boolean add(TechnicsRequest technicsRequest) {
        DesktopEntity desktopEntity = new DesktopEntity();
        TechnicsEntity technicsEntity = new TechnicsEntity();
        initTechEntity(technicsEntity, technicsRequest);
        desktopEntity.technicsEntity = technicsEntity;
        desktopEntity.formFactor = PCFormFactor.valueOf(technicsRequest.specificParam.toUpperCase());
        desktopsRepo.save(desktopEntity);
        desktopsRepo.flush();
        return false;
    }

    @Override
    public boolean edit(Long id, String field, String variable) {
        DesktopEntity desktopEntity = desktopsRepo.findFirstByTechnicsEntity(technicsRepo.findFirstBySerialNumber(id));

        try {
            TechnicFields technicField = TechnicFields.valueOf(field.toUpperCase());

            switch (technicField){
                case FORM_FACTOR: return editFormFactor(desktopEntity, variable);
                default: return editTechEntity(desktopEntity.technicsEntity, technicField, variable);
            }
        } catch (IllegalArgumentException e){
            return false;
        }
    }

    private boolean editFormFactor(DesktopEntity desktopEntity, String variable) throws IllegalArgumentException{
        desktopEntity.formFactor = PCFormFactor.valueOf(variable);
        return true;
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
        List<TechnicsResponse> technicsResponseArrayList = new ArrayList<>();
        List<DesktopEntity> desktopEntityArrayList = desktopsRepo.findAll();

        desktopEntityArrayList.forEach(desktopEntity -> technicsResponseArrayList.add(castToDesktopResponse(desktopEntity)));
        return technicsResponseArrayList;
    }

    private TechnicsResponse castToDesktopResponse(DesktopEntity desktopEntity) {
        TechnicsResponse technicsResponse = new TechnicsResponse();

        technicsResponse.specificFieldType = "form-factor";
        technicsResponse.specificFieldValue = desktopEntity.formFactor.name();

        castToTechnicsResponse(technicsResponse, desktopEntity.technicsEntity);

        return technicsResponse;
    }
}
