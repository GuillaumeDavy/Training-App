package com.guillaume.training.service.model;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class User {
    Long id;
    String name;
    String email;
}
