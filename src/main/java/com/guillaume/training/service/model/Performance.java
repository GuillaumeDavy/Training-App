package com.guillaume.training.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Performance {
    private Long id;
    private float maxWeight;
    private User user;
    private Exercice exercice;
}
