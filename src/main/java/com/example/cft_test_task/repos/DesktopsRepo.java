package com.example.cft_test_task.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DesktopsRepo extends JpaRepository<DesktopsRepo, Long> {
}
