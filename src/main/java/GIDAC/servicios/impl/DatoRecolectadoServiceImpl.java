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
    public List buscarPorVigenciaVariable(Boolean vigencia, Integer id) {
        Variable variable=new Variable();
        variable.setIdVariable(id);
        return repository.findByVigenciaAndVariable(vigencia,variable);
    }

    @Override
    public List buscarPorEditable() {
        return repository.findByEditable(true);
    }

    @Override
    public List<Object[]> listarTodosLosDatos() {
        return repository.obtenerPromedioValores();
    }

    @Override
    public List<Object[]> listarTodosLosDatosProyecto(Integer idProyecto) {
        return repository.obtenerPromedioValoresProyecto(idProyecto);
    }

    @Override
    public List listarTodosLosDatosVariable(Integer idVariable) {
        return repository.obtenerPromedioValoresCatalogo(idVariable);
    }

}
