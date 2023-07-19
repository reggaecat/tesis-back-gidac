package GIDAC.repositorios;

import GIDAC.modelo.EquivalenciaVariable;
import GIDAC.modelo.EquivalenciaVariableId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquivalenciaVariableRepository extends JpaRepository<EquivalenciaVariable, EquivalenciaVariableId> {
    //List<EquivalenciaVariable> findByCatalogoEspochProyectoInvestigacionIdProyecto( Integer idProyecto);
}
