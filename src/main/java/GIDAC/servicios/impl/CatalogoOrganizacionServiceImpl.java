package GIDAC.servicios.impl;


import GIDAC.modelo.CatalogoOrganizacion;
import GIDAC.repositorios.CatalogoOrganizacionRepository;
import GIDAC.servicios.CatalogoOrganizacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class CatalogoOrganizacionServiceImpl implements CatalogoOrganizacionService {

    @Autowired
    private CatalogoOrganizacionRepository repository;

    @Override
    public CatalogoOrganizacion guardar(Object objeto) {
        CatalogoOrganizacion oA=(CatalogoOrganizacion) objeto;
        return repository.save(oA);
    }

    @Override
    public CatalogoOrganizacion buscarPorId(String id) {
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
    public List buscarPorVigencia(Boolean vigencia) {
        return repository.findByVigencia(vigencia);
    }
    
    @Override
    public List buscarPorVigenciaAndOrganizacion(Boolean vigencia, Integer idOrganizacion) {
        return repository.findByVigenciaAndOrganizacionIdOrganizacion(vigencia, idOrganizacion);
    }
    
    
}
