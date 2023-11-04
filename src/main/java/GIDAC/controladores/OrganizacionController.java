package GIDAC.controladores;


import GIDAC.modelo.Organizacion;
import GIDAC.servicios.OrganizacionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/organizacion")
@CrossOrigin("*")
public class OrganizacionController {

    
    @Autowired
    private OrganizacionService service;

    @PostMapping("/guardar")
    public Object guardar(@RequestBody Organizacion oC)
    {
        cValidaciones oV=new cValidaciones();
        oC.setFechaCreacion(oV.fechaActual());
        return service.guardar(oC);    
    }
    
    @PutMapping("/actualizar")
    public Object editar(@RequestBody Organizacion oC)
    {
        Organizacion oD=(Organizacion) service.buscarPorId(oC.getIdOrganizacion());
        cValidaciones oV=new cValidaciones();
        oC.setFechaCreacion(oD.getFechaCreacion());
        oC.setFechaActualizacion(oV.fechaActual());
        return service.guardar(oC);    
    }
    
    @GetMapping("/obtener/{id}")
    public Object obtener(@PathVariable Integer id)
    {
        
        return service.buscarPorId(id);
    }
    
    @GetMapping("/listar")
    public List<Organizacion> listar()
    {
        return service.buscarPorVigencia(true);
    }
    
    @GetMapping("/listar-eliminados")
    public List<Organizacion> listarEliminados()
    {
        return service.buscarPorVigencia(false);
    }
    
    
    @DeleteMapping("/reestablecer/{id}")
    public void reestablecer(@PathVariable Integer id)
    {
        Organizacion oC=(Organizacion) service.buscarPorId(id);
        oC.setVigencia(true);
        cValidaciones oV=new cValidaciones();
        oC.setFechaActualizacion(oV.fechaActual());
        service.guardar(oC);    
    }
    
    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable Integer id)
    {
        Organizacion oC=(Organizacion) service.buscarPorId(id);
        cValidaciones oV=new cValidaciones();
        oC.setFechaActualizacion(oV.fechaActual());
        oC.setVigencia(false);
        service.guardar(oC);    
    }
    
    
}
