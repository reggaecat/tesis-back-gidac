package GIDAC.controladores;


import GIDAC.modelo.CatalogoEspoch;
import GIDAC.servicios.CatalogoEspochService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/catalogo-espoch")
@CrossOrigin("*")
public class CatalogoEspochController {

    
    @Autowired
    private CatalogoEspochService service;

    @PostMapping("/guardar-catalogo-espoch")
    public Object guardar(@RequestBody CatalogoEspoch oC)
    {
        return service.guardar(oC);    
    }
    
    @GetMapping("/obtener-catalogo-espoch/{id}")
    public Object obtener(@PathVariable String id)
    {
        return service.buscarPorId(id);
    }
    
    @GetMapping("/listar-catalogo-espoch")
    public List<CatalogoEspoch> listar()
    {
        return service.buscarTodos();
    }
    
    @DeleteMapping("/eliminar-catalogo-espoch/{id}")
    public void eliminar(@PathVariable String id)
    {
        service.eliminar(id);
    }
    
    
}
