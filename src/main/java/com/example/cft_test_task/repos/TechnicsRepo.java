package com.example.cft_test_task.repos;

import com.example.cft_test_task.model.entities.computers.TechnicsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnicsRepo extends JpaRepository<TechnicsEntity, Long>{
    TechnicsEntity findFirstBySerialNumber(Long serialNumber);
    void deleteFirstBySerialNumber(Long id);
}
