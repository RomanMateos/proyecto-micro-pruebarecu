package com.fullstackev2.prestamo.dto;

import lombok.*;
import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class LibroResponseDTO {
    private Integer id;
    private String titulo;      // Solo necesitamos el título
    private Integer paginas;
    private LocalDate fechaPublicacion;
    private Boolean disponible;
}