package GIDAC.servicios.impl;



import GIDAC.modelo.AreaInvestigacion;
import GIDAC.modelo.LocalizacionProyecto;
import GIDAC.repositorios.AreaInvestigacionRepository;
import GIDAC.repositorios.LocalizacionProyectoRepository;
import GIDAC.servicios.AreaInvestigacionProyectoService;
import GIDAC.servicios.AreaInvestigacionService;
import GIDAC.servicios.LocalizacionProyectoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class LocalizacionProyectoServiceImpl implements LocalizacionProyectoService {

     @Autowired
    private LocalizacionProyectoRepository repository;

    @Override
    public LocalizacionProyecto guardar(Object objeto) {
        LocalizacionProyecto oA=(LocalizacionProyecto) objeto;
        return repository.save(oA);
    }


    @Override
    public List buscarTodos() {
        return repository.findAll();
    }

    @Override
    public List buscarPorProyecto(Integer id) {
        return repository.findByVigenciaAndProyectoInvestigacionIdProyecto(Boolean.TRUE, id);
    }

    
    
}
