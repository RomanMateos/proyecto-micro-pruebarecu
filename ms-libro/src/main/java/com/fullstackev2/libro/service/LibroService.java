package com.fullstackev2.libro.service;

import com.fullstackev2.libro.dto.LibroDTO;
import com.fullstackev2.libro.exception.LibroNotFoundException;
import com.fullstackev2.libro.mapper.LibroMapper;
import com.fullstackev2.libro.model.Libro;
import com.fullstackev2.libro.repository.LibroRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class LibroService {


    private final LibroRepository libroRepository;
    private final LibroMapper libroMapper;


    public List<LibroDTO> listarTodos() {
        log.info("[LibroService] Listando todos los libros");
        List<LibroDTO> lista = libroRepository.findAll()
                .stream()
                .map(libroMapper::toDTO)
                .toList();
        log.debug("[LibroService] Total encontrados: {}", lista.size());
        return lista;
    }


    public LibroDTO buscarPorId(Integer id) {
        log.info("[LibroService] Buscando libro con id={}", id);
        Libro libro = libroRepository.findById(id)
                .orElseThrow(() -> new LibroNotFoundException(id));
        return libroMapper.toDTO(libro);
    }


    public LibroDTO guardar(LibroDTO dto) {
        log.info("[LibroService] Guardando nuevo libro: {}", dto.getTitulo());
        Libro libro = libroMapper.toEntity(dto);
        Libro guardado = libroRepository.save(libro);
        log.info("[LibroService] Libro guardado con id={}", guardado.getId());
        return libroMapper.toDTO(guardado);
    }

        public void eliminar(Integer id) {
        log.info("[LibroService] Eliminando libro con id={}", id);
        if (!libroRepository.existsById(id)) {
            throw new LibroNotFoundException(id);
        }
        libroRepository.deleteById(id);
        log.info("[LibroService] Libro id={} eliminado exitosamente", id);
    }
}