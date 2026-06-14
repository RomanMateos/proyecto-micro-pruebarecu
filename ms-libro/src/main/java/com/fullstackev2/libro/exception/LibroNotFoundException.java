package com.fullstackev2.libro.exception;

public class LibroNotFoundException extends RuntimeException {
    public LibroNotFoundException(Integer id) {
        super("No se encontró el libro con ID: " + id);
    }
}