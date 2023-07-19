package GIDAC.repositorios;

import GIDAC.modelo.UnidadMedida;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnidadMedidaRepository extends JpaRepository<UnidadMedida,Integer> {
    UnidadMedida findByAbreviatura(String abreviatura);
}
