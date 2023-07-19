package GIDAC.repositorios;

import GIDAC.modelo.Familia;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FamiliaRepository extends JpaRepository<Familia,Integer> {
    List<Familia> findByVigencia(Boolean vigenica);
}
