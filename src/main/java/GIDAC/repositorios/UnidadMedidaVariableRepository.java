package GIDAC.repositorios;

import GIDAC.modelo.VariableUnidadMedida;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnidadMedidaVariableRepository extends JpaRepository<VariableUnidadMedida, Integer> {
    //List<EquivalenciaVariable> findByCatalogoEspochProyectoInvestigacionIdProyecto( Integer idProyecto);
    List<VariableUnidadMedida> findByVigencia(Boolean vigencia);
    List<VariableUnidadMedida> findByVigenciaAndVariableVigencia(Boolean vigencia, Boolean vigenciaVariable);
    VariableUnidadMedida findByIdVariableUnidadMedidaAndVigencia(Integer id, Boolean vigencia);
    VariableUnidadMedida findByUnidadMedidaIdUnidadMedidaAndVariableIdVariableAndVigencia(Integer idUnidadMedida, Integer idVariable, Boolean vigencia);
    VariableUnidadMedida findByUnidadMedidaIdUnidadMedidaAndVariableIdVariable(Integer idUnidadMedida, Integer idVariable);
}
