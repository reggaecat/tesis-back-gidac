package GIDAC.servicios.impl;



import GIDAC.modelo.Institucion;
import GIDAC.repositorios.InstitucionRepository;
import GIDAC.servicios.InstitucionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class InstitucionServiceImpl implements InstitucionService {

    @Autowired
    private InstitucionRepository repository;

    @Override
    public Institucion guardar(Object objeto) {
        Institucion oA=(Institucion) objeto;
        return repository.save(oA);
    }

    @Override
    public Institucion buscarPorId(Integer id) {
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


}
