package com.guillaume.training.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class ExerciceResponse {
    Long id;
    String name;
    String description;
}
