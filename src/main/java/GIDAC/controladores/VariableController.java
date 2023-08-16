package GIDAC.controladores;


import GIDAC.modelo.CatalogoOrganizacion;
import GIDAC.modelo.Familia;
import GIDAC.modelo.UnidadMedida;
import GIDAC.modelo.ValorPermitido;
import GIDAC.modelo.ValorPermitidoUnidadMedida;
import GIDAC.modelo.Variable;
import GIDAC.modelo.VariableDifusion;
import GIDAC.modelo.VariableFamilia;
import GIDAC.modelo.VariableUnidadMedida;
import GIDAC.modelo.VariableView;
import GIDAC.modelo.VariablesEncontradas;
import GIDAC.servicios.CatalogoOrganizacionService;
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
@RequestMapping("/variable")
@CrossOrigin("*")
public class VariableController {

    
    @Autowired
    private VariableService service;

    @PostMapping("/guardar-variable")
    public Object guardar(@RequestBody Variable oC)
    {
        return service.guardar(oC);    
    }
    
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
    
    
    @PostMapping("/guardar-datos-variable")
    public Object guardarDatosVariable(@RequestParam("variable") String datosJson,@RequestParam("catalogoOrganizacion") String datosJsonCatalogo, @RequestParam("listaValoresPermitidos") String datosJsonVariables, @RequestParam("listaFamilia") String datosJsonFamilia) throws JsonProcessingException{
        
        ObjectMapper objectMapper=new ObjectMapper();
        Variable variableAux = new ObjectMapper().readValue(datosJson, Variable.class);
        
        System.out.println("Seraliza variable");
        System.out.println("--------------------------------------------------------------------------------");
        CatalogoOrganizacion variableAuxCatalogo = new ObjectMapper().readValue(datosJsonCatalogo, CatalogoOrganizacion.class);
        System.out.println("Seraliza variable");
        System.out.println("--------------------------------------------------------------------------------");
        List<ValorPermitidoUnidadMedida> valoresPermitidos = objectMapper.readValue(datosJsonVariables, new TypeReference<List<ValorPermitidoUnidadMedida>>() {});
        System.out.println("Seraliza valores permitidos");
        System.out.println("--------------------------------------------------------------------------------");
        List<Familia> familias = objectMapper.readValue(datosJsonFamilia, new TypeReference<List<Familia>>() {});
        System.out.println("Seraliza familia");
        System.out.println("--------------------------------------------------------------------------------");
        variableAux=(Variable) service.guardar(variableAux);
        variableAuxCatalogo.setVariable(variableAux);
        catalogoOrganizacionService.guardar(variableAuxCatalogo);
        
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
        return variableAux;
    }
    
    @GetMapping("/listar-variable-descargar-proyecto/{id}/{idOrganizacion}")
    public List<VariablesEncontradas> listarVariablesDescarga(@PathVariable Integer id, @PathVariable Integer idOrganizacion)
    {
        List<Object[]> listaObject;
        if(idOrganizacion==0){
            listaObject= service.listarCatalogoParaPerfiladoPorProyecto(id);
        }else{
            listaObject= service.listarCatalogoParaPerfiladoPorProyectoOganizacion(id,idOrganizacion);
        }
        List<VariablesEncontradas> listaEquivalencia = new ArrayList<>();
        for (Object[] objeto : listaObject) {
            VariablesEncontradas variable = new VariablesEncontradas();
            variable.setIdVariableUnidadMedida((Integer) objeto[0]);
            variable.setIdVariable((Integer) objeto[1]);
            variable.setNombreVariable((String) objeto[2]);
            variable.setNombreOrganizacion((String) objeto[3]);
            variable.setNombreTipoVariable((String) objeto[4]);
            variable.setUnidadMedida((String) objeto[5]);
            listaEquivalencia.add(variable);
        }
        return listaEquivalencia;   
    }
    
    
    @GetMapping("/obtener-variable/{id}")
    public Object obtener(@PathVariable Integer id)
    {
        return service.buscarPorId(id);
    }
    
    @GetMapping("/listar-variable")
    public List<Variable> listar()
    {
        return service.buscarTodos();
    }
    
//    @GetMapping("/listar-variable-completas")
//    public List<VariableView> listarCompletas()
//    {
//        return service.litsarVairbalesCompletas();
//    }
    
    @GetMapping("/listar-variable-completas")
    public List<VariablesEncontradas> listarCompletas()
    {
        
        List<Object[]> listaObject= service.litsarVairbalesCompletas();
        List<VariablesEncontradas> listaEquivalencia = new ArrayList<>();
        for (Object[] objeto : listaObject) {
            VariablesEncontradas variable = new VariablesEncontradas();
            variable.setIdVariable((Integer) objeto[0]);
            variable.setCodigoVariable((String) objeto[1]);
            variable.setNombreVariable((String) objeto[2]);
            variable.setNombreOrganizacion((String) objeto[3]);
            variable.setNombreTipoVariable((String) objeto[4]);
            listaEquivalencia.add(variable);
        }
        return listaEquivalencia;   
    }
    
