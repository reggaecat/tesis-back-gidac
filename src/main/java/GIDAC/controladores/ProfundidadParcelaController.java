package GIDAC.controladores;


import GIDAC.modelo.ProfundidadParcela;
import GIDAC.servicios.ProfundidadParcelaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profundidad-parcela")
@CrossOrigin("*")
public class ProfundidadParcelaController {

    
    @Autowired
    private ProfundidadParcelaService service;

    @PostMapping("/guardar-profundidad-parcela")
    public Object guardar(@RequestBody ProfundidadParcela oC)
    {
        return service.guardar(oC);    
    }
    
    @GetMapping("/listar-profundidad-parcela")
    public List<ProfundidadParcela> listar()
    {
        return service.buscarTodos();
    }
    
}
