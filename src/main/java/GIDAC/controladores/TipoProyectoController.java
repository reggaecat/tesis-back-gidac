package GIDAC.controladores;


import GIDAC.modelo.TipoProyecto;
import GIDAC.servicios.TipoProyectoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tipo-proyecto")
@CrossOrigin("*")
public class TipoProyectoController {

    
    @Autowired
    private TipoProyectoService service;

    @PostMapping("/guardar")
    public Object guardar(@RequestBody TipoProyecto oC)
    {
        return service.guardar(oC);    
    }
    
    @PostMapping("/actualizar")
    public Object actualizar(@RequestBody TipoProyecto oC)
    {
        return service.guardar(oC);    
    }
    
    @GetMapping("/obtener/{id}")
    public Object obtener(@PathVariable Integer id)
    {
        return service.buscarPorId(id);
    }
    
    @GetMapping("/listar-vigentes")
    public List<TipoProyecto> listarVigentes()
    {
        return service.buscarTodos(true);
    }
    
    @GetMapping("/listar-eliminados")
    public List<TipoProyecto> listarEliminados()
    {
        return service.buscarTodos(false);
    }
    
    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable Integer id)
    {
        TipoProyecto oC=(TipoProyecto) service.buscarPorId(id);
        oC.setVigencia(false);
        service.guardar(oC);   
    }
    
    @GetMapping("/restablecer/{id}")
    public void restablecer(@PathVariable Integer id)
    {
        TipoProyecto oC=(TipoProyecto) service.buscarPorId(id);
        oC.setVigencia(true);
        service.guardar(oC);  
    }
    
}
