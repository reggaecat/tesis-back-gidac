package GIDAC.servicios.impl;



import GIDAC.modelo.AreaInvestigacion;
import GIDAC.modelo.InstitucionEjecutora;
import GIDAC.repositorios.AreaInvestigacionRepository;
import GIDAC.repositorios.InstitucionEjecutoraRepository;
import GIDAC.servicios.AreaInvestigacionProyectoService;
import GIDAC.servicios.AreaInvestigacionService;
import GIDAC.servicios.InstitucionEjecutoraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class InstitucionEjecutoraServiceImpl implements InstitucionEjecutoraService {

     @Autowired
    private InstitucionEjecutoraRepository repository;

    @Override
    public InstitucionEjecutora guardar(Object objeto) {
        InstitucionEjecutora oA=(InstitucionEjecutora) objeto;
        return repository.save(oA);
    }


    @Override
    public List buscarTodos() {
        return repository.findAll();
    }
    
}
