package com.guillaume.training.controller.mapper;

import com.guillaume.training.service.model.Exercice;
import com.guillaume.training.service.model.Performance;
import com.guillaume.training.service.model.User;
import com.guillaume.training.controller.dto.PerformancePayload;
import com.guillaume.training.controller.dto.PerformanceResponse;
import com.guillaume.training.repository.entity.PerformanceEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PerformanceMapper {
    //Payload ---> Model ---> Entity
    public static Performance getModelFromDTO(PerformancePayload performancePayload, User user, Exercice exercice){
        return new Performance(
                performancePayload.getId(),
                performancePayload.getMaxWeight(),
                user,
                exercice
        );
    }

    public static PerformanceEntity getEntityFromModel(Performance performance){
        return new PerformanceEntity(
                performance.getId(),
                performance.getMaxWeight(),
                UserMapper.getEntityFromModel(performance.getUser()),
                ExerciceMapper.getEntityFromModel(performance.getExercice())
        );
    }

    //Entity ---> Model ---> Response
    public static Performance getModelFromEntity(PerformanceEntity performanceEntity){
        return new Performance(
                performanceEntity.getId(),
                performanceEntity.getMaxWeight(),
                UserMapper.getModelFromEntity(performanceEntity.getUserEntity()),
                ExerciceMapper.getModelFromEntity(performanceEntity.getExerciceEntity())
        );
    }

    public static PerformanceResponse getDTOFromModel(Performance performance){
        return new PerformanceResponse(
                performance.getId(),
                performance.getMaxWeight(),
                performance.getUser().getId(),
                performance.getExercice().getId()
        );
    }
}
