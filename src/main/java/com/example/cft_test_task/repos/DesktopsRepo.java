package com.example.cft_test_task.repos;

import com.example.cft_test_task.model.entities.computers.TechnicsEntity;
import com.example.cft_test_task.model.entities.computers.technics.DesktopEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DesktopsRepo extends JpaRepository<DesktopEntity, Long> {
    DesktopEntity findFirstByTechnicsEntity(TechnicsEntity technicsEntity);
    List<DesktopEntity> findAll();
}
