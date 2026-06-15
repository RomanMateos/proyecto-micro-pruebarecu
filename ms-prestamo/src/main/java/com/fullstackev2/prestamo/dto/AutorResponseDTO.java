package com.fullstackev2.prestamo.dto;

import lombok.*;
import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class AutorResponseDTO {
    private Integer id;
    private String nombre;      // Solo necesitamos el nombre
    private Integer librosPublicados;
    private LocalDate fechaNacimiento;
    private Boolean activo;
}