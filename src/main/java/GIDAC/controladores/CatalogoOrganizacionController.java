package GIDAC.controladores;


import GIDAC.modelo.CatalogoOrganizacion;
import GIDAC.servicios.CatalogoOrganizacionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/catalogo-organizacion")
@CrossOrigin("*")
public class CatalogoOrganizacionController {

    
    @Autowired
    private CatalogoOrganizacionService service;

    @PostMapping("/guardar-catalogo-organizacion")
    public Object guardar(@RequestBody CatalogoOrganizacion oC)
    {
        return service.guardar(oC);    
    }
    
    @GetMapping("/obtener-catalogo-organizacion/{id}")
    public Object obtener(@PathVariable String id)
    {
        return service.buscarPorId(id);
    }
    
    @GetMapping("/listar-catalogo-organizacion")
    public List<CatalogoOrganizacion> listar()
    {
        return service.buscarTodos();
    }
    
    @DeleteMapping("/eliminar-catalogo-organizacion/{id}")
    public void eliminar(@PathVariable String id)
    {
        service.eliminar(id);
    }
    
    
}
