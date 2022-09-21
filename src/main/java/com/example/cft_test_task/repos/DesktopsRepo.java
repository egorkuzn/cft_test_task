package com.example.cft_test_task.repos;

import com.example.cft_test_task.model.entities.computers.technics.DesktopEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DesktopsRepo extends JpaRepository<DesktopEntity, Long> {
}
