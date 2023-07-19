package GIDAC.repositorios;

import GIDAC.modelo.UnidadMedidaVariable;
import GIDAC.modelo.UnidadMedidaVariableId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnidadMedidaVariableRepository extends JpaRepository<UnidadMedidaVariable, UnidadMedidaVariableId> {
    //List<EquivalenciaVariable> findByCatalogoEspochProyectoInvestigacionIdProyecto( Integer idProyecto);
}
