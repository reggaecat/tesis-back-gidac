package GIDAC.repositorios;

import GIDAC.modelo.UnidadMedida;
import GIDAC.modelo.Profundidad;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfundidadRepository extends JpaRepository<Profundidad,Integer> {
    List<Profundidad> findByUnidadMedida(UnidadMedida medida);
    Profundidad findByProfundidadMinimaAndProfundidadMaximaAndUnidadMedida_Abreviatura(Double profundidadMinima, Double profundidadMaxima, String abreviatura);
}
