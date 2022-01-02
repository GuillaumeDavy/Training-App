package com.guillaume.training.exception;

import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpStatus;

@Value
@AllArgsConstructor
public class ErrorMessage {
    HttpStatus status;
    String message;
}
