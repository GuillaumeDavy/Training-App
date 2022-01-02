package com.guillaume.training.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(ExerciceNotFoundException.class)
    public ModelAndView handleExerciceNotFoundException(ExerciceNotFoundException exception) {
        return this.configureErrorPage(exception, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PerformanceNotFoundException.class)
    public ModelAndView handlePerformanceNotFoundException(PerformanceNotFoundException exception) {
        return this.configureErrorPage(exception, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ModelAndView handleUserNotFoundException(UserNotFoundException exception) {
        return this.configureErrorPage(exception, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ModelAndView handleUserAlreadyExistsException(UserAlreadyExistsException exception) {
        return this.configureErrorPage(exception, HttpStatus.CONFLICT);
    }

    private ModelAndView configureErrorPage(Exception exception, HttpStatus status) {
        String errorMessage = exception.getMessage() == null || exception.getMessage().equals("") ?
                "[pas de message précisé]" : exception.getMessage();

        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", exception);
        mav.addObject("statusCode", status);
        mav.addObject("errorMessage", errorMessage);

        return mav;
    }
}
