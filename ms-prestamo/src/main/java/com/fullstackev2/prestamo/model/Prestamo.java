package com.fullstackev2.prestamo.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "prestamos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Prestamo {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(nullable = false, length = 100)
    private String nombreAlumno;


    @Column(nullable = false)
    private Integer libroId;


    @Column(nullable = false)
    private Integer autorId;


    @Column(nullable = false)
    private Integer diasPrestamo;


    @Column(nullable = false)
    private LocalDate fechaPrestamo;


    @Column(nullable = false)
    private Boolean activo;
}