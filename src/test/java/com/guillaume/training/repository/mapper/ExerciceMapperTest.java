package com.guillaume.training.repository.mapper;

import com.guillaume.training.repository.entity.ExerciceEntity;
import com.guillaume.training.service.model.Exercice;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExerciceMapperTest {
    @Test
    public void shouldMapCorrectlyAnExerciceModelToExerciceEntity(){
        Exercice exercice = new Exercice(1L, "exercice Name", "description");
        ExerciceEntity expected = new ExerciceEntity(1L, "exercice Name", "description");
        ExerciceEntity actual = ExerciceMapper.getEntityFromModel(exercice);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldMapCorrectlyAnExerciceEntityToExerciceModel(){
        ExerciceEntity exercice = new ExerciceEntity(1L, "exercice Name", "description");
        Exercice expected = new Exercice(1L, "exercice Name", "description");
        Exercice actual = ExerciceMapper.getModelFromEntity(exercice);

        assertEquals(expected, actual);
    }
}
