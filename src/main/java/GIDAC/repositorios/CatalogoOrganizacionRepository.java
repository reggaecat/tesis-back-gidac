package GIDAC.repositorios;

import GIDAC.modelo.CatalogoOrganizacion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CatalogoOrganizacionRepository extends JpaRepository<CatalogoOrganizacion,Integer> {
    List<CatalogoOrganizacion> findByVigencia(Boolean vigencia);
    CatalogoOrganizacion findByCodigoVariableOrganizacion(String codigovariable);
    List<CatalogoOrganizacion> findByVigenciaAndOrganizacionIdOrganizacionAndOrganizacionVigencia(Boolean vigencia, Integer idOrganizacion, Boolean vigenciaOrganizacion);
    List<CatalogoOrganizacion> findByVigenciaAndVariableIdVariable(Boolean vigencia, Integer idVariable);
    
    
}
