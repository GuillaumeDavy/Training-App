package com.guillaume.training.controller.mapper;

import com.guillaume.training.controller.dto.ExercicePayload;
import com.guillaume.training.controller.dto.ExerciceResponse;
import com.guillaume.training.service.model.Exercice;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExerciceMapperTest {
    @Test
    public void shouldMapCorrectlyAnExercicePayloadToExerciceModel(){
        ExercicePayload exercice = new ExercicePayload(1L, "exercice Name", "description");
        Exercice expected = new Exercice(1L, "exercice Name", "description");
        Exercice actual = ExerciceMapper.getModelFromDTO(exercice);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldMapCorrectlyAnExerciceModelToExerciceResponse(){
        Exercice exercice = new Exercice(1L, "exercice Name", "description");
        ExerciceResponse expected = new ExerciceResponse(1L, "exercice Name", "description");
        ExerciceResponse actual = ExerciceMapper.getDTOFromModel(exercice);

        assertEquals(expected, actual);
    }
}