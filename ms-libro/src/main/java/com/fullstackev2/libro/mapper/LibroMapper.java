package com.fullstackev2.libro.mapper;

import com.fullstackev2.libro.dto.LibroDTO;
import com.fullstackev2.libro.model.Libro;
import org.springframework.stereotype.Component;


@Component
public class LibroMapper {


    public LibroDTO toDTO(Libro libro) {
        if (libro == null) return null;
        LibroDTO dto = new LibroDTO();
        dto.setId(libro.getId());
        dto.setTitulo(libro.getTitulo());
        dto.setPaginas(libro.getPaginas());
        dto.setFechaPublicacion(libro.getFechaPublicacion());
        dto.setDisponible(libro.getDisponible());
        return dto;
    }


    public Libro toEntity(LibroDTO dto) {
        if (dto == null) return null;
        Libro libro = new Libro();
        libro.setTitulo(dto.getTitulo());
        libro.setPaginas(dto.getPaginas());
        libro.setFechaPublicacion(dto.getFechaPublicacion());
        libro.setDisponible(dto.getDisponible());
        return libro;
    }
}