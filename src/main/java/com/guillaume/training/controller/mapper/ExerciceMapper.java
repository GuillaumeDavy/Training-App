package com.guillaume.training.controller.mapper;

import com.guillaume.training.controller.dto.ExercicePayload;
import com.guillaume.training.controller.dto.ExerciceResponse;
import com.guillaume.training.service.model.Exercice;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ExerciceMapper {
    public static Exercice getModelFromDTO(ExercicePayload exercicePayload){
        return new Exercice(
                exercicePayload.getId(),
                exercicePayload.getName(),
                exercicePayload.getDescription()
        );
    }

    public static ExerciceResponse getDTOFromModel(Exercice exercice){
        return new ExerciceResponse(
                exercice.getId(),
                exercice.getName(),
                exercice.getDescription()
        );
    }
}
