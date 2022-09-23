package com.example.cft_test_task.repos;

import com.example.cft_test_task.model.entities.TechnicsEntity;
import com.example.cft_test_task.model.entities.technics.LaptopEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LaptopsRepo extends JpaRepository<LaptopEntity, Long> {
    LaptopEntity findFirstByTechnicsEntity(TechnicsEntity technicsEntity);
}
