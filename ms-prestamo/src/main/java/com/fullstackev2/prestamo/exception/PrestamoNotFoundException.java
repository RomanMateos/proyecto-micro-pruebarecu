package com.fullstackev2.prestamo.exception;


public class PrestamoNotFoundException extends RuntimeException {
    public PrestamoNotFoundException(Integer id) {
        super("No se encontró el préstamo con ID: " + id);
    }
}