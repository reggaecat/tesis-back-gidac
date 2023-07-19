package GIDAC.servicios.impl;



import GIDAC.modelo.Pais;
import GIDAC.repositorios.PaisRepository;
import GIDAC.servicios.PaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class PaisServiceImpl implements PaisService {

    @Autowired
    private PaisRepository repository;

    @Override
    public Pais guardar(Object objeto) {
        Pais oA=(Pais) objeto;
        return repository.save(oA);
    }

    @Override
    public Pais buscarPorId(String id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List buscarTodos() {
        return repository.findAll();
    }

    @Override
    public void eliminar(String id) {
        repository.deleteById(id);
    }


}
