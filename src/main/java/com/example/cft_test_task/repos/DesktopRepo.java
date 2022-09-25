package com.example.cft_test_task.repos;

import com.example.cft_test_task.model.entities.TechnicsEntity;
import com.example.cft_test_task.model.entities.technics.DesktopEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DesktopRepo extends JpaRepository<DesktopEntity, Long> {
    DesktopEntity findFirstByTechnicsEntity(TechnicsEntity technicsEntity);
}
