package com.guillaume.training.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserPayload {
    private Long id;
    private String name;
    private String email;
}
