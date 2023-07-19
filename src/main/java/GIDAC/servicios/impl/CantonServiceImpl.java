package GIDAC.servicios.impl;



import GIDAC.modelo.Canton;
import GIDAC.modelo.Pais;
import GIDAC.modelo.Provincia;
import GIDAC.repositorios.CantonRepository;
import GIDAC.repositorios.PaisRepository;
import GIDAC.repositorios.ProvinciaRepository;
import GIDAC.servicios.CantonService;
import GIDAC.servicios.PaisService;
import GIDAC.servicios.ProvinciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class CantonServiceImpl implements CantonService {

    @Autowired
    private CantonRepository repository;

    @Override
    public Canton guardar(Object objeto) {
        Canton oA=(Canton) objeto;
        return repository.save(oA);
    }

    @Override
    public Canton buscarPorId(String id) {
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
    public Object buscarPorProvincia(String codigo) {
        return repository.findByProvinciaCodigoProvincia(codigo);
    }


}
