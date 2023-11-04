package GIDAC.controladores;


import GIDAC.modelo.Profundidad;
import GIDAC.servicios.ProfundidadService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profundidad")
@CrossOrigin("*")
public class ProfundidadController {

    
    @Autowired
    private ProfundidadService service;

    @PostMapping("/guardar-profundidad")
    public Object guardar(@RequestBody Profundidad oC)
    {
        cValidaciones oV=new cValidaciones();
        oC.setFechaCreacion(oV.fechaActual());
        return service.guardar(oC);    
    }
    
    @PutMapping("/actualizar-profundidad")
    public Object actualizar(@RequestBody Profundidad oC)
    {
        Profundidad oD=(Profundidad) service.buscarPorId(oC.getIdProfundidad());
        cValidaciones oV=new cValidaciones();
        oC.setFechaCreacion(oD.getFechaCreacion());
        oC.setFechaActualizacion(oV.fechaActual());
        return service.guardar(oC);    
    }
    
    @GetMapping("/actualizar-editable")
    public void actualizarEditable()
    {
        List<Profundidad> listaCompleta=service.buscarTodos();
        List<Object[]> datos=service.obtenerProfundidadesUsadas();
        for (Profundidad lista : listaCompleta) {
            boolean aux=true;
            for (Object[] dato : datos) {
                
                if(lista.getIdProfundidad()==(Integer)dato[0]){
                    lista.setEditable(false);
                    service.guardar(lista);    
                    aux=false;  
                    break;
                }
            }
            if(aux==true){
                if(lista.isEditable()==false){
                    lista.setEditable(true);
                    service.guardar(lista);   
                }
            }
        }
    }
    
    @GetMapping("/obtener-profundidad/{id}")
    public Object obtener(@PathVariable Integer id)
    {
        return service.buscarPorId(id);
    }
    
    @GetMapping("/listar-profundidad")
    public List<Profundidad> listar()
    {
        return service.buscarPorVigencia(true);
    }
    
    @DeleteMapping("/eliminar-profundidad/{id}")
    public void eliminar(@PathVariable Integer id)
    {
        Profundidad oC= (Profundidad) service.buscarPorId(id);
        oC.setVigencia(false);
        cValidaciones oV=new cValidaciones();
        oC.setFechaActualizacion(oV.fechaActual());
        service.guardar(oC);  
    }
    
    @GetMapping("/obtener-profundidad/por-medida/{id}")
    public List obtenerPorMedida(@PathVariable Integer id)
    {
        
        return service.buscarPorMedida(id);
    }
    
}
