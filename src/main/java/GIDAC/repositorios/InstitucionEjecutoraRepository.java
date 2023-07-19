package GIDAC.repositorios;

import GIDAC.modelo.Institucion;
import GIDAC.modelo.InstitucionEjecutora;
import GIDAC.modelo.InstitucionEjecutoraId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstitucionEjecutoraRepository extends JpaRepository<InstitucionEjecutora,InstitucionEjecutoraId> {
}
