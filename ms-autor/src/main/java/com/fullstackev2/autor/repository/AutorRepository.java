package com.fullstackev2.autor.repository;

import com.fullstackev2.autor.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AutorRepository extends JpaRepository<Autor, Integer> {
}