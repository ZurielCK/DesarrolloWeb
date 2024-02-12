package servicios.uno.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Libros {

    private @Id
    @GeneratedValue Long id;
    private String nombre;
    private String autor;
    private String genero;
    private String añoPub;

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

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getAñoPub() {
        return añoPub;
    }

    public void setAñoPub(String añoPub) {
        this.añoPub = añoPub;
    }
}
