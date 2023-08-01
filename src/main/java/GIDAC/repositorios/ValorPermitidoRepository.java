package GIDAC.repositorios;

import GIDAC.modelo.ValorPermitido;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ValorPermitidoRepository extends JpaRepository<ValorPermitido,Integer> {
    
    List<ValorPermitido> findByVigenciaAndVariableUnidadMedidaIdVariableUnidadMedidaAndVariableUnidadMedidaVigencia(Boolean vigencia1, Integer id, Boolean vigencia2);
    
    
}
