package GIDAC.controladores;


import GIDAC.modelo.CatalogoEspoch;
import GIDAC.modelo.CatalogoOrganizacion;
import GIDAC.modelo.DatosVariable;
import GIDAC.modelo.EquivalenciaVariable;
import GIDAC.modelo.ProyectoInvestigacion;
import GIDAC.modelo.ValorPermitido;
import GIDAC.modelo.Variable;
import GIDAC.modelo.VariablesEncontradas;
import GIDAC.servicios.CatalogoEspochService;
import GIDAC.servicios.CatalogoOrganizacionService;
import GIDAC.servicios.EquivalenciaVariableService;
import GIDAC.servicios.TipoVariableService;
import GIDAC.servicios.ValorPermitidoService;
import GIDAC.servicios.VariableService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/equivalencia-variable")
@CrossOrigin("*")
public class EquivalenciaVariableController {

    
    @Autowired
    private EquivalenciaVariableService service;
    
    @Autowired
    private VariableService variableService;
    
    @Autowired
    private CatalogoEspochService catalogoEspochService;
    
    @Autowired
    private CatalogoOrganizacionService catalogoOrganizacionService;
    
    @Autowired
    private TipoVariableService tipoVariableService;
    
    @Autowired
    private ValorPermitidoService valorPermitidoService;

    @PostMapping("/guardar-equivalencia-variable")
    public Object guardar(@RequestParam("equivalenciaVariables") String datosJson, @RequestParam("listaValoresPermitidos") String datosJsonVariables) throws JsonProcessingException{
        
        ObjectMapper objectMapper=new ObjectMapper();
        EquivalenciaVariable oC = new ObjectMapper().readValue(datosJson, EquivalenciaVariable.class);
        List<ValorPermitido> valoresPermitidos = objectMapper.readValue(datosJsonVariables, new TypeReference<List<ValorPermitido>>() {});
        
        
        CatalogoOrganizacion catalogoOrganizacion=(CatalogoOrganizacion) catalogoOrganizacionService.buscarPorId(oC.getCatalogoOrganizacion().getCodigoVariableOrganizacion());
        
        Variable variable=new Variable();
        variable.setNombreVariable(catalogoOrganizacion.getNombreVariableOrganizacion());
        variable.setTipoVariable(oC.getVariable().getTipoVariable());
        Variable variableAux=(Variable) variableService.guardar(variable);
        
        
        CatalogoEspoch catalogoEspoch=new CatalogoEspoch();
        catalogoEspoch.setNombreVariableEspoch(oC.getCatalogoEspoch().getNombreVariableEspoch());
        CatalogoEspoch catalogoEspochAux=(CatalogoEspoch) catalogoEspochService.guardar(catalogoEspoch);
        
        oC.setVariable(variableAux);
        oC.setCatalogoEspoch(catalogoEspochAux);
        oC.setIdVariable(variableAux.getIdVariable());
        oC.setCodigoVariableEspoch(catalogoEspochAux.getCodigoVariableEspoch());
        oC.setCatalogoOrganizacion(catalogoOrganizacion);
        oC.setCodigoVariableOrganizacion(catalogoOrganizacion.getCodigoVariableOrganizacion());
        
        for(ValorPermitido valorPermitido:valoresPermitidos) {
            valorPermitido.setVariable(variableAux);
            valorPermitidoService.guardar(valorPermitido);
        }
        
        return service.guardar(oC);    
    }
    
    @GetMapping("/listar-equivalencia-variable")
    public List<EquivalenciaVariable> listar()
    {
        /*List<DatosVariable> datosVatiable= new ArrayList();
        List<EquivalenciaVariable> datos=service.buscarTodos();
        for(EquivalenciaVariable oDato:datos) {
            DatosVariable odatosVatiable=new DatosVariable();
            odatosVatiable.setIdVariable(oDato.getIdVariable());
            odatosVatiable.setCodigoVariableEspoch(oDato.getCodigoVariableEspoch());
            odatosVatiable.setCodigoVariableOrganizacion(oDato.getCodigoVariableOrganizacion());
            odatosVatiable.setNombreVariable(oDato.getVariable().getNombreVariable());
            odatosVatiable.setNombreVariableEspoch(oDato.getCatalogoEspoch().getNombreVariableEspoch());
            odatosVatiable.setNombreVariableOrganizacion(oDato.getCatalogoOrganizacion().getNombreVariableOrganizacion());
            Variable oVariable= (Variable) variableService.buscarPorId(oDato.getIdVariable());
            odatosVatiable.setIdTipoVariable( oVariable.getTipoVariable().getIdTipoVariable());
            odatosVatiable.setNombreTipoVariable(oVariable.getTipoVariable().getNombreTipoVariable());
            datosVatiable.add(odatosVatiable);
            
        }*/
        return service.buscarTodos();
    }
    
//    @GetMapping("/listar-equivalencia-variable/por-poyecto/{id}")
//    public List<EquivalenciaVariable> listar(@PathVariable Integer id)
//    {
//        return service.buscarPorProyecto(id);
//    }
    
    
    
}
