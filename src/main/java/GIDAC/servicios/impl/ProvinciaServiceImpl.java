package GIDAC.servicios.impl;



import GIDAC.modelo.Pais;
import GIDAC.modelo.Provincia;
import GIDAC.repositorios.PaisRepository;
import GIDAC.repositorios.ProvinciaRepository;
import GIDAC.servicios.PaisService;
import GIDAC.servicios.ProvinciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ProvinciaServiceImpl implements ProvinciaService {

    @Autowired
    private ProvinciaRepository repository;

    @Override
    public Provincia guardar(Object objeto) {
        Provincia oA=(Provincia) objeto;
        return repository.save(oA);
    }

    @Override
    public Provincia buscarPorId(String id) {
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
    public Object buscarPorPais(String codigo) {
        return repository.findByPaisCodigoPais(codigo);
    }


}
