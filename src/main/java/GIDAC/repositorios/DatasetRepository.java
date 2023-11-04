package GIDAC.repositorios;

import GIDAC.modelo.Dataset;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DatasetRepository extends JpaRepository<Dataset,Integer> {
    List<Dataset> findByVigenciaAndProfundidadParcela_idParcela(Boolean vigencia, Integer idParcela);
    
    Dataset findByProfundidadParcelaParcelaIdParcelaAndProfundidadParcelaProfundidadIdProfundidad(Integer idParcela, Integer idProfundidad);
    
    @Query(value="  SELECT distinct ds.id_dataset" +
                "   FROM dato_recolectado dr JOIN dataset ds ON ds.id_dataset = dr.id_dataset" +
                "   WHERE ds.id_parcela=:idDataset AND dr.vigencia",
            nativeQuery=true)
    List<Object[]> obtenerDataSetUsados(@Param("idDataset") Integer idDataset);
}
