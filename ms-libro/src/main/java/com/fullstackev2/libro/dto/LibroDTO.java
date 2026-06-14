package com.fullstackev2.libro.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LibroDTO {
    private Integer id;
    @NotBlank(message = "El titulo es obligatorio")
    @Size(min = 2, max = 150, message = "El titulo debe tener entre 2 y 150 caracteres")
    private String titulo;

    @NotNull(message = "Las páginas son obligatorias")
    @Min(value = 1, message = "El libro debe tener al menos 1 pagina")
    private Integer paginas;

    @NotNull(message = "La fecha de publicacion es obligatoria")
    private LocalDate fechaPublicacion;

    @NotNull(message = "El campo disponible es obligatorio")
    private Boolean disponible;
}