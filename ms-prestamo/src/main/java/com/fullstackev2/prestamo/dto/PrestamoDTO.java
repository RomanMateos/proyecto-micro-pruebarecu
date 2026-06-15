package com.fullstackev2.prestamo.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrestamoDTO {

    private Integer id;

    @NotBlank(message = "El nombre del alumno es obligatorio")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    private String nombreAlumno;

    @NotNull(message = "El ID del libro es obligatorio")
    @Min(value = 1, message = "El ID del libro debe ser mayor a 0")
    private Integer libroId;

    @NotNull(message = "El ID del autor es obligatorio")
    @Min(value = 1, message = "El ID del autor debe ser mayor a 0")
    private Integer autorId;

    @NotNull(message = "Los días de préstamo son obligatorios")
    @Min(value = 1, message = "El préstamo debe ser de al menos 1 día")
    private Integer diasPrestamo;

    @NotNull(message = "La fecha del préstamo es obligatoria")
    private LocalDate fechaPrestamo;

    @NotNull(message = "El campo activo es obligatorio")
    private Boolean activo;
}