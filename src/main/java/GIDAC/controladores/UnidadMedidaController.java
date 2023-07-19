package GIDAC.controladores;

import GIDAC.modelo.UnidadMedida;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import GIDAC.servicios.UnidadMedidaService;

@RestController
@RequestMapping("/medida")
@CrossOrigin("*")
public class UnidadMedidaController {

    
    @Autowired
    private UnidadMedidaService service;

    @PostMapping("/guardar-medida")
    public Object guardar(@RequestBody UnidadMedida oC)
    {
        return service.guardar(oC);    
    }
    
    @GetMapping("/obtener-medida/{id}")
    public Object obtener(@PathVariable Integer id)
    {
        return service.buscarPorId(id);
    }
    
    @GetMapping("/listar-medida")
    public List<UnidadMedida> listar()
    {
        return service.buscarTodos();
    }
    
    @DeleteMapping("/eliminar-medida/{id}")
    public void eliminar(@PathVariable Integer id)
    {
        service.eliminar(id);
    }
    
    
}
