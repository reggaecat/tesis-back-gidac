package GIDAC.controladores;


import GIDAC.modelo.CatalogoEspoch;
import GIDAC.modelo.CatalogoOrganizacion;
import GIDAC.modelo.EquivalenciaVariable;
import GIDAC.modelo.VariableUnidadMedida;
import GIDAC.modelo.ValorPermitido;
import GIDAC.modelo.Variable;
import GIDAC.servicios.UnidadMedidaVariableService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/unidad-medida-variable")
@CrossOrigin("*")
public class UnidadMedidaVariableController {

    
    @Autowired
    private UnidadMedidaVariableService service;
    

    @PostMapping("/guardar-equivalencia-variable")
    public Object guardar(@RequestBody VariableUnidadMedida oC) throws JsonProcessingException{
//        oC.setIdVariable(oC.getVariable().getIdVariable());
//        oC.setIdUnidadMedida(oC.getUnidadMedida().getIdUnidadMedida());
        return service.guardar(oC);    
    }
    
    @PutMapping("/actualizar")
    public Object actualizar(@RequestBody VariableUnidadMedida oC) throws JsonProcessingException{
//        oC.setIdVariable(oC.getVariable().getIdVariable());
//        oC.setIdUnidadMedida(oC.getUnidadMedida().getIdUnidadMedida());
        return service.guardar(oC);    
    }
    
    @GetMapping("/obtener/{id}")
    public Object obtenerVigente(@PathVariable Integer id)
    {    
        return service.buscarPorId(id);
    }
    
    @GetMapping("/listar")
    public List<VariableUnidadMedida> listarVigentes1()
    {    
        return service.buscarTodos(true);
    }
    
    @GetMapping("/listar-equivalencia-variable-vigentes")
    public List<EquivalenciaVariable> listarVigentes()
    {    
        return service.buscarTodos(true);
    }
    
    @GetMapping("/listar-equivalencia-variable-eliminados")
    public List<EquivalenciaVariable> listarEliminados()
    {    
        return service.buscarTodos(false);
    }
    
    
    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable Integer id)
    {   
        VariableUnidadMedida oC=(VariableUnidadMedida) service.buscarPorId(id);
        oC.setVigencia(false);
        service.guardar(oC);
    }
    
    
    
    
    
}
