package com.platzi.play.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(MovieAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // Código HTTP 400
    public ErrorResponse handleMovieAlreadyExists(MovieAlreadyExistsException ex) {
        return new ErrorResponse(
                ex.getClass().getSimpleName(), // "MovieAlreadyExistsException"
                ex.getMessage()                // "La película '...' ya existe."
        );
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND) // Código HTTP 404
    public ErrorResponse handleResourceNotFound(ResourceNotFoundException ex) {
        return new ErrorResponse(
                ex.getClass().getSimpleName(), // "ResourceNotFoundException"
                ex.getMessage()                // "Película no encontrada con ID: ..."
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}