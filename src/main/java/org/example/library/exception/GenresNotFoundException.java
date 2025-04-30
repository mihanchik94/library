package org.example.library.exception;

public class GenresNotFoundException extends RuntimeException {
    public GenresNotFoundException(String message) {
        super(message);
    }
}
