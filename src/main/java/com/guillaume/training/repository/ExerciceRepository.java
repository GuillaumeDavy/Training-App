package com.guillaume.training.repository;

import com.guillaume.training.repository.entity.ExerciceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciceRepository extends JpaRepository<ExerciceEntity, Long> {
}
