package com.guillaume.training.service.model;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class Exercice {
    private Long id;
    private String name;
    private String description;
}
