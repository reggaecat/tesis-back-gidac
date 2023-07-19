package GIDAC.repositorios;

import GIDAC.modelo.ProfundidadParcela;
import GIDAC.modelo.ProfundidadParcelaId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfundidadParcelaRepository extends JpaRepository<ProfundidadParcela,ProfundidadParcelaId> {
    
    ProfundidadParcela findByIdParcelaAndIdProfundidad(Integer idParcela, Integer idProfundidad);
}
