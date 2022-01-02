package com.guillaume.training.controller.mapper;

import com.guillaume.training.controller.dto.PerformancePayload;
import com.guillaume.training.controller.dto.PerformanceResponse;
import com.guillaume.training.service.model.Exercice;
import com.guillaume.training.service.model.Performance;
import com.guillaume.training.service.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PerformanceMapperTest {
    private final User user = new User(1L, "User", "email@email.fr");
    private final Exercice exercice = new Exercice(2L, "Exercice", "Description");

    @Test
    public void shouldMapCorrectlyAnPerformancePayloadToPerformanceModel(){
        PerformancePayload performance = new PerformancePayload(1L, 55.5f, 1L, 2L);
        Performance expected = new Performance(1L, 55.5f, user, exercice);
        Performance actual = PerformanceMapper.getModelFromDTO(performance, user, exercice);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldMapCorrectlyAnPerformanceModelToPerformanceResponse(){
        Performance performance = new Performance(1L, 55.5f, user, exercice);
        PerformanceResponse expected = new PerformanceResponse(1L, 55.5f, 1L, 2L);
        PerformanceResponse actual = PerformanceMapper.getDTOFromModel(performance);

        assertEquals(expected, actual);
    }

}