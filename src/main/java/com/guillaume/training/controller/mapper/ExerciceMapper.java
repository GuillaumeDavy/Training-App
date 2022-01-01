package com.guillaume.training.controller.mapper;

import com.guillaume.training.service.model.Exercice;
import com.guillaume.training.controller.dto.ExercicePayload;
import com.guillaume.training.controller.dto.ExerciceResponse;
import com.guillaume.training.repository.entity.ExerciceEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ExerciceMapper {
    //Payload ---> Model ---> Entity
    public static Exercice getModelFromDTO(ExercicePayload exercicePayload){
        return new Exercice(
                exercicePayload.getId(),
                exercicePayload.getName(),
                exercicePayload.getDescription()
        );
    }

    public static ExerciceEntity getEntityFromModel(Exercice exercice){
        return new ExerciceEntity(
                exercice.getId(),
                exercice.getName(),
                exercice.getDescription()
        );
    }

    //Entity ---> Model ---> Response
    public static Exercice getModelFromEntity(ExerciceEntity exerciceEntity){
        return new Exercice(
                exerciceEntity.getId(),
                exerciceEntity.getName(),
                exerciceEntity.getDescription()
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
