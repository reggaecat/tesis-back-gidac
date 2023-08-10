package GIDAC.repositorios;

import GIDAC.modelo.Altura;
import GIDAC.modelo.Conglomerado;
import GIDAC.modelo.ProyectoInvestigacion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConglomeradoRepository extends JpaRepository<Conglomerado,Integer> {
    List<Conglomerado> findByProyectoInvestigacion(ProyectoInvestigacion proyectoInvestigacion);
    Conglomerado findByCodigoConglomeradoAndProyectoInvestigacionAndAltura(String codigoConglomerado,ProyectoInvestigacion proyectoInvestigacion, Altura altura);
    Conglomerado findByCodigoConglomeradoAndProyectoInvestigacion(String codigoConglomerado,ProyectoInvestigacion proyectoInvestigacion);
    
}
