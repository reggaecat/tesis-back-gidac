package GIDAC.repositorios;

import GIDAC.modelo.ValorPermitido;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ValorPermitidoRepository extends JpaRepository<ValorPermitido,Integer> {
    List<ValorPermitido> findByVariable_IdVariable(Integer idvariable);
    
}
