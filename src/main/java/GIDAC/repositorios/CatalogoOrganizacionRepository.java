package GIDAC.repositorios;

import GIDAC.modelo.CatalogoOrganizacion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatalogoOrganizacionRepository extends JpaRepository<CatalogoOrganizacion,String> {
    List<CatalogoOrganizacion> findByVigencia(Boolean vigencia);
}
