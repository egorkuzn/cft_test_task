package com.example.cft_test_task.repos;

import com.example.cft_test_task.model.entities.TechnicsEntity;
import com.example.cft_test_task.model.entities.technics.StorageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoragesRepo extends JpaRepository<StorageEntity, Long> {
    StorageEntity findFirstByTechnicsEntity(TechnicsEntity technicsEntity);
}
