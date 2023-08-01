package GIDAC.repositorios;

import GIDAC.modelo.CatalogoEspoch;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatalogoEspochRepository extends JpaRepository<CatalogoEspoch,Integer> {
    List<CatalogoEspoch> findByVigencia(Boolean vigencia);
    
    
}
