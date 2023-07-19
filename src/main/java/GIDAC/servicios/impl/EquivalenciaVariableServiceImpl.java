package GIDAC.servicios.impl;


import GIDAC.modelo.EquivalenciaVariable;
import GIDAC.repositorios.EquivalenciaVariableRepository;
import GIDAC.servicios.EquivalenciaVariableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class EquivalenciaVariableServiceImpl implements EquivalenciaVariableService {

    @Autowired
    private EquivalenciaVariableRepository repository;

    @Override
    public EquivalenciaVariable guardar(Object objeto) {
        EquivalenciaVariable oA=(EquivalenciaVariable) objeto;
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
