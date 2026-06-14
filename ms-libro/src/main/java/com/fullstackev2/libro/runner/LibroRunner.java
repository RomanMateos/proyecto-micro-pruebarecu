package com.fullstackev2.libro.runner;

import com.fullstackev2.libro.model.Libro;
import com.fullstackev2.libro.repository.LibroRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.time.LocalDate;

// El DataLoader carga datos iniciales al arrancar la aplicación
// Solo inserta si la tabla está vacía para no duplicar datos
@Component
@RequiredArgsConstructor
@Slf4j
public class LibroRunner implements CommandLineRunner {

    private final LibroRepository libroRepository;

    @Override
    public void run(String... args) {
        // Verifica si ya hay datos antes de insertar
        if (libroRepository.count() == 0) {
            log.info("[LibroRunner] Cargando datos iniciales...");

            libroRepository.save(new Libro(null,
                    "El Quijote", 1500,
                    LocalDate.of(1605, 1, 16), true));

            libroRepository.save(new Libro(null,
                    "Cien años de soledad", 432,
                    LocalDate.of(1967, 5, 30), true));

            libroRepository.save(new Libro(null,
                    "La Odisea", 541,
                    LocalDate.of(1990, 3, 1), false));

            log.info("[LibroRunner] 3 libros cargados exitosamente");
        } else {
            log.info("[LibroRunner] Ya existen datos, no se cargan duplicados");
        }
    }
}