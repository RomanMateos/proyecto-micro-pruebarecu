package com.fullstackev2.libro.repository;

import com.fullstackev2.libro.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LibroRepository extends JpaRepository<Libro, Integer> {
}