package GIDAC.controladores;


import GIDAC.modelo.CatalogoEspoch;
import GIDAC.modelo.CatalogoOrganizacion;
import GIDAC.modelo.DatosVariable;
import GIDAC.modelo.EquivalenciaVariable;
import GIDAC.modelo.Familia;
import GIDAC.modelo.ProyectoInvestigacion;
import GIDAC.modelo.UnidadMedida;
import GIDAC.modelo.ValorPermitido;
import GIDAC.modelo.ValorPermitidoUnidadMedida;
import GIDAC.modelo.Variable;
import GIDAC.modelo.VariableFamilia;
import GIDAC.modelo.VariableUnidadMedida;
import GIDAC.modelo.VariablesEncontradas;
import GIDAC.servicios.CatalogoEspochService;
import GIDAC.servicios.CatalogoOrganizacionService;
import GIDAC.servicios.EquivalenciaVariableService;
import GIDAC.servicios.TipoVariableService;
import GIDAC.servicios.UnidadMedidaService;
import GIDAC.servicios.UnidadMedidaVariableService;
import GIDAC.servicios.ValorPermitidoService;
import GIDAC.servicios.VariableFamiliaService;
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
    
    @Autowired
    private UnidadMedidaVariableService unidadMedidaVariableService;
    
    @Autowired
    private UnidadMedidaService unidadMedidaService;
    
    @Autowired
    private VariableFamiliaService variableFamiliaService;
    
    @Autowired
    private EquivalenciaVariableService equivalenciaVariableService;
    
    
    @PostMapping("/guardar-equivalencia-variable")
    public Object guardarEquivalencia(@RequestBody EquivalenciaVariable oC) throws JsonProcessingException{
               
        Variable variable=new Variable();
        if(variableService.buscarPorId(oC.getVariable().getIdVariable())==null){
            variable.setIdVariable(oC.getVariable().getIdVariable());
            variable.setNombreVariable(oC.getVariable().getNombreVariable());
            variable=(Variable) variableService.guardar(variable);
        }
        
        oC.setIdVariable(oC.getVariable().getIdVariable());
        oC.setCodigoVariableEspoch(oC.getCatalogoEspoch().getCodigoVariableEspoch());
        oC.setCodigoVariableOrganizacion(oC.getCatalogoOrganizacion().getCodigoVariableOrganizacion());
        
        return service.guardar(oC);    
    }
    
    @PostMapping("/guardar-datos-variable")
    public Object guardarDatosVariable(@RequestParam("variable") String datosJson, @RequestParam("listaValoresPermitidos") String datosJsonVariables, @RequestParam("listaFamilia") String datosJsonFamilia) throws JsonProcessingException{
        
        ObjectMapper objectMapper=new ObjectMapper();
        Variable variableAux = new ObjectMapper().readValue(datosJson, Variable.class);
        System.out.println("Seraliza variable");
        List<ValorPermitidoUnidadMedida> valoresPermitidos = objectMapper.readValue(datosJsonVariables, new TypeReference<List<ValorPermitidoUnidadMedida>>() {});
        System.out.println("Seraliza valores permitidos");
        List<Familia> familias = objectMapper.readValue(datosJsonFamilia, new TypeReference<List<Familia>>() {});
        System.out.println("Seraliza familia");
       
        
        for(Familia familia:familias) {
            VariableFamilia oVariableFamilia=new VariableFamilia();
            Familia oFamilia=new Familia();
            oFamilia.setIdFamilia(familia.getIdFamilia());
                    
            oVariableFamilia.setFamilia(oFamilia);
            oVariableFamilia.setVariable(variableAux);
            oVariableFamilia.setIdVariable(variableAux.getIdVariable());
            oVariableFamilia.setIdFamilia(oFamilia.getIdFamilia());
            variableFamiliaService.guardar(oVariableFamilia);
        }
        
        if(variableAux.getTipoVariable().getIdTipoVariable()==1){
            for(ValorPermitidoUnidadMedida valorPermitido:valoresPermitidos) {
                VariableUnidadMedida VUM=new VariableUnidadMedida();
                if(unidadMedidaVariableService.buscarPorUnidadMedidaAndVariableAndVigencia(valorPermitido.getIdUnidadMedida(), variableAux.getIdVariable(), true)==null){
                    UnidadMedida unidadMedidaAux=new UnidadMedida();
                    unidadMedidaAux.setIdUnidadMedida(valorPermitido.getIdUnidadMedida());
                    VUM.setVariable(variableAux);
                    VUM.setUnidadMedida(unidadMedidaAux);
                    VUM=(VariableUnidadMedida) unidadMedidaVariableService.guardar(VUM);
                }else{
                    VUM=(VariableUnidadMedida) unidadMedidaVariableService.buscarPorUnidadMedidaAndVariableAndVigencia(valorPermitido.getIdUnidadMedida(), variableAux.getIdVariable(), true);
                }
                ValorPermitido ValorPermitidoGuardar=new ValorPermitido();
                ValorPermitidoGuardar.setVariableUnidadMedida(VUM);
                ValorPermitidoGuardar.setValorMaximo(valorPermitido.getValorMaximo());
                ValorPermitidoGuardar.setValorMinimo(valorPermitido.getValorMinimo());
                ValorPermitidoGuardar.setValorPermitido(valorPermitido.getValorPermitido());
                valorPermitidoService.guardar(ValorPermitidoGuardar);
            }
        }else{
            for(ValorPermitidoUnidadMedida valorPermitido:valoresPermitidos) {
                VariableUnidadMedida VUM=new VariableUnidadMedida();
                if(unidadMedidaVariableService.buscarPorUnidadMedidaAndVariableAndVigencia(0, variableAux.getIdVariable(), true)==null){
                    UnidadMedida unidadMedidaAux=new UnidadMedida();
                    unidadMedidaAux.setIdUnidadMedida(0);
                    VUM.setVariable(variableAux);
                    VUM.setUnidadMedida(unidadMedidaAux);
                    VUM=(VariableUnidadMedida) unidadMedidaVariableService.guardar(VUM);
                }else{
                    VUM=(VariableUnidadMedida) unidadMedidaVariableService.buscarPorUnidadMedidaAndVariableAndVigencia(0, variableAux.getIdVariable(), true);
                }
                ValorPermitido ValorPermitidoGuardar=new ValorPermitido();
                ValorPermitidoGuardar.setVariableUnidadMedida(VUM);
                ValorPermitidoGuardar.setValorMaximo(valorPermitido.getValorMaximo());
                ValorPermitidoGuardar.setValorMinimo(valorPermitido.getValorMinimo());
                ValorPermitidoGuardar.setValorPermitido(valorPermitido.getValorPermitido());
                valorPermitidoService.guardar(ValorPermitidoGuardar);
            }
        }
        return variableService.guardar(variableAux);
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
    
    @GetMapping("/listar-variable-descargar-proyecto/{id}")
    public List<VariablesEncontradas> listarVariablesDescarga(@PathVariable Integer id)
    {
        
        List<Object[]> listaObject= equivalenciaVariableService.listarCatalogoParaPerfiladoPorProyecto(id);
        List<VariablesEncontradas> listaEquivalencia = new ArrayList<>();
        for (Object[] objeto : listaObject) {
            VariablesEncontradas variable = new VariablesEncontradas();
            variable.setIdVariableUnidadMedida((Integer) objeto[0]);
            variable.setIdVariable((String) objeto[1]);
            variable.setNombreVariable((String) objeto[2]);
            variable.setNombreOrganizacion((String) objeto[3]);
            variable.setNombreTipoVariable((String) objeto[4]);
            variable.setUnidadMedida((String) objeto[5]);
            listaEquivalencia.add(variable);
        }
        return listaEquivalencia;   
    }
    
    
}
