package GIDAC.controladores;


import GIDAC.modelo.CatalogoEspoch;
import GIDAC.modelo.CatalogoOrganizacion;
import GIDAC.modelo.EquivalenciaVariable;
import GIDAC.modelo.UnidadMedidaVariable;
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
    public Object guardar(@RequestParam("equivalenciaVariables") UnidadMedidaVariable oC) throws JsonProcessingException{
        oC.setIdVariable(oC.getVariable().getIdVariable());
        oC.setIdUnidadMedida(oC.getUnidadMedida().getIdUnidadMedida());
        return service.guardar(oC);    
    }
    
    @GetMapping("/listar-equivalencia-variable")
    public List<EquivalenciaVariable> listar()
    {    
        return service.buscarTodos();
    }
    
    
    
    
}
