package GIDAC.servicios.impl;


import GIDAC.modelo.Dataset;
import GIDAC.modelo.DatoRecolectado;
import GIDAC.modelo.Variable;
import GIDAC.repositorios.DatoRecolectadoRepository;
import GIDAC.servicios.DatoRecolectadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class DatoRecolectadoServiceImpl implements DatoRecolectadoService {

    @Autowired
    private DatoRecolectadoRepository repository;

    @Override
    public DatoRecolectado guardar(Object objeto) {
        DatoRecolectado oA=(DatoRecolectado) objeto;
        return repository.save(oA);
    }

    @Override
    public DatoRecolectado buscarPorId(Integer id) {
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
    public List buscarPorVigenciaDataset(Boolean vigencia,Integer id) {
        Dataset dataset=new Dataset();
        dataset.setIdDataset(id);
        return repository.findByVigenciaAndDataset(vigencia, dataset);
    }
    
    @Override
    public List buscarPorVigenciaProyecto(Boolean vigencia, Integer id) {
        return repository.findByVigenciaAndDatasetProyectoInvestigacionIdProyecto(vigencia, id);
    }
    
    @Override
    public List buscarPorVigenciaVariable(Boolean vigencia, String id) {
        Variable variable=new Variable();
        variable.setIdVariable(id);
        return repository.findByVigenciaAndVariable(vigencia,variable);
    }

    @Override
    public List buscarPorEditable() {
        return repository.findByEditable(true);
    }

    @Override
    public List<Object[]> listarTodosLosDatosNumerico() {
        return repository.obtenerPromedioValoresNumerico();
    }
    
    @Override
    public List<Object[]> listarTodosLosDatosNominal() {
        return repository.obtenerPromedioValoresNominal();
    }

    @Override
    public List<Object[]> listarTodosLosDatosProyectoNumerico(Integer idProyecto) {
        return repository.obtenerPromedioValoresProyectoNumerico(idProyecto);
    }

    @Override
    public List listarTodosLosDatosVariableNumerico(String idVariable) {
        return repository.obtenerPromedioValoresCatalogoNumerico(idVariable);
    }
    
    @Override
    public List listarTodosLosDatosProyectoVariableNumerico(Integer idProyecto, String idVariable) {
        return repository.obtenerPromedioValoresProyectoVariableNumerico(idProyecto, idVariable);
    }
    
    @Override
    public List<Object[]> listarTodosLosDatosProyectoNominal(Integer idProyecto) {
        return repository.obtenerPromedioValoresProyectoNominal(idProyecto);
    }

    @Override
    public List listarTodosLosDatosVariableNominal(String idVariable) {
        return repository.obtenerPromedioValoresCatalogoNominal(idVariable);
    }
    
    @Override
    public List listarTodosLosDatosProyectoVariableNominal(Integer idProyecto, String idVariable) {
        return repository.obtenerPromedioValoresProyectoVariableNominal(idProyecto, idVariable);
    }

}
