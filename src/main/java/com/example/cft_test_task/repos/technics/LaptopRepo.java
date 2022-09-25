package com.example.cft_test_task.repos.technics;

import com.example.cft_test_task.model.entities.TechnicsEntity;
import com.example.cft_test_task.model.entities.technics.DesktopEntity;
import com.example.cft_test_task.model.entities.technics.LaptopEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LaptopRepo extends JpaRepository<LaptopEntity, Long> {
    LaptopEntity findFirstByTechnicsEntity(TechnicsEntity technicsEntity);
}
