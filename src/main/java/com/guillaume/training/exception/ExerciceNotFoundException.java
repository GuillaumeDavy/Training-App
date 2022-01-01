package com.guillaume.training.exception;

public class ExerciceNotFoundException extends RuntimeException {
    public ExerciceNotFoundException(Long id) {
        super("Could not find exercice " + id);
    }
}
