package GIDAC.servicios.impl;



import GIDAC.modelo.Canton;
import GIDAC.modelo.Pais;
import GIDAC.modelo.Parroquia;
import GIDAC.modelo.Provincia;
import GIDAC.repositorios.CantonRepository;
import GIDAC.repositorios.PaisRepository;
import GIDAC.repositorios.ParroquiaRepository;
import GIDAC.repositorios.ProvinciaRepository;
import GIDAC.servicios.CantonService;
import GIDAC.servicios.PaisService;
import GIDAC.servicios.ParroquiaService;
import GIDAC.servicios.ProvinciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ParroquiaServiceImpl implements ParroquiaService {

    @Autowired
    private ParroquiaRepository repository;

    @Override
    public Parroquia guardar(Object objeto) {
        Parroquia oA=(Parroquia) objeto;
        return repository.save(oA);
    }

    @Override
    public Parroquia buscarPorId(String id) {
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

    @Override
    public Object buscarPorCanton(String codigo) {
        return repository.findByCantonCodigoCanton(codigo);
    }


}
