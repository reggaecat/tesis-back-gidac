package GIDAC.controladores;


import GIDAC.modelo.InstitucionEjecutora;
import GIDAC.servicios.InstitucionEjecutoraService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/institucion-ejecutora")
@CrossOrigin("*")
public class InstitucionEjecutoraController {

    
    @Autowired
    private InstitucionEjecutoraService service;

    @PostMapping("/guardar")
    public Object guardar(@RequestBody InstitucionEjecutora oC)
    {
        return service.guardar(oC);    
    }
    
    @PostMapping("/actualizar")
    public Object actualizar(@RequestBody InstitucionEjecutora oC)
    {
        return service.guardar(oC);    
    }
    
    @GetMapping("/listar")
    public List<InstitucionEjecutora> listar()
    {
        return service.buscarTodos();
    }
    
}
