package com.guillaume.training.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PerformancePayload {
    private Long id;
    private float maxWeight;
    private Long userID;
    private Long exerciceID;
}
