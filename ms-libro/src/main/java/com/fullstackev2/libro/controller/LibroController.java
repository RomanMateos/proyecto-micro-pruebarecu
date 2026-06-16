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

    // Trae todos los libros de la BD
    @GetMapping
    public ResponseEntity<List<LibroDTO>> listarTodos() {
        log.info("[LibroController] GET /api/libros");
        return ResponseEntity.ok(libroService.listarTodos());
    }

    // Busca un libro por su ID
    @GetMapping("/{id}")
    public ResponseEntity<LibroDTO> buscarPorId(@PathVariable Integer id) {
        log.info("[LibroController] GET /api/libros/{}", id);
        return ResponseEntity.ok(libroService.buscarPorId(id));
    }

    // Crea un libro nuevo
    @PostMapping
    public ResponseEntity<LibroDTO> crear(@Valid @RequestBody LibroDTO dto) {
        log.info("[LibroController] POST /api/libros");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(libroService.guardar(dto));
    }

    // Actualiza un libro existente por su ID
    @PutMapping("/{id}")
    public ResponseEntity<LibroDTO> actualizar(@PathVariable Integer id,
                                               @Valid @RequestBody LibroDTO dto) {
        log.info("[LibroController] PUT /api/libros/{}", id);
        return ResponseEntity.ok(libroService.actualizar(id, dto));
    }

    // Elimina un libro por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        log.info("[LibroController] DELETE /api/libros/{}", id);
        libroService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}