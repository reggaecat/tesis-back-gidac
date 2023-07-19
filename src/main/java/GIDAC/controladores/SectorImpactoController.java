package GIDAC.controladores;


import GIDAC.modelo.SectorImpacto;
import GIDAC.servicios.SectorImpactoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sector-impacto")
@CrossOrigin("*")
public class SectorImpactoController {

    
    @Autowired
    private SectorImpactoService service;

    @PostMapping("/guardar")
    public Object guardar(@RequestBody SectorImpacto oC)
    {
        return service.guardar(oC);    
    }
    
    @PostMapping("/actualizar")
    public Object actualizar(@RequestBody SectorImpacto oC)
    {
        return service.guardar(oC);    
    }
    
    @GetMapping("/obtener/{id}")
    public Object obtener(@PathVariable Integer id)
    {
        return service.buscarPorId(id);
    }
  
    @GetMapping("/listar-vigentes")
    public List<SectorImpacto> listarVigentes()
    {
        return service.buscarTodos(true);
    }
    
    @GetMapping("/listar-eliminados")
    public List<SectorImpacto> listarEliminados()
    {
        return service.buscarTodos(false);
    }
    
    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable Integer id)
    {
        
        SectorImpacto oC=(SectorImpacto) service.buscarPorId(id);
        oC.setVigencia(false);
        service.guardar(oC);  
    }
    
    @GetMapping("/restablecer/{id}")
    public void restablecer(@PathVariable Integer id)
    {
        SectorImpacto oC=(SectorImpacto) service.buscarPorId(id);
        oC.setVigencia(true);
        service.guardar(oC);  
    }
    
    
}
