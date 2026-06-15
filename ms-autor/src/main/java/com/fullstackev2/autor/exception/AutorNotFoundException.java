package com.fullstackev2.autor.exception;


public class AutorNotFoundException extends RuntimeException {
    public AutorNotFoundException(Integer id) {
        super("No se encontró el autor con ID: " + id);
    }
}