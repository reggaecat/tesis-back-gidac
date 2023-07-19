/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package GIDAC.repositorios;



import GIDAC.modelo.ProyectoInvestigacion;
import GIDAC.modelo.GrupoInvestigacion;
import GIDAC.modelo.GrupoInvestigacionId;
import GIDAC.modelo.GrupoInvestigacionPk;
import GIDAC.modelo.Usuario;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author My Notebook
 */
public interface GrupoInvestigacionRepository extends CrudRepository<GrupoInvestigacion,GrupoInvestigacionId>{
    List<GrupoInvestigacion> findByProyectoInvestigacion(ProyectoInvestigacion investigacion);
    List<GrupoInvestigacion> findByVigenciaAndProyectoInvestigacionAndUsuarioRolNombreRol(Boolean estado,ProyectoInvestigacion investigacion, String nombreRol);
    List<GrupoInvestigacion> findByVigenciaAndUsuarioAndProyectoInvestigacion(Boolean estado,Usuario usuario,ProyectoInvestigacion investigacion);
    
    GrupoInvestigacion findByUsuarioAndProyectoInvestigacion(Usuario usuario,ProyectoInvestigacion investigacion);
    
    List<GrupoInvestigacion> findByVigenciaAndUsuario(Boolean vigencia, Usuario usuario);
    List<GrupoInvestigacion> findByUsuarioAndProyectoInvestigacionVigencia(Usuario usuario, Boolean vigenciaProy);
    //List<GrupoInvestigacion> findByIdProyectoInvestigacion(Integer idProyectoInvestigacion);
}
