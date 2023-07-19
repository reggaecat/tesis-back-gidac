package GIDAC.repositorios;

import GIDAC.modelo.CatalogoOrganizacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatalogoOrganizacionRepository extends JpaRepository<CatalogoOrganizacion,String> {
    
}
