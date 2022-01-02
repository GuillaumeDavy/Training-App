package com.guillaume.training.controller.mapper;

import com.guillaume.training.controller.dto.PerformancePayload;
import com.guillaume.training.controller.dto.PerformanceResponse;
import com.guillaume.training.service.model.Exercice;
import com.guillaume.training.service.model.Performance;
import com.guillaume.training.service.model.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PerformanceMapper {
    public static Performance getModelFromDTO(PerformancePayload performancePayload, User user, Exercice exercice){
        return new Performance(
                performancePayload.getId(),
                performancePayload.getMaxWeight(),
                user,
                exercice
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
