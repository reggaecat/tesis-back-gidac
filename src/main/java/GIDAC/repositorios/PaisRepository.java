package GIDAC.repositorios;

import GIDAC.modelo.Pais;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaisRepository extends JpaRepository<Pais,String> {
    
}
