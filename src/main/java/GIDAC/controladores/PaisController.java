package GIDAC.controladores;


import GIDAC.modelo.Pais;
import GIDAC.servicios.PaisService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pais")
@CrossOrigin("*")
public class PaisController {

    
    @Autowired
    private PaisService service;

    @PostMapping("/guardar")
    public Object guardar(@RequestBody Pais oC)
    {
        return service.guardar(oC);    
    }
    
    @PostMapping("/actualizar")
    public Object actualizar(@RequestBody Pais oC)
    {
        return service.guardar(oC);    
    }
    
    @GetMapping("/obtener/{id}")
    public Object obtener(@PathVariable String id)
    {
        return service.buscarPorId(id);
    }
    
    @GetMapping("/listar")
    public List<Pais> listar()
    {
        return service.buscarTodos();
    }
    
    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable String id)
    {
        service.eliminar(id);
    }
    
    
}
