package com.fullstackev2.autor.controller;

import com.fullstackev2.autor.dto.AutorDTO;
import com.fullstackev2.autor.service.AutorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/autores")
@RequiredArgsConstructor
@Slf4j
public class AutorController {

    private final AutorService autorService;

    // Trae todos los autores de la BD
    @GetMapping
    public ResponseEntity<List<AutorDTO>> listarTodos() {
        log.info("[AutorController] GET /api/autores");
        return ResponseEntity.ok(autorService.listarTodos());
    }

    // Busca un autor por su ID
    @GetMapping("/{id}")
    public ResponseEntity<AutorDTO> buscarPorId(@PathVariable Integer id) {
        log.info("[AutorController] GET /api/autores/{}", id);
        return ResponseEntity.ok(autorService.buscarPorId(id));
    }

    // Crea un autor nuevo
    @PostMapping
    public ResponseEntity<AutorDTO> crear(@Valid @RequestBody AutorDTO dto) {
        log.info("[AutorController] POST /api/autores");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(autorService.guardar(dto));
    }

    // Actualiza un autor existente por su ID
    @PutMapping("/{id}")
    public ResponseEntity<AutorDTO> actualizar(@PathVariable Integer id,
                                               @Valid @RequestBody AutorDTO dto) {
        log.info("[AutorController] PUT /api/autores/{}", id);
        return ResponseEntity.ok(autorService.actualizar(id, dto));
    }

    // Elimina un autor por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        log.info("[AutorController] DELETE /api/autores/{}", id);
        autorService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}