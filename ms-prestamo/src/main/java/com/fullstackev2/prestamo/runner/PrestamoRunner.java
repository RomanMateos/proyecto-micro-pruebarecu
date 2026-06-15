package com.fullstackev2.prestamo.runner;

import com.fullstackev2.prestamo.model.Prestamo;
import com.fullstackev2.prestamo.repository.PrestamoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.time.LocalDate;


@Component
@RequiredArgsConstructor
@Slf4j
public class PrestamoRunner implements CommandLineRunner {

    private final PrestamoRepository prestamoRepository;

    @Override
    public void run(String... args) {
        if (prestamoRepository.count() == 0) {
            log.info("[PrestamoRunner] Cargando datos iniciales...");


            prestamoRepository.save(new Prestamo(null,
                    "Juan Pérez", 1, 1, 7,
                    LocalDate.of(2024, 3, 1), true));


            prestamoRepository.save(new Prestamo(null,
                    "María González", 2, 2, 14,
                    LocalDate.of(2024, 3, 5), true));


            prestamoRepository.save(new Prestamo(null,
                    "Carlos López", 3, 3, 10,
                    LocalDate.of(2024, 3, 10), false));

            log.info("[PrestamoRunner] 3 préstamos cargados exitosamente");
        } else {
            log.info("[PrestamoRunner] Ya existen datos, no se cargan duplicados");
        }
    }
}