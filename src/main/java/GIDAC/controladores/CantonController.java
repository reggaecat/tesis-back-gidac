package GIDAC.controladores;


import GIDAC.modelo.Canton;
import GIDAC.servicios.CantonService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/canton")
@CrossOrigin("*")
public class CantonController {

    
    @Autowired
    private CantonService service;

    @PostMapping("/guardar")
    public Object guardar(@RequestBody Canton oC)
    {
        return service.guardar(oC);    
    }
    
    @PostMapping("/actualizar")
    public Object actualizar(@RequestBody Canton oC)
    {
        return service.guardar(oC);    
    }
    
    @GetMapping("/obtener/{id}")
    public Object obtener(@PathVariable String id)
    {
        return service.buscarPorId(id);
    }
    
     @GetMapping("/obtener/por-provincia/{id}")
    public Object obtenerPorProvincia(@PathVariable String id)
    {
        return service.buscarPorProvincia(id);
    }
    
    @GetMapping("/listar")
    public List<Canton> listar()
    {
        return service.buscarTodos();
    }
    
    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable String id)
    {
        service.eliminar(id);
    }
    
    
}
