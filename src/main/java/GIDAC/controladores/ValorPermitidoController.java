package GIDAC.controladores;


import GIDAC.modelo.ValorPermitido;
import GIDAC.servicios.ValorPermitidoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/valor-permitido")
@CrossOrigin("*")
public class ValorPermitidoController {

    
    @Autowired
    private ValorPermitidoService service;

    @PostMapping("/guardar-valor-permitido")
    public Object guardar(@RequestBody ValorPermitido oC)
    {
        return service.guardar(oC);    
    }
    
    @GetMapping("/obtener-valor-permitido/{id}")
    public Object obtener(@PathVariable Integer id)
    {
        return service.buscarPorId(id);
    }
    
    @GetMapping("/listar-valor-permitido")
    public List<ValorPermitido> listar()
    {
        return service.buscarTodos();
    }
    
    @DeleteMapping("/eliminar-valor-permitido/{id}")
    public void eliminar(@PathVariable Integer id)
    {
        service.eliminar(id);
    }
    
    @GetMapping("/listar-valor-permitido/por-variable/{id}")
    public List<ValorPermitido> listarPorVariable(@PathVariable Integer id)
    {
        return service.obtenerPorVariable(id);
    }
    
    
}
