package com.example.Web2.controller;

import com.example.Web2.model.Libros;
import com.example.Web2.service.LibrosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/libros")
@CrossOrigin(origins = {"*"})
public class LibrosController {
    @Autowired
    private LibrosService service;
    @GetMapping("/")
    public List<Libros>getAll(){return service.getListLibros();}
    @PostMapping("/add")
    public ResponseEntity<Libros> saveLibros(@RequestBody Libros bean) {
        Libros savedLibros = service.saveLibros(bean);
        return ResponseEntity.ok(savedLibros);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Libros> updateLibros(@PathVariable Long id, @RequestBody Libros libroAct) {
        Libros libroActualizadoGuardado = service.updateLibros(id, libroAct);
        return new ResponseEntity<>(libroActualizadoGuardado, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLibrosById(@PathVariable Long id) {
        service.deleteLibrosById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/fecha")
    public List<Libros> getLibrosOrdenadosPorFechaPubDesc() {
        return service.findAllByOrderByFechaPubDesc();
    }

    @GetMapping("/autor")
    public List<Libros> getAutoresOrdenadosAsc() {
        return service.findAllOrderedByAutorAsc();
    }

    @GetMapping("/imagen")
    public List<Libros>getLibrosPorImagen(){return service.findByImagenIsNotNull();}
}
