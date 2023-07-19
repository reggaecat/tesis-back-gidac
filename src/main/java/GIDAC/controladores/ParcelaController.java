package GIDAC.controladores;


import GIDAC.modelo.Parcela;
import GIDAC.servicios.ParcelaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parcela")
@CrossOrigin("*")
public class ParcelaController {

    
    @Autowired
    private ParcelaService service;

    @PostMapping("/guardar-parcela")
    public Object guardar(@RequestBody Parcela oC)
    {
        return service.guardar(oC);    
    }
    
    @GetMapping("/obtener-parcela/{id}")
    public Object obtener(@PathVariable Integer id)
    {
        return service.buscarPorId(id);
    }
    
    @GetMapping("/listar-parcela")
    public List<Parcela> listar()
    {
        return service.buscarTodos();
    }
    
    @DeleteMapping("/eliminar-parcela/{id}")
    public void eliminar(@PathVariable Integer id)
    {
        service.eliminar(id);
    }
    
    @GetMapping("/obtener-parcela/por-conglomerado/{id}")
    public List obtenerPorConglomerado(@PathVariable Integer id)
    {
        return service.buscarPorConglomerado(id);
    }
    
    
}
