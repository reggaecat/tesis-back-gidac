
package GIDAC.servicios.impl;


import GIDAC.controladores.cValidaciones;
import GIDAC.modelo.CatalogoOrganizacion;
import GIDAC.modelo.Organizacion;
import GIDAC.modelo.Variable;
import GIDAC.repositorios.CatalogoOrganizacionRepository;
import GIDAC.repositorios.OrganizacionRepository;
import GIDAC.repositorios.VariableRepository;
import GIDAC.servicios.VariableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import org.bouncycastle.dvcs.DVCSRequestInfo;

@Service
public class VariableServiceImpl implements VariableService {

    @Autowired
    private VariableRepository repository;
    
    @Autowired
    private CatalogoOrganizacionRepository catalogoOrganizacionRepository;
    
    @Autowired
    private OrganizacionRepository organizacionRepository;

    @Override
    public Variable guardar(Object objeto) {
         Variable oA=(Variable) objeto;
        return repository.save(oA);
    }

    @Override
    public Variable buscarPorId(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List buscarTodos() {
        return repository.findAll();
    }

    @Override
    public void eliminar(Integer id) {
        List<CatalogoOrganizacion> oC=catalogoOrganizacionRepository.findByVigenciaAndVariableIdVariable(true, id);
        cValidaciones val=new cValidaciones();
        for(CatalogoOrganizacion dato:oC){
            dato.setFechaActualizacion(val.fechaActual());
            dato.setVigencia(false);
            catalogoOrganizacionRepository.save(dato);
        }
        Variable variable=repository.findById(id).orElse(null);
        variable.setVigencia(false);
        variable.setFechaActualizacion(val.fechaActual());
        repository.save(variable);
    }
    
    @Override
    public void activar(Integer id) {
        List<CatalogoOrganizacion> oC=catalogoOrganizacionRepository.findByVigenciaAndVariableIdVariable(false, id);
        cValidaciones val=new cValidaciones();
        for(CatalogoOrganizacion dato:oC){
            dato.setVigencia(true);
            dato.setFechaActualizacion(val.fechaActual());
            catalogoOrganizacionRepository.save(dato);
            Organizacion oranizacion=organizacionRepository.findByVigenciaAndIdOrganizacion(false,dato.getOrganizacion().getIdOrganizacion());
            if(oranizacion!=null){
                oranizacion.setFechaActualizacion(val.fechaActual());
                oranizacion.setVigencia(true);
                organizacionRepository.save(oranizacion);
            }
        }
        Variable variable=repository.findById(id).orElse(null);
        variable.setFechaActualizacion(val.fechaActual());
        variable.setVigencia(true);
        repository.save(variable);
        
        
    }

    @Override
    public List<Object[]>  litsarVairbalesCompletas() {
        return repository.obtenerVariablesCompletas();
    }

    
    @Override
    public List<Object[]>  litsarVairbalesCompletasInvestigador() {
        return repository.obtenerVariablesCompletasInvestigador();
    }

    
    @Override
    public List<Object[]>  litsarVairbalesIncompletas() {
        return repository.obtenerVariablesImcompletas();
    }

    @Override
    public List<Object[]>  litsarVairbalesConDatosSinFamilia() {
        return repository.obtenerVariablesConDatosSinFamilia();
    }

    @Override
    public List<Object[]>  litsarVairbalesConDatosConFiltroFamilia(Integer idFamilia) {
        return repository.obtenerVariablesConDatosFiltradoPorFamilia(idFamilia);
    }
    
    @Override
    public List<Object[]>  litsarVairbalesConDatosSinFamiliaOrganizacion(Integer idOrganizacion) {
        return repository.obtenerVariablesConDatosSinFamiliaOrganizacion(idOrganizacion);
    }

    @Override
    public List<Object[]>  litsarVairbalesConDatosConFiltroFamiliaOrganizacion(Integer idFamilia, Integer idOrganizacion) {
        return repository.obtenerVariablesConDatosFiltradoPorFamiliaOrganizacion(idFamilia, idOrganizacion);
    }

    @Override
    public List<Object[]>  litsarVairbalesConDatosSinFamiliaInvestigador(Integer idProyecto) {
        return repository.obtenerVariablesConDatosSinFamiliaInvestigador(idProyecto);
    }

    @Override
    public List<Object[]>  litsarVairbalesConDatosConFiltroFamiliaInvestigador(Integer idFamilia, Integer idProyecto) {
        return repository.obtenerVariablesConDatosFiltradoPorFamiliaInvestigador(idFamilia,idProyecto);
    }

    @Override
    public List<Object[]> listarCatalogoParaPerfilado() {
        return repository.obtenerVariablesParaCatalogo();
    }
    
    @Override
    public List<Object[]> listarCatalogoParaPerfiladoPorProyecto(Integer id) {
        return repository.obtenerVariablesParaCatalogoProProyeyecto(id);
    }
    
    @Override
    public List<Object[]> listarCatalogoParaPerfiladoPorProyectoOganizacion(Integer id, Integer idOrganizacion) {
        return repository.obtenerVariablesParaCatalogoProProyeyectoOrganizacion(id,idOrganizacion);
    }

    @Override
    public List buscarPorVigencia(Boolean vigencia) {
        return repository.findByVigencia(vigencia);
    }

    @Override
    public List buscarPorVigenciaAndCodigoVariable(Boolean vigencia, String codigoVariable) {
        return repository.findByVigenciaAndCodigoVariable(vigencia, codigoVariable);
    }

    @Override
    public List listarCatalogoParaPerfiladoPorProyectoCodigoDataset(Integer id, Integer codigoDataset) {
        return repository.obtenerVariablesParaCatalogoProProyeyectoCodigoDataset(id, codigoDataset);
    }

    @Override
    public List listarCatalogoParaPerfiladoPorProyectoOganizacionCodigoDataset(Integer id, Integer idOrganizacion, Integer codigoDataset) {
        return repository.obtenerVariablesParaCatalogoProProyeyectoOrganizacionCodigoDataset(id,idOrganizacion, codigoDataset);
    }

    @Override
    public Object findByVigenciaAndIdVariableAndCodigoVariable(Boolean vigencia, Integer idVariable, String codigoVariable) {
        return repository.findByVigenciaAndIdVariableAndCodigoVariable(vigencia,idVariable, codigoVariable);
    }
    
}
