package GIDAC.controladores;


import GIDAC.modelo.Conglomerado;
import GIDAC.servicios.ConglomeradoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/conglomerado")
@CrossOrigin("*")
public class ConglomeradoController {

    
    @Autowired
    private ConglomeradoService service;

    @PostMapping("/guardar-conglomerado")
    public Object guardar(@RequestBody Conglomerado oC)
    {
        return service.guardar(oC);    
    }
    
    @GetMapping("/obtener-conglomerado/{id}")
    public Object obtener(@PathVariable Integer id)
    {
        return service.buscarPorId(id);
    }
    
    @GetMapping("/listar-conglomerado")
    public List<Conglomerado> listar()
    {
        return service.buscarTodos();
    }
    
    @DeleteMapping("/eliminar-conglomerado/{id}")
    public void eliminar(@PathVariable Integer id)
    {
        service.eliminar(id);
    }
    
    @GetMapping("/obtener-conglomerado/por-proyecto/{id}")
    public List<Conglomerado> buscarPorProyectoInvestigacion(@PathVariable Integer id){
        return service.buscarPorProyectoInvestigacion(id);
    }
    
}
