package com.fullstackev2.autor.service;

import com.fullstackev2.autor.dto.AutorDTO;
import com.fullstackev2.autor.exception.AutorNotFoundException;
import com.fullstackev2.autor.mapper.AutorMapper;
import com.fullstackev2.autor.model.Autor;
import com.fullstackev2.autor.repository.AutorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class AutorService {

    private final AutorRepository autorRepository;
    private final AutorMapper autorMapper;


    public List<AutorDTO> listarTodos() {
        log.info("[AutorService] Listando todos los autores");
        List<AutorDTO> lista = autorRepository.findAll()
                .stream()
                .map(autorMapper::toDTO)
                .toList();
        log.debug("[AutorService] Total encontrados: {}", lista.size());
        return lista;
    }


    public AutorDTO buscarPorId(Integer id) {
        log.info("[AutorService] Buscando autor con id={}", id);
        Autor autor = autorRepository.findById(id)
                .orElseThrow(() -> new AutorNotFoundException(id));
        return autorMapper.toDTO(autor);
    }


    public AutorDTO guardar(AutorDTO dto) {
        log.info("[AutorService] Guardando autor: {}", dto.getNombre());
        Autor autor = autorMapper.toEntity(dto);
        Autor guardado = autorRepository.save(autor);
        log.info("[AutorService] Autor guardado con id={}", guardado.getId());
        return autorMapper.toDTO(guardado);
    }


    public void eliminar(Integer id) {
        log.info("[AutorService] Eliminando autor con id={}", id);
        if (!autorRepository.existsById(id)) {
            throw new AutorNotFoundException(id);
        }
        autorRepository.deleteById(id);
        log.info("[AutorService] Autor id={} eliminado", id);
    }

    public AutorDTO actualizar(Integer id, AutorDTO dto) {
        log.info("[AutorService] Actualizando autor con id={}", id);
        Autor autor = autorRepository.findById(id)
                .orElseThrow(() -> new AutorNotFoundException(id));
        autor.setNombre(dto.getNombre());
        autor.setLibrosPublicados(dto.getLibrosPublicados());
        autor.setFechaNacimiento(dto.getFechaNacimiento());
        autor.setActivo(dto.getActivo());
        Autor actualizado = autorRepository.save(autor);
        log.info("[AutorService] Autor id={} actualizado exitosamente", id);
        return autorMapper.toDTO(actualizado);
    }
}