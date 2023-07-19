package GIDAC.servicios.impl;



import GIDAC.modelo.AreaInvestigacion;
import GIDAC.modelo.InstitucionParticipante;
import GIDAC.repositorios.AreaInvestigacionRepository;
import GIDAC.repositorios.InstitucionParticipanteRepository;
import GIDAC.servicios.AreaInvestigacionProyectoService;
import GIDAC.servicios.AreaInvestigacionService;
import GIDAC.servicios.InstitucionParticipanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class InstitucionParticipanteServiceImpl implements InstitucionParticipanteService {

     @Autowired
    private InstitucionParticipanteRepository repository;

    @Override
    public InstitucionParticipante guardar(Object objeto) {
        InstitucionParticipante oA=(InstitucionParticipante) objeto;
        return repository.save(oA);
    }


    @Override
    public List buscarTodos() {
        return repository.findAll();
    }
    
}
