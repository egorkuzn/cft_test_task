package com.example.cft_test_task.repos;

import com.example.cft_test_task.model.entities.TechnicsEntity;
import com.example.cft_test_task.model.entities.technics.DesktopEntity;
import com.example.cft_test_task.model.entities.technics.DisplayEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisplayRepo extends JpaRepository<DisplayEntity, Long> {
    DisplayEntity findFirstByTechnicsEntity(TechnicsEntity technicsEntity);
}
