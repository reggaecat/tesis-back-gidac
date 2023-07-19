package GIDAC.servicios.impl;


import GIDAC.modelo.Localizacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import GIDAC.repositorios.LocalizacionRepository;
import GIDAC.servicios.LocalizacionService;

@Service
public class LocalizacionServiceImpl implements LocalizacionService {

    @Autowired
    private LocalizacionRepository repository;

    @Override
    public Localizacion guardar(Object objeto) {
        Localizacion oA=(Localizacion) objeto;
        return repository.save(oA);
    }

    @Override
    public Localizacion buscarPorId(Integer id) {
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

    @Override
    public Localizacion buscarPorPais(String id) {
        return (Localizacion) repository.findByCodigoPais(id);
    }
    
    @Override
    public Localizacion buscarPorProvincia(String id) {
        return (Localizacion) repository.findByCodigoProvincia(id);
    }
    
    @Override
    public Localizacion buscarPorCanton(String id) {
        return (Localizacion)repository.findByCodigoCanton(id);
    }
    
    @Override
    public Localizacion buscarPorParroquia(String id) {
        return (Localizacion)repository.findByCodigoParroquia(id);
    }
    
    @Override
    public List buscarPaises() {
        return repository.obtenerPaises();
    }

    @Override
    public List buscarPrvincias(String codPais) {
        return repository.obtenerProvincias(codPais);
    }

    @Override
    public List buscarCantones(String codPais, String codProvincia) {
       return repository.obtenerCantones(codPais, codProvincia);
    }

    @Override
    public List buscarParroquias(String codPais, String codProvincia, String codCanton) {
        return repository.findByCodigoPaisAndCodigoProvinciaAndCodigoCantonAndVigencia(codPais, codProvincia, codCanton, true);
    }

    @Override
    public List buscarVIgencia(Boolean vigencia) {
        return repository.findByVigencia(vigencia);
    }

}
