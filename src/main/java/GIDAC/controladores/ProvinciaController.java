package GIDAC.controladores;


import GIDAC.modelo.Pais;
import GIDAC.modelo.Provincia;
import GIDAC.servicios.PaisService;
import GIDAC.servicios.ProvinciaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/provincia")
@CrossOrigin("*")
public class ProvinciaController {

    
    @Autowired
    private ProvinciaService service;

    @PostMapping("/guardar")
    public Object guardar(@RequestBody Provincia oC)
    {
        return service.guardar(oC);    
    }
    
    @PostMapping("/actualizar")
    public Object actualizar(@RequestBody Provincia oC)
    {
        return service.guardar(oC);    
    }
    
    @GetMapping("/obtener/{id}")
    public Object obtener(@PathVariable String id)
    {
        return service.buscarPorId(id);
    }
    
     @GetMapping("/obtener/por-pais/{id}")
    public Object obtenerPorPais(@PathVariable String id)
    {
        return service.buscarPorPais(id);
    }
    
    @GetMapping("/listar")
    public List<Provincia> listar()
    {
        return service.buscarTodos();
    }
    
    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable String id)
    {
        service.eliminar(id);
    }
    
    
}
