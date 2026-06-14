package com.fullstackev2.libro.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;


@Entity
@Table(name = "libros")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Libro {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(nullable = false, length = 150)
    private String titulo;


    @Column(nullable = false)
    private Integer paginas;


    @Column(nullable = false)
    private LocalDate fechaPublicacion;


    @Column(nullable = false)
    private Boolean disponible;
}