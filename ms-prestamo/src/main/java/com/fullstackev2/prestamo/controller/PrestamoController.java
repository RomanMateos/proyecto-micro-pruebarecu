package com.fullstackev2.prestamo.controller;

import com.fullstackev2.prestamo.dto.PrestamoDTO;
import com.fullstackev2.prestamo.dto.PrestamoResumenDTO;
import com.fullstackev2.prestamo.service.PrestamoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/prestamos")
@RequiredArgsConstructor
@Slf4j
public class PrestamoController {

    private final PrestamoService prestamoService;

    // Trae todos los préstamos enriquecidos con datos de libro y autor
    @GetMapping
    public ResponseEntity<List<PrestamoResumenDTO>> listarTodos() {
        log.info("[PrestamoController] GET /api/prestamos");
        return ResponseEntity.ok(prestamoService.listarTodos());
    }

    // Busca un préstamo por ID con datos completos de libro y autor
    @GetMapping("/{id}")
    public ResponseEntity<PrestamoResumenDTO> buscarPorId(@PathVariable Integer id) {
        log.info("[PrestamoController] GET /api/prestamos/{}", id);
        return ResponseEntity.ok(prestamoService.buscarPorId(id));
    }

    // Registra un nuevo préstamo consultando ms-libro y ms-autor via FeignClient
    @PostMapping
    public ResponseEntity<PrestamoResumenDTO> crear(@Valid @RequestBody PrestamoDTO dto) {
        log.info("[PrestamoController] POST /api/prestamos");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(prestamoService.guardar(dto));
    }

    // Actualiza un préstamo existente por su ID
    @PutMapping("/{id}")
    public ResponseEntity<PrestamoResumenDTO> actualizar(@PathVariable Integer id,
                                                         @Valid @RequestBody PrestamoDTO dto) {
        log.info("[PrestamoController] PUT /api/prestamos/{}", id);
        return ResponseEntity.ok(prestamoService.actualizar(id, dto));
    }

    // Elimina un préstamo por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        log.info("[PrestamoController] DELETE /api/prestamos/{}", id);
        prestamoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}