package com.guillaume.training.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class PerformancePayload {
    Long id;
    float maxWeight;
    Long userID;
    Long exerciceID;
}
