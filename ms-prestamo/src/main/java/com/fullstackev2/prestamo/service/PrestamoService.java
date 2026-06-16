package com.fullstackev2.prestamo.service;

import com.fullstackev2.prestamo.client.AutorClient;
import com.fullstackev2.prestamo.client.LibroClient;
import com.fullstackev2.prestamo.dto.*;
import com.fullstackev2.prestamo.exception.PrestamoNotFoundException;
import com.fullstackev2.prestamo.model.Prestamo;
import com.fullstackev2.prestamo.repository.PrestamoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class PrestamoService {


    private final PrestamoRepository prestamoRepository;


    private final LibroClient libroClient;


    private final AutorClient autorClient;


    public List<PrestamoResumenDTO> listarTodos() {
        log.info("[PrestamoService] Listando todos los préstamos con datos de libro y autor");

        return prestamoRepository.findAll()
                .stream()
                .map(this::convertirAResumen) // convierte cada préstamo a resumen enriquecido
                .toList();
    }


    public PrestamoResumenDTO buscarPorId(Integer id) {
        log.info("[PrestamoService] Buscando préstamo con id={}", id);
        Prestamo prestamo = prestamoRepository.findById(id)
                .orElseThrow(() -> new PrestamoNotFoundException(id));
        return convertirAResumen(prestamo);
    }


    public PrestamoResumenDTO guardar(PrestamoDTO dto) {
        log.info("[PrestamoService] Registrando préstamo para alumno: {}", dto.getNombreAlumno());


        log.info("[PrestamoService] Consultando ms-libro para libroId={}", dto.getLibroId());
        LibroResponseDTO libro = libroClient.obtenerLibroPorId(dto.getLibroId());
        log.info("[PrestamoService] Libro encontrado: {}", libro.getTitulo());


        log.info("[PrestamoService] Consultando ms-autor para autorId={}", dto.getAutorId());
        AutorResponseDTO autor = autorClient.obtenerAutorPorId(dto.getAutorId());
        log.info("[PrestamoService] Autor encontrado: {}", autor.getNombre());


        Prestamo prestamo = new Prestamo();
        prestamo.setNombreAlumno(dto.getNombreAlumno());
        prestamo.setLibroId(dto.getLibroId());
        prestamo.setAutorId(dto.getAutorId());
        prestamo.setDiasPrestamo(dto.getDiasPrestamo());
        prestamo.setFechaPrestamo(dto.getFechaPrestamo());
        prestamo.setActivo(dto.getActivo());

        Prestamo guardado = prestamoRepository.save(prestamo);
        log.info("[PrestamoService] Préstamo guardado con id={}", guardado.getId());


        return convertirAResumen(guardado);
    }


    public void eliminar(Integer id) {
        log.info("[PrestamoService] Eliminando préstamo con id={}", id);
        if (!prestamoRepository.existsById(id)) {
            throw new PrestamoNotFoundException(id);
        }
        prestamoRepository.deleteById(id);
        log.info("[PrestamoService] Préstamo id={} eliminado", id);
    }


    private PrestamoResumenDTO convertirAResumen(Prestamo p) {
        log.debug("[PrestamoService] Enriqueciendo préstamo id={} con datos externos", p.getId());


        String tituloLibro;
        try {
            tituloLibro = libroClient.obtenerLibroPorId(p.getLibroId()).getTitulo();
        } catch (Exception e) {

            log.warn("[PrestamoService] No se pudo obtener libro id={}: {}", p.getLibroId(), e.getMessage());
            tituloLibro = "Libro ID: " + p.getLibroId();
        }


        String nombreAutor;
        try {
            nombreAutor = autorClient.obtenerAutorPorId(p.getAutorId()).getNombre();
        } catch (Exception e) {
            log.warn("[PrestamoService] No se pudo obtener autor id={}: {}", p.getAutorId(), e.getMessage());
            nombreAutor = "Autor ID: " + p.getAutorId();
        }


        return new PrestamoResumenDTO(
                p.getId(),
                p.getNombreAlumno(),
                tituloLibro,
                nombreAutor,
                p.getDiasPrestamo(),
                p.getFechaPrestamo(),
                p.getActivo()
        );
    }

    public PrestamoResumenDTO actualizar(Integer id, PrestamoDTO dto) {
        log.info("[PrestamoService] Actualizando préstamo con id={}", id);
        Prestamo prestamo = prestamoRepository.findById(id)
                .orElseThrow(() -> new PrestamoNotFoundException(id));
        prestamo.setNombreAlumno(dto.getNombreAlumno());
        prestamo.setLibroId(dto.getLibroId());
        prestamo.setAutorId(dto.getAutorId());
        prestamo.setDiasPrestamo(dto.getDiasPrestamo());
        prestamo.setFechaPrestamo(dto.getFechaPrestamo());
        prestamo.setActivo(dto.getActivo());
        Prestamo actualizado = prestamoRepository.save(prestamo);
        log.info("[PrestamoService] Préstamo id={} actualizado exitosamente", id);
        return convertirAResumen(actualizado);
    }
}