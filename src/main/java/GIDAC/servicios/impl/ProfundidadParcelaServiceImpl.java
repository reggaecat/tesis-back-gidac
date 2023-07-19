package GIDAC.servicios.impl;


import GIDAC.modelo.ProfundidadParcela;
import GIDAC.repositorios.ProfundidadParcelaRepository;
import GIDAC.servicios.ProfundidadParcelaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ProfundidadParcelaServiceImpl implements ProfundidadParcelaService {

    @Autowired
    private ProfundidadParcelaRepository repository;

    @Override
    public ProfundidadParcela guardar(Object objeto) {
        ProfundidadParcela oA=(ProfundidadParcela) objeto;
        return repository.save(oA);
    }

    @Override
    public List buscarTodos() {
        return repository.findAll();
    }
    
    @Override
    public Object buscarPorParcelaProfundidad(Integer idParcela, Integer idProfundidad) {
        return repository.findByIdParcelaAndIdProfundidad(idParcela, idProfundidad);
    }


}
