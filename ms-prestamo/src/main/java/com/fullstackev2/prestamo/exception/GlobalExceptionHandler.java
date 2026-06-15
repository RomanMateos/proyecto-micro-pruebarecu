package com.fullstackev2.prestamo.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.stream.Collectors;


@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> manejarValidacion(MethodArgumentNotValidException ex) {
        String errores = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(e -> e.getField() + ": " + e.getDefaultMessage())
                .collect(Collectors.joining(", "));
        log.warn("[GlobalExceptionHandler] Validación fallida: {}", errores);
        return ResponseEntity.badRequest().body(errores);
    }


    @ExceptionHandler(PrestamoNotFoundException.class)
    public ResponseEntity<String> manejarNoEncontrado(PrestamoNotFoundException ex) {
        log.warn("[GlobalExceptionHandler] Préstamo no encontrado: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> manejarGenerico(Exception ex) {
        log.error("[GlobalExceptionHandler] Error: {}", ex.getMessage());
        return ResponseEntity.internalServerError()
                .body("Error: " + ex.getMessage());
    }
}