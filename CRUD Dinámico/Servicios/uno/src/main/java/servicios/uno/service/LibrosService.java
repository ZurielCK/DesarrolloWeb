package servicios.uno.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import servicios.uno.model.Libros;

import servicios.uno.repository.LibrosRepo;


import java.util.List;
import java.util.Optional;

@Service
public class LibrosService {

    @Autowired
    private LibrosRepo repo;


    public List<Libros> getListLibros(){
        return repo.findAll();
    }

    public Optional<Libros> getById(Long id){
        return repo.findById(id);
    }

    public Libros saveLibros(Libros bean){
        return repo.save(bean);
    }

    /*public void deletePersona (Long id){
        repo.deleteById(id);

   public Page<Persona> PageofPersona(Pageable page){
        return repo.findAll(page);
    }
*/
}
