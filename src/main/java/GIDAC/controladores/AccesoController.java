package GIDAC.controladores;

import GIDAC.modelo.Acceso;
import GIDAC.servicios.AccesoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/acceso")
@CrossOrigin("*")
public class AccesoController {

    
    @Autowired
    private AccesoService service;

    
    @GetMapping("/listar-accesos")
    public List<Acceso> mostrarAccesos()
    {
        return service.findAll();
    }
    
    @GetMapping("/listar-accesos-administrador")
    public List<Acceso> mostrarAccesoAdministrador()
    {
        System.out.println("llega----------------------------------------------------------");
        return service.findAllByRol("ADMINISTRADOR");
    }
    
    @GetMapping("/listar-accesos-director")
    public List<Acceso> mostrarAccesoDirector()
    {
        return service.findAllByRol("DIRECTOR");
    }
    
    @GetMapping("/listar-accesos-investigador")
    public List<Acceso> mostrarAccesoInvestigador()
    {
        return service.findAllByRol("INVESTIGADOR");
    }
    
    
    @GetMapping("/obtener-acceso/{id}")
    public Object obtenerRol(@PathVariable Integer id)
    {
        return service.findById(id);
    }
    @PostMapping("/guardar-acceso")
    public Object guardarAcceso(@RequestBody Acceso oC)
    {
        System.out.println("----------------------------------------------------------------------------");
        System.out.println("llega-----------------------------------------------------------------------");
        cValidaciones validaciones=new cValidaciones();
        oC.setFechaAcceso(validaciones.fechaActual());
        return service.save(oC);    
    }
}
