package GIDAC.controladores;

import GIDAC.modelo.Familia;
import GIDAC.modelo.UnidadMedida;
import GIDAC.servicios.FamiliaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import GIDAC.servicios.UnidadMedidaService;

@RestController
@RequestMapping("/familia")
@CrossOrigin("*")
public class FamiliaController {

    
    @Autowired
    private FamiliaService service;

    @PostMapping("/guardar")
    public Object guardar(@RequestBody Familia oC)
    {
        return service.guardar(oC);    
    }
    
    @PutMapping("/actualizar")
    public Object actualizar(@RequestBody Familia oC)
    {
        return service.guardar(oC);    
    }
    
    @GetMapping("/obtener/{id}")
    public Object obtener(@PathVariable Integer id)
    {
        return service.buscarPorId(id);
    }
    
    @GetMapping("/listar")
    public List<Familia> listar()
    {
        return service.buscarTodos();
    }
    
    @GetMapping("/listar-vigentes")
    public List<Familia> listarVigentes()
    {
        return service.buscarPorVigencia(true);
    }
    
    @GetMapping("/listar-eliminados")
    public List<Familia> listarEliminados()
    {
        return service.buscarPorVigencia(false);
    }
    
    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable Integer id)
    {
        service.eliminar(id);
    }
    
    
}
