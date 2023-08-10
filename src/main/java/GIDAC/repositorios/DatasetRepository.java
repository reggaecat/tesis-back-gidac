package GIDAC.repositorios;

import GIDAC.modelo.Dataset;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DatasetRepository extends JpaRepository<Dataset,Integer> {
    List<Dataset> findByProfundidadParcela_idParcela(Integer idParcela);
    Dataset findByProfundidadParcelaParcelaIdParcelaAndProfundidadParcelaProfundidadIdProfundidad(Integer idParcela, Integer idProfundidad);
}
