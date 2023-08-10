package GIDAC.controladores;


import GIDAC.modelo.Variable;
import GIDAC.modelo.VariableDifusion;
import GIDAC.modelo.VariableView;
import GIDAC.modelo.VariablesEncontradas;
import GIDAC.servicios.VariableService;
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
    
    @GetMapping("/obtener-variable/{id}")
    public Object obtener(@PathVariable String id)
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
            variable.setIdVariable((String) objeto[0]);
            variable.setNombreVariable((String) objeto[1]);
            variable.setNombreOrganizacion((String) objeto[2]);
            variable.setNombreTipoVariable((String) objeto[3]);
            listaEquivalencia.add(variable);
        }
        return listaEquivalencia;   
    }
    
    @GetMapping("/listar-variable-completas-investigador")
    public List<VariablesEncontradas> listarCompletasInvestigador()
    {
        
        List<Object[]> listaObject= service.litsarVairbalesCompletasInvestigador();
        List<VariablesEncontradas> listaEquivalencia = new ArrayList<>();
        for (Object[] objeto : listaObject) {
            VariablesEncontradas variable = new VariablesEncontradas();
            variable.setIdVariable((String) objeto[0]);
            variable.setNombreVariable((String) objeto[1]);
            variable.setNombreOrganizacion((String) objeto[2]);
            variable.setNombreTipoVariable((String) objeto[3]);
            variable.setNombreVariableEspoch((String) objeto[4]);
            variable.setUnidadMedida((String) objeto[5]);
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
            variable.setIdVariable((String) objeto[0]);
            variable.setNombreVariable((String) objeto[1]);
            variable.setNombreOrganizacion((String) objeto[2]);
            listaEquivalencia.add(variable);
        }
        return listaEquivalencia;   
    }
    
    @GetMapping("/listar-variable-difucion/{id}")
    public List<VariableDifusion> listarVariablesUsuarioComun(@PathVariable Integer id)
    {
        List<Object[]> listaObject= new ArrayList<Object[]>();
        if(id==0){
            listaObject= service.litsarVairbalesConDatosSinFamilia();
        }else{
            listaObject= service.litsarVairbalesConDatosConFiltroFamilia(id);
        }
        List<VariableDifusion> listaVariables = new ArrayList<>();
        for (Object[] objeto : listaObject) {
            VariableDifusion variable = new VariableDifusion();

            variable.setIdVariable((String) objeto[0]);
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

            variable.setIdVariable((String) objeto[0]);
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
    public void eliminar(@PathVariable String id)
    {
        service.eliminar(id);
    }
    
    
}
