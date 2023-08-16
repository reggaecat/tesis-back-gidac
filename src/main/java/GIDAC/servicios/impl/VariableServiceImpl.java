
package GIDAC.servicios.impl;


import GIDAC.modelo.Variable;
import GIDAC.repositorios.VariableRepository;
import GIDAC.servicios.VariableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class VariableServiceImpl implements VariableService {

    @Autowired
    private VariableRepository repository;

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
        repository.deleteById(id);
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
    
}
