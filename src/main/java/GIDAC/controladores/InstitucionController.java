package GIDAC.controladores;


import GIDAC.modelo.Institucion;
import GIDAC.servicios.InstitucionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/institucion")
@CrossOrigin("*")
public class InstitucionController {

    
    @Autowired
    private InstitucionService service;

    @PostMapping("/guardar")
    public Object guardar(@RequestBody Institucion oC)
    {
        return service.guardar(oC);    
    }
    
    @PostMapping("/actualizar")
    public Object actualizar(@RequestBody Institucion oC)
    {
        return service.guardar(oC);    
    }
    
    @GetMapping("/obtener/{id}")
    public Object obtener(@PathVariable Integer id)
    {
        return service.buscarPorId(id);
    }
    
    @GetMapping("/listar")
    public List<Institucion> listar()
    {
        return service.buscarTodos();
    }
    
    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable Integer id)
    {
        service.eliminar(id);
    }
    
    
}
