package com.guillaume.training.repository.mapper;

import com.guillaume.training.repository.entity.PerformanceEntity;
import com.guillaume.training.service.model.Performance;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PerformanceMapper {
    public static PerformanceEntity getEntityFromModel(Performance performance){
        return new PerformanceEntity(
                performance.getId(),
                performance.getMaxWeight(),
                UserMapper.getEntityFromModel(performance.getUser()),
                ExerciceMapper.getEntityFromModel(performance.getExercice())
        );
    }

    public static Performance getModelFromEntity(PerformanceEntity performanceEntity){
        return new Performance(
                performanceEntity.getId(),
                performanceEntity.getMaxWeight(),
                UserMapper.getModelFromEntity(performanceEntity.getUserEntity()),
                ExerciceMapper.getModelFromEntity(performanceEntity.getExerciceEntity())
        );
    }
}
