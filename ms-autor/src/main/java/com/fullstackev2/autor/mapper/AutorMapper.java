package com.fullstackev2.autor.mapper;

import com.fullstackev2.autor.dto.AutorDTO;
import com.fullstackev2.autor.model.Autor;
import org.springframework.stereotype.Component;


@Component
public class AutorMapper {


    public AutorDTO toDTO(Autor autor) {
        if (autor == null) return null;
        AutorDTO dto = new AutorDTO();
        dto.setId(autor.getId());
        dto.setNombre(autor.getNombre());
        dto.setLibrosPublicados(autor.getLibrosPublicados());
        dto.setFechaNacimiento(autor.getFechaNacimiento());
        dto.setActivo(autor.getActivo());
        return dto;
    }


    public Autor toEntity(AutorDTO dto) {
        if (dto == null) return null;
        Autor autor = new Autor();
        autor.setNombre(dto.getNombre());
        autor.setLibrosPublicados(dto.getLibrosPublicados());
        autor.setFechaNacimiento(dto.getFechaNacimiento());
        autor.setActivo(dto.getActivo());
        return autor;
    }
}