package com.fullstackev2.prestamo.client;

import com.fullstackev2.prestamo.dto.LibroResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "ms-libro", url = "http://localhost:8091")
public interface LibroClient {


    @GetMapping("/api/libros/{id}")
    LibroResponseDTO obtenerLibroPorId(@PathVariable Integer id);
}