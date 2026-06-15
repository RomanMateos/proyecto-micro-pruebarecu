package com.fullstackev2.prestamo.dto;

import lombok.*;
import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrestamoResumenDTO {

    private Integer id;
    private String nombreAlumno;


    private String tituloLibro;


    private String nombreAutor;

    private Integer diasPrestamo;
    private LocalDate fechaPrestamo;
    private Boolean activo;
}