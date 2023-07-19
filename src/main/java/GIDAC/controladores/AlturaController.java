package GIDAC.controladores;


import GIDAC.modelo.Altura;
import GIDAC.servicios.AlturaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/altura")
@CrossOrigin("*")
public class AlturaController {

    
    @Autowired
    private AlturaService service;

    @PostMapping("/guardar-altura")
    public Object guardar(@RequestBody Altura oC)
    {
        return service.guardar(oC);    
    }
    
     @PostMapping("/actualizar")
    public Object actualizar(@RequestBody Altura oC)
    {
        return service.guardar(oC);    
    }
    
    @GetMapping("/obtener-altura/{id}")
    public Object obtener(@PathVariable Integer id)
    {
        return service.buscarPorId(id);
    }
    
    @GetMapping("/listar-altura")
    public List<Altura> listar()
    {
        return service.buscarTodos();
    }
    
    @DeleteMapping("/eliminar-altura/{id}")
    public void eliminar(@PathVariable Integer id)
    {
        service.eliminar(id);
    }
    
    
}
