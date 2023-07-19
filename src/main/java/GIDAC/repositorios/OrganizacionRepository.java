package GIDAC.repositorios;

import GIDAC.modelo.Organizacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizacionRepository extends JpaRepository<Organizacion,Integer> {
    
}
