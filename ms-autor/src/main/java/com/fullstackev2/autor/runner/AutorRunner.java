package com.fullstackev2.autor.runner;

import com.fullstackev2.autor.model.Autor;
import com.fullstackev2.autor.repository.AutorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.time.LocalDate;

@Component
@RequiredArgsConstructor
@Slf4j
public class AutorRunner implements CommandLineRunner {

    private final AutorRepository autorRepository;

    @Override
    public void run(String... args) {
        if (autorRepository.count() == 0) {
            log.info("[AutorRunner] Cargando datos iniciales...");

            autorRepository.save(new Autor(null,
                    "Miguel de Cervantes", 5,
                    LocalDate.of(1547, 9, 29), false));

            autorRepository.save(new Autor(null,
                    "Gabriel García Márquez", 12,
                    LocalDate.of(1927, 3, 6), false));

            autorRepository.save(new Autor(null,
                    "Isabel Allende", 20,
                    LocalDate.of(1942, 8, 2), true));

            log.info("[AutorRunner] 3 autores cargados exitosamente");
        } else {
            log.info("[AutorRunner] Ya existen datos, no se cargan duplicados");
        }
    }
}