package GIDAC.repositorios;

import GIDAC.modelo.VariableUnidadMedida;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnidadMedidaVariableRepository extends JpaRepository<VariableUnidadMedida, Integer> {
    //List<EquivalenciaVariable> findByCatalogoEspochProyectoInvestigacionIdProyecto( Integer idProyecto);
    List<VariableUnidadMedida> findByVigencia(Boolean vigencia);
    VariableUnidadMedida findByIdVariableUnidadMedidaAndVigencia(Integer id, Boolean vigencia);
    VariableUnidadMedida findByUnidadMedidaIdUnidadMedidaAndVariableIdVariableAndVigencia(Integer idUnidadMedida, String idVariable, Boolean vigencia);
}
