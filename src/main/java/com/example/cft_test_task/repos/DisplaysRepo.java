package com.example.cft_test_task.repos;

import com.example.cft_test_task.model.entities.computers.technics.DisplayEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisplaysRepo extends JpaRepository<DisplayEntity, Long> {

}
