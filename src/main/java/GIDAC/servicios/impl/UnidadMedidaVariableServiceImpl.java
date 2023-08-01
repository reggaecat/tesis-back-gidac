package GIDAC.servicios.impl;


import GIDAC.modelo.EquivalenciaVariable;
import GIDAC.modelo.VariableUnidadMedida;
import GIDAC.repositorios.EquivalenciaVariableRepository;
import GIDAC.repositorios.UnidadMedidaVariableRepository;
import GIDAC.servicios.EquivalenciaVariableService;
import GIDAC.servicios.UnidadMedidaVariableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class UnidadMedidaVariableServiceImpl implements UnidadMedidaVariableService {

    @Autowired
    private UnidadMedidaVariableRepository repository;

    @Override
    public VariableUnidadMedida guardar(Object objeto) {
        VariableUnidadMedida oA=(VariableUnidadMedida) objeto;
        return repository.save(oA);
    }

//    @Override
//    public List buscarPorProyecto(Integer idProyecto) {
//        return repository.findByCatalogoEspochProyectoInvestigacionIdProyecto(idProyecto);
//    }

    @Override
    public Object buscarPorId(Integer id) {
        return repository.findByIdVariableUnidadMedidaAndVigencia(id, true);
    }

    @Override
    public List buscarTodos(Boolean vigencia) {
        return repository.findByVigencia(vigencia);
    }

    @Override
    public void eliminar(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public Object buscarPorUnidadMedidaAndVariableAndVigencia(Integer idUnidadMedida, String idVariable, Boolean vigencia) {
        return repository.findByUnidadMedidaIdUnidadMedidaAndVariableIdVariableAndVigencia(idUnidadMedida, idVariable, vigencia);
    }


}
