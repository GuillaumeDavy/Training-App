package com.guillaume.training.repository.dao;

import com.guillaume.training.repository.ExerciceRepository;
import com.guillaume.training.repository.mapper.ExerciceMapper;
import com.guillaume.training.service.model.Exercice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ExerciceDAO {
    private final ExerciceRepository exerciceRepository;

    @Autowired
    public ExerciceDAO(ExerciceRepository exerciceRepository) {
        this.exerciceRepository = exerciceRepository;
    }
    public List<Exercice> findAll(){
        return exerciceRepository.findAll().stream()
                .map(ExerciceMapper::getModelFromEntity)
                .collect(Collectors.toList());
    }

    public Optional<Exercice> findById(Long id){
        return exerciceRepository.findById(id)
                .map(ExerciceMapper::getModelFromEntity);
    }

    public Exercice save(Exercice exercice){
        return ExerciceMapper.getModelFromEntity(
                exerciceRepository.save(ExerciceMapper.getEntityFromModel(exercice))
        );
    }
}
