package com.guillaume.training.service;

import com.guillaume.training.controller.dto.ExercicePayload;
import com.guillaume.training.controller.mapper.ExerciceMapper;
import com.guillaume.training.exception.ExerciceNotFoundException;
import com.guillaume.training.repository.dao.ExerciceDAO;
import com.guillaume.training.repository.entity.ExerciceEntity;
import com.guillaume.training.repository.ExerciceRepository;
import com.guillaume.training.service.model.Exercice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciceService {
    private final ExerciceDAO exerciceDAO;

    @Autowired
    public ExerciceService(ExerciceDAO exerciceDAO) {
        this.exerciceDAO = exerciceDAO;
    }

    public List<Exercice> findAll(){
        return exerciceDAO.findAll();
    }

    public Exercice findById(Long id){
        return exerciceDAO.findById(id)
                .orElseThrow(() -> new ExerciceNotFoundException(id));
    }

    public Exercice add(Exercice exercice){
        return exerciceDAO.save(exercice);
    }

    public Exercice update(Exercice newExercice, Long id) {
        return exerciceDAO.findById(id)
                .map(exercice -> {
                    exercice.setName(newExercice.getName());
                    exercice.setDescription(newExercice.getDescription());
                    return exerciceDAO.save(exercice);
                })
                .orElseGet(() -> {
                    newExercice.setId(id);
                    return exerciceDAO.save(newExercice);
                });
    }
}
