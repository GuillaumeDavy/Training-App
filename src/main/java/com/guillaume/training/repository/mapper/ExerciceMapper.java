package com.guillaume.training.repository.mapper;

import com.guillaume.training.repository.entity.ExerciceEntity;
import com.guillaume.training.service.model.Exercice;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ExerciceMapper {
    public static ExerciceEntity getEntityFromModel(Exercice exercice){
        return new ExerciceEntity(
                exercice.getId(),
                exercice.getName(),
                exercice.getDescription()
        );
    }

    public static Exercice getModelFromEntity(ExerciceEntity exerciceEntity){
        return new Exercice(
                exerciceEntity.getId(),
                exerciceEntity.getName(),
                exerciceEntity.getDescription()
        );
    }
}
