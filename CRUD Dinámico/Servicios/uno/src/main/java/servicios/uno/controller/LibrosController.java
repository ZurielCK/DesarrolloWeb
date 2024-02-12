package servicios.uno.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import servicios.uno.model.Libros;
import servicios.uno.service.LibrosService;


import java.util.List;


@RestController
@RequestMapping("/api/libros")
@CrossOrigin(origins = "http://localhost:5173")  // Especifica el origen permitido
public class LibrosController {

    @Autowired
    LibrosService service;

    @GetMapping
    public List<Libros> getAll() {
        return service.getListLibros();
    }

    /*@GetMapping("/{id}")
    public Libros get(@PathVariable Long id) {
        return service.getById(id).orElse(null);
    }*/

    @PostMapping("/add")
    public Libros saveLibros(@RequestBody Libros bean) {
        return service.saveLibros(bean);
    }

   /* @PostMapping("/libros")
    Page<Libros> getAll(Pageable pageable) {
        return service.PageofPersona(pageable);
    }

    */
}
