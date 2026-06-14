package com.fullstackev2.libro.controller;

import com.fullstackev2.libro.dto.LibroDTO;
import com.fullstackev2.libro.service.LibroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/libros")
@RequiredArgsConstructor
@Slf4j
public class LibroController {

    private final LibroService libroService;


    @GetMapping
    public ResponseEntity<List<LibroDTO>> listarTodos() {
        log.info("[LibroController] GET /api/libros");
        return ResponseEntity.ok(libroService.listarTodos());
    }


    @GetMapping("/{id}")
    public ResponseEntity<LibroDTO> buscarPorId(@PathVariable Integer id) {
        log.info("[LibroController] GET /api/libros/{}", id);
        return ResponseEntity.ok(libroService.buscarPorId(id));
    }


    @PostMapping
    public ResponseEntity<LibroDTO> crear(@Valid @RequestBody LibroDTO dto) {
        log.info("[LibroController] POST /api/libros");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(libroService.guardar(dto));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        log.info("[LibroController] DELETE /api/libros/{}", id);
        libroService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}