package com.guillaume.training.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExercicePayload {
    private Long id;
    private String name;
    private String description;
}
