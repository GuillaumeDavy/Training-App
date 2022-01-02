package com.guillaume.training.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class UserPayload {
    Long id;
    String name;
    String email;
}
