package com.guillaume.training.service.model;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class User {
    private Long id;
    private String name;
    private String email;
}
