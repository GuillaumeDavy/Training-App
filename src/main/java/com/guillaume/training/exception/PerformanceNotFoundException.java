package com.guillaume.training.exception;

public class PerformanceNotFoundException extends RuntimeException{
    public PerformanceNotFoundException(Long id){
        super("Could not find performance " + id);
    }
}
