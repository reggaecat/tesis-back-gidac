package GIDAC.controladores;


import GIDAC.modelo.Organizacion;
import GIDAC.servicios.OrganizacionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/organizacion")
@CrossOrigin("*")
public class OrganizacionController {

    
    @Autowired
    private OrganizacionService service;

    @PostMapping("/guardar")
    public Object guardar(@RequestBody Organizacion oC)
    {
        return service.guardar(oC);    
    }
    
    @PostMapping("/editar")
    public Object editar(@RequestBody Organizacion oC)
    {
        return service.guardar(oC);    
    }
    
    @GetMapping("/obtener/{id}")
    public Object obtener(@PathVariable Integer id)
    {
        return service.buscarPorId(id);
    }
    
    @GetMapping("/listar")
    public List<Organizacion> listar()
    {
        return service.buscarTodos();
    }
    
    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable Integer id)
    {
        service.eliminar(id);
    }
    
    
}
