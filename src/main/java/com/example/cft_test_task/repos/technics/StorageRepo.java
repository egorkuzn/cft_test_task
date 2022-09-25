package com.example.cft_test_task.repos.technics;

import com.example.cft_test_task.model.entities.TechnicsEntity;
import com.example.cft_test_task.model.entities.technics.DesktopEntity;
import com.example.cft_test_task.model.entities.technics.StorageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StorageRepo extends JpaRepository<StorageEntity, Long> {
    StorageEntity findFirstByTechnicsEntity(TechnicsEntity technicsEntity);
}
