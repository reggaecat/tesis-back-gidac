package GIDAC.controladores;


import GIDAC.modelo.TipoInvestigacion;
import GIDAC.servicios.TipoInvestigacionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tipo-investigacion")
@CrossOrigin("*")
public class TipoInvestigacionController {

    
    @Autowired
    private TipoInvestigacionService service;

    @PostMapping("/guardar")
    public Object guardar(@RequestBody TipoInvestigacion oC)
    {
        return service.guardar(oC);    
    }
    
    @PostMapping("/actualizar")
    public Object actualizar(@RequestBody TipoInvestigacion oC)
    {
        return service.guardar(oC);    
    }
    
    @GetMapping("/obtener/{id}")
    public Object obtener(@PathVariable Integer id)
    {
        return service.buscarPorId(id);
    }
    
    @GetMapping("/listar-vigentes")
    public List<TipoInvestigacion> listarVigentes()
    {
        return service.buscarTodos(true);
    }
    
    @GetMapping("/listar-eliminados")
    public List<TipoInvestigacion> listarEliminados()
    {
        return service.buscarTodos(false);
    }
    
    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable Integer id)
    {
        TipoInvestigacion oC=(TipoInvestigacion) service.buscarPorId(id);
        oC.setVigencia(false);
        service.guardar(oC);  
    }
    
    @GetMapping("/restablecer/{id}")
    public void restablecer(@PathVariable Integer id)
    {
        TipoInvestigacion oC=(TipoInvestigacion) service.buscarPorId(id);
        oC.setVigencia(true);
        service.guardar(oC);  
    }
    
    
}
