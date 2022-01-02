package com.guillaume.training.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class ExercicePayload {
    Long id;
    String name;
    String description;
}
