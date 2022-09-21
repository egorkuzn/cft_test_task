package com.example.cft_test_task.repos;

import com.example.cft_test_task.model.entities.computers.TechnicsEntity;
import com.example.cft_test_task.model.enums.tech.TechnicTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TechnicsRepo extends JpaRepository<TechnicsEntity, Long>{
    TechnicsEntity findFirstBySerialNumber(Long serialNumber);
}
