package com.fullstackev2.autor.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AutorDTO {

    private Integer id;
    @NotBlank(message = "El Nombre es obligatorio")
    @Size(min = 2, max = 100, message = "El Nombre debe tener entre 2 y 100 caracteres")
    private String nombre;

    @NotNull(message = "Los libros publicados son obligatorios")
    @Min(value = 0, message = "no puede ser negativo")
    private Integer librosPublicados;

    @NotNull(message = "La fecha de nacimiento es obligatoria")
    private LocalDate fechaNacimiento;

    @NotNull(message = "El campo disponible es obligatorio")
    private Boolean activo;
}