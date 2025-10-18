package com.platzi.play.exception;

public class MovieAlreadyExistsException extends RuntimeException {
    public MovieAlreadyExistsException(String title) {
        super("La película con el título '" + title + "' ya existe.");
    }
}