package com.example.Web2.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
public class Libros {

    private @Id
    @GeneratedValue Long id;
    private String nombre;
    private String autor;
    @Temporal(TemporalType.DATE)
    private LocalDate fechaPub;
    private String imagen;


    public Libros(Long id, String nombre, String autor, LocalDate fechaPub, String imagen) {
        this.id = id;
        this.nombre = nombre;
        this.autor = autor;
        this.fechaPub = fechaPub;
        this.imagen=imagen;
    }

    public Libros() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public LocalDate getFechaPub() {
        return fechaPub;
    }

    public void setFechaPub(LocalDate fechaPub) {
        this.fechaPub = fechaPub;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
