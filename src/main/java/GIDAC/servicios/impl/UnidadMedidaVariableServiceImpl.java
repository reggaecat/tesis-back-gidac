package GIDAC.servicios.impl;


import GIDAC.modelo.EquivalenciaVariable;
import GIDAC.modelo.UnidadMedidaVariable;
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
    public UnidadMedidaVariable guardar(Object objeto) {
        UnidadMedidaVariable oA=(UnidadMedidaVariable) objeto;
        return repository.save(oA);
    }

    @Override
    public List buscarTodos() {
        return repository.findAll();
    }

//    @Override
//    public List buscarPorProyecto(Integer idProyecto) {
//        return repository.findByCatalogoEspochProyectoInvestigacionIdProyecto(idProyecto);
//    }


}
