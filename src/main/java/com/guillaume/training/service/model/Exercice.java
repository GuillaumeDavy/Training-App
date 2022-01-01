package com.guillaume.training.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Exercice {
    private Long id;
    private String name;
    private String description;
}
