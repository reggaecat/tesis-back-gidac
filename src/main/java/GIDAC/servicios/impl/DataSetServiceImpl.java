package GIDAC.servicios.impl;


import GIDAC.modelo.Dataset;
import GIDAC.repositorios.DatasetRepository;
import GIDAC.servicios.DataSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class DataSetServiceImpl implements DataSetService {

    @Autowired
    private DatasetRepository repository;

    @Override
    public Dataset guardar(Object objeto) {
        Dataset oA=(Dataset) objeto;
        return repository.save(oA);
    }

    @Override
    public Dataset buscarPorId(Integer id) {
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
    public List buscarPorParcela(Integer id) {
        return repository.findByProfundidadParcela_idParcela(id);
    }

    @Override
    public Object buscarPorParcelaProfundidad(Integer idParcela, Integer idProfundidad) {
        return repository.findByProfundidadParcelaParcelaIdParcelaAndProfundidadParcelaProfundidadIdProfundidad(idParcela, idProfundidad);
    }

}
