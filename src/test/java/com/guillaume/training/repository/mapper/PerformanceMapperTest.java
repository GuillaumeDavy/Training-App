package com.guillaume.training.repository.mapper;

import com.guillaume.training.repository.entity.ExerciceEntity;
import com.guillaume.training.repository.entity.PerformanceEntity;
import com.guillaume.training.repository.entity.UserEntity;
import com.guillaume.training.service.model.Exercice;
import com.guillaume.training.service.model.Performance;
import com.guillaume.training.service.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PerformanceMapperTest {
    private final User user = new User(1L, "User", "email@email.fr");
    private final UserEntity userEntity = new UserEntity(1L, "User", "email@email.fr");
    private final Exercice exercice = new Exercice(2L, "Exercice", "Description");
    private final ExerciceEntity exerciceEntity = new ExerciceEntity(2L, "Exercice", "Description");


    @Test
    public void shouldMapCorrectlyAnPerformanceModelToPerformanceEntity(){
        Performance performance = new Performance(1L, 55.5f, user, exercice);
        PerformanceEntity expected = new PerformanceEntity(1L, 55.5f, userEntity, exerciceEntity);
        PerformanceEntity actual = PerformanceMapper.getEntityFromModel(performance);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldMapCorrectlyAnPerformanceEntityToPerformanceModel(){
        PerformanceEntity performance = new PerformanceEntity(1L, 55.5f, userEntity, exerciceEntity);
        Performance expected = new Performance(1L, 55.5f, user, exercice);
        Performance actual = PerformanceMapper.getModelFromEntity(performance);

        assertEquals(expected, actual);
    }
}
