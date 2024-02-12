package servicios.uno.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import servicios.uno.model.Libros;

public interface LibrosRepo extends JpaRepository<Libros,Long> {

}
