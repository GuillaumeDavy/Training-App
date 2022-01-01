package com.guillaume.training.repository;

import com.guillaume.training.repository.entity.PerformanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerformanceRepository extends JpaRepository<PerformanceEntity, Long> {
}
