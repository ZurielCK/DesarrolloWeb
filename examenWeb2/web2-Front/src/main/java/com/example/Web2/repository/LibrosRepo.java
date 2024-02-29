package com.example.Web2.repository;

import com.example.Web2.model.Libros;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LibrosRepo extends JpaRepository <Libros, Long> {
    List<Libros> findAllByOrderByFechaPubDesc();
    List<Libros> findAllByOrderByAutorAsc();
    List<Libros>findByImagenIsNotNull();



}
