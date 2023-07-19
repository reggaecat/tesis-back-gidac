package GIDAC.controladores;


import GIDAC.modelo.Profundidad;
import GIDAC.servicios.ProfundidadService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profundidad")
@CrossOrigin("*")
public class ProfundidadController {

    
    @Autowired
    private ProfundidadService service;

    @PostMapping("/guardar-profundidad")
    public Object guardar(@RequestBody Profundidad oC)
    {
        return service.guardar(oC);    
    }
    
    @GetMapping("/obtener-profundidad/{id}")
    public Object obtener(@PathVariable Integer id)
    {
        return service.buscarPorId(id);
    }
    
    @GetMapping("/listar-profundidad")
    public List<Profundidad> listar()
    {
        return service.buscarTodos();
    }
    
    @DeleteMapping("/eliminar-profundidad/{id}")
    public void eliminar(@PathVariable Integer id)
    {
        service.eliminar(id);
    }
    
    @GetMapping("/obtener-profundidad/por-medida/{id}")
    public List obtenerPorMedida(@PathVariable Integer id)
    {
        return service.buscarPorMedida(id);
    }
    
}
