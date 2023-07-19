package GIDAC.repositorios;

import GIDAC.modelo.Provincia;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProvinciaRepository extends JpaRepository<Provincia,String> {
    List<Provincia> findByPaisCodigoPais(String codigoPais);
}
