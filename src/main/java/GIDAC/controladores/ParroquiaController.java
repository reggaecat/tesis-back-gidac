package GIDAC.controladores;



import GIDAC.modelo.Parroquia;
import GIDAC.servicios.ParroquiaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parroquia")
@CrossOrigin("*")
public class ParroquiaController {

    
    @Autowired
    private ParroquiaService service;

    @PostMapping("/guardar")
    public Object guardar(@RequestBody Parroquia oC)
    {
        return service.guardar(oC);    
    }
    
    @PostMapping("/actualizar")
    public Object actualizar(@RequestBody Parroquia oC)
    {
        return service.guardar(oC);    
    }
    
    @GetMapping("/obtener/{id}")
    public Object obtener(@PathVariable String id)
    {
        return service.buscarPorId(id);
    }
    
     @GetMapping("/obtener/por-canton/{id}")
    public Object obtenerPorCanton(@PathVariable String id)
    {
        return service.buscarPorCanton(id);
    }
    
    @GetMapping("/listar")
    public List<Parroquia> listar()
    {
        return service.buscarTodos();
    }
    
    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable String id)
    {
        service.eliminar(id);
    }
    
    
}
