package com.guillaume.training.service.model;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class Performance {
    Long id;
    float maxWeight;
    User user;
    Exercice exercice;
}
