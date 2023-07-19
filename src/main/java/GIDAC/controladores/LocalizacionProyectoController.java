package GIDAC.controladores;


import GIDAC.modelo.LocalizacionProyecto;
import GIDAC.servicios.LocalizacionProyectoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/localizacion-proyecto")
@CrossOrigin("*")
public class LocalizacionProyectoController {

    
    @Autowired
    private LocalizacionProyectoService service;

    @PostMapping("/guardar")
    public Object guardar(@RequestBody LocalizacionProyecto oC)
    {
        return service.guardar(oC);    
    }
    
    @PostMapping("/actualizar")
    public Object actualizar(@RequestBody LocalizacionProyecto oC)
    {
        return service.guardar(oC);    
    }
    
    @GetMapping("/listar")
    public List<LocalizacionProyecto> listar()
    {
        return service.buscarTodos();
    }
    
    @GetMapping("/listar-por-proyecto/{id}")
    public List<LocalizacionProyecto> listarPorProyecto(@PathVariable Integer id)
    {
        return service.buscarPorProyecto(id);
    }
    
}
