package com.example.cft_test_task.repos;

import com.example.cft_test_task.model.entities.TechnicsEntity;
import com.example.cft_test_task.model.entities.technics.TechEntityBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TechTypeRepo <TypeEntity extends TechEntityBase> extends JpaRepository<TypeEntity, Long> {
    TypeEntity findFirstByTechnicsEntity(TechnicsEntity technicsEntity);
    List<TypeEntity> findAll();
}
