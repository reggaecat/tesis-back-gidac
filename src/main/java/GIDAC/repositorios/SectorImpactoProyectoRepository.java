package GIDAC.repositorios;

import GIDAC.modelo.Institucion;
import GIDAC.modelo.SectorImpacto;
import GIDAC.modelo.SectorImpactoProyecto;
import GIDAC.modelo.SectorImpactoProyectoId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SectorImpactoProyectoRepository extends JpaRepository<SectorImpactoProyecto,SectorImpactoProyectoId> {
    
    List<SectorImpactoProyecto> findByVigenciaAndProyectoInvestigacionIdProyecto(Boolean estado,Integer idProyecto);
}
