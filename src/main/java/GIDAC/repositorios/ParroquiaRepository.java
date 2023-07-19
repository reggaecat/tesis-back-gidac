package GIDAC.repositorios;

import GIDAC.modelo.Parroquia;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParroquiaRepository extends JpaRepository<Parroquia,String> {
    List<Parroquia> findByCantonCodigoCanton(String codigoCanton);
}
