package com.example.Web2.service;

import com.example.Web2.model.Libros;
import com.example.Web2.repository.LibrosRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class LibrosService {
    @Autowired
    private LibrosRepo repo;
    public List<Libros> getListLibros(){return repo.findAll();}
    public Libros saveLibros(Libros bean) {
        return repo.save(bean);
    }

    public Libros updateLibros(Long id, Libros libroAct) {
        libroAct.setId(id);
        return repo.save(libroAct);
    }
    public void deleteLibrosById(Long id) {
        repo.deleteById(id);
    }
    public List<Libros> findAllByOrderByFechaPubDesc() {
        return repo.findAllByOrderByFechaPubDesc();
    }
    public List<Libros> findAllOrderedByAutorAsc() {
        return repo.findAllByOrderByAutorAsc();
    }
    public List<Libros>findByImagenIsNotNull(){return repo.findByImagenIsNotNull();}
}
