package com.example.cft_test_task.service.technics;

import com.example.cft_test_task.model.entities.computers.TechnicsEntity;
import com.example.cft_test_task.model.entities.computers.technics.DesktopEntity;
import com.example.cft_test_task.model.enums.tech.TechnicFields;
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
        try {
            DesktopEntity desktopEntity = new DesktopEntity();
            TechnicsEntity technicsEntity = new TechnicsEntity();
            initTechEntity(technicsEntity, technicsRequest);
            desktopEntity.technicsEntity = technicsEntity;
            desktopEntity.formFactor = PCFormFactor.valueOf(technicsRequest.specificParam.toUpperCase());
            desktopsRepo.save(desktopEntity);
            desktopsRepo.flush();
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public boolean edit(Long id, String field, String variable) {
        DesktopEntity desktopEntity = desktopsRepo.findFirstByTechnicsEntity(technicsRepo.findFirstBySerialNumber(id));

        try {
            TechnicFields technicField = TechnicFields.valueOf(field.toUpperCase());

            return switch (technicField) {
                case FORM_FACTOR -> editFormFactor(desktopEntity, variable);
                default -> editTechEntity(desktopEntity.technicsEntity, technicField, variable);
            };
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
        DesktopEntity desktopEntity = desktopsRepo.findFirstByTechnicsEntity(technicsRepo.findFirstBySerialNumber(id));
        desktopsRepo.delete(desktopEntity);
        technicsRepo.deleteFirstBySerialNumber(id);
        return true;
    }

    @Override
    public TechnicsResponse getById(Long id) {
        DesktopEntity desktopEntity = desktopsRepo.findFirstByTechnicsEntity(technicsRepo.findFirstBySerialNumber(id));
        return castToDesktopResponse(desktopEntity);
    }

    @Override
    public List<TechnicsResponse> getAll() {
        List<TechnicsResponse> technicsResponseList = new ArrayList<>();
        List<DesktopEntity> desktopEntityList = desktopsRepo.findAll();

        desktopEntityList.forEach(desktopEntity -> technicsResponseList.add(castToDesktopResponse(desktopEntity)));
        return technicsResponseList;
    }

    private TechnicsResponse castToDesktopResponse(DesktopEntity desktopEntity) {
        TechnicsResponse technicsResponse = new TechnicsResponse();

        technicsResponse.specificFieldType = "form_factor";
        technicsResponse.specificFieldValue = desktopEntity.formFactor.name();

        castToTechnicsResponse(technicsResponse, desktopEntity.technicsEntity);

        return technicsResponse;
    }
}
