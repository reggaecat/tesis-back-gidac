package GIDAC.controladores;


import GIDAC.modelo.Area;
import GIDAC.servicios.AreaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/area")
@CrossOrigin("*")
public class AreaController {

    
    @Autowired
    private AreaService service;

    @PostMapping("/guardar")
    public Object guardar(@RequestBody Area oC)
    {
        return service.guardar(oC);    
    }
    
    @PostMapping("/actualizar")
    public Object actualizar(@RequestBody Area oC)
    {
        return service.guardar(oC);    
    }
    
    @GetMapping("/obtener/{id}")
    public Object obtener(@PathVariable Integer id)
    {
        return service.buscarPorId(id);
    }
    
    @GetMapping("/listar")
    public List<Area> listar()
    {
        return service.buscarTodos();
    }
    
    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable Integer id)
    {
        service.eliminar(id);
    }
    
    
}