    @GetMapping("/listar-variable-completas-investigador")
    public List<VariablesEncontradas> listarCompletasInvestigador()
    {
//        v.id_variable, 0
//        v.nombre_variable, 1
//        co.codigo_variable_organizacion, 2
//        co.nombre_variable_organizacion, 3
//        o.siglas, 4
//        tv.nombre_tipo_variable, 5
//        um.abreviatura 6
        List<Object[]> listaObject= service.litsarVairbalesCompletasInvestigador();
        List<VariablesEncontradas> listaEquivalencia = new ArrayList<>();
        for (Object[] objeto : listaObject) {
            VariablesEncontradas variable = new VariablesEncontradas();
            variable.setIdVariable((Integer) objeto[0]);
            //variable.setIdVariable((Integer) objeto[0]);
            variable.setNombreVariable((String) objeto[1]);
            variable.setNombreOrganizacion((String) objeto[4]);
            variable.setNombreTipoVariable((String) objeto[5]);
            variable.setNombreVariableOrganizacion((String) objeto[3]);
            listaEquivalencia.add(variable);
        }
        return listaEquivalencia;   
    }
    
    
    @GetMapping("/listar-variable-incompletas-investigador")
    public List<VariablesEncontradas> listarIncompletas()
    {
        
        List<Object[]> listaObject= service.litsarVairbalesIncompletas();
        List<VariablesEncontradas> listaEquivalencia = new ArrayList<>();
        for (Object[] objeto : listaObject) {
            VariablesEncontradas variable = new VariablesEncontradas();
            variable.setIdVariable((Integer) objeto[0]);
            variable.setNombreVariable((String) objeto[1]);
            variable.setNombreOrganizacion((String) objeto[2]);
            listaEquivalencia.add(variable);
        }
        return listaEquivalencia;   
    }
    
    @GetMapping("/listar-variable-difucion/{id}/{idOrganizacion}")
    public List<VariableDifusion> listarVariablesUsuarioComun(@PathVariable Integer id, @PathVariable Integer idOrganizacion)
    {
        List<Object[]> listaObject= new ArrayList<Object[]>();
        if(id==0){
            if(idOrganizacion==0){
                System.out.println("cargo variable-------------------------------------------------------- asdasdas");
                listaObject= service.litsarVairbalesConDatosSinFamilia();
                System.out.println("cargo variable--------------------------------------------------------  asdasd");
            }else{
                listaObject= service.litsarVairbalesConDatosSinFamiliaOrganizacion(idOrganizacion);
            }
        }else{
            if(idOrganizacion==0){
                listaObject= service.litsarVairbalesConDatosConFiltroFamilia(id);
            }else{
                listaObject= listaObject= service.litsarVairbalesConDatosConFiltroFamiliaOrganizacion(id, idOrganizacion);
            }
        }
        List<VariableDifusion> listaVariables = new ArrayList<>();
        for (Object[] objeto : listaObject) {
            VariableDifusion variable = new VariableDifusion();

            variable.setIdVariable((Integer) objeto[0]);
            variable.setNombreVariable((String) objeto[1]);
            variable.setNombreSIglas((String) objeto[2]);
            variable.setNombreOrganizacion((String) objeto[3]);

            listaVariables.add(variable);
        }
        return listaVariables;   
    }
    
    @GetMapping("/listar-variable-investigador/{idFamilia}/{idProyecto}")
    public List<VariableDifusion> listarVariablesInvestigador(@PathVariable Integer idFamilia,@PathVariable Integer idProyecto )
    {
        List<Object[]> listaObject= new ArrayList<Object[]>();
        if(idFamilia==0){
            listaObject= service.litsarVairbalesConDatosSinFamiliaInvestigador(idProyecto);
        }else{
            listaObject= service.litsarVairbalesConDatosConFiltroFamiliaInvestigador(idFamilia, idProyecto);
        }
        List<VariableDifusion> listaVariables = new ArrayList<>();
        for (Object[] objeto : listaObject) {
            VariableDifusion variable = new VariableDifusion();

            variable.setIdVariable((Integer) objeto[0]);
            variable.setNombreVariable((String) objeto[1]);
            variable.setNombreSIglas((String) objeto[2]);
            variable.setNombreOrganizacion((String) objeto[3]);

            listaVariables.add(variable);
            System.out.println("lllllllllllllllllllllllllllllllllllllllllllllllllllllllllll");
            System.out.println("llega la variable");
        }
        return listaVariables;   
    }
    
    @DeleteMapping("/eliminar-variable/{id}")
    public void eliminar(@PathVariable Integer id)
    {
        service.eliminar(id);
    }
    
    
}
