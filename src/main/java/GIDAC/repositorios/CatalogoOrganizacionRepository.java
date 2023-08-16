package GIDAC.repositorios;

import GIDAC.modelo.CatalogoOrganizacion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CatalogoOrganizacionRepository extends JpaRepository<CatalogoOrganizacion,String> {
    List<CatalogoOrganizacion> findByVigencia(Boolean vigencia);
    List<CatalogoOrganizacion> findByVigenciaAndOrganizacionIdOrganizacion(Boolean vigencia, Integer idOrganizacion);
    
    
}
