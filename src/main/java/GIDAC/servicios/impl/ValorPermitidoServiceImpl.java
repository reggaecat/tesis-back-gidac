package GIDAC.servicios.impl;



import GIDAC.modelo.ValorPermitido;
import GIDAC.repositorios.ValorPermitidoRepository;
import GIDAC.servicios.ValorPermitidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ValorPermitidoServiceImpl implements ValorPermitidoService {

    @Autowired
    private ValorPermitidoRepository repository;

    @Override
    public ValorPermitido guardar(Object objeto) {
        ValorPermitido oA=(ValorPermitido) objeto;
        return repository.save(oA);
    }

    @Override
    public ValorPermitido buscarPorId(Integer id) {
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
    public List obtenerPorVariable(Integer id) {
        return repository.findByVariable_IdVariable(id);
    }

}
