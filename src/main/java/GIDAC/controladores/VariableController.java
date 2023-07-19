package GIDAC.controladores;


import GIDAC.modelo.Variable;
import GIDAC.servicios.VariableService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/variable")
@CrossOrigin("*")
public class VariableController {

    
    @Autowired
    private VariableService service;

    @PostMapping("/guardar-variable")
    public Object guardar(@RequestBody Variable oC)
    {
        return service.guardar(oC);    
    }
    
    @GetMapping("/obtener-variable/{id}")
    public Object obtener(@PathVariable Integer id)
    {
        return service.buscarPorId(id);
    }
    
    @GetMapping("/listar-variable")
    public List<Variable> listar()
    {
        return service.buscarTodos();
    }
    
    @DeleteMapping("/eliminar-variable/{id}")
    public void eliminar(@PathVariable Integer id)
    {
        service.eliminar(id);
    }
    
    
}
