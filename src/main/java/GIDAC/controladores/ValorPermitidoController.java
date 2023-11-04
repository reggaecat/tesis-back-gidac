package GIDAC.controladores;


import GIDAC.modelo.UnidadMedida;
import GIDAC.modelo.ValorPermitido;
import GIDAC.modelo.ValorPermitidoUnidadMedida;
import GIDAC.modelo.Variable;
import GIDAC.modelo.VariableUnidadMedida;
import GIDAC.servicios.UnidadMedidaVariableService;
import GIDAC.servicios.ValorPermitidoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/valor-permitido")
@CrossOrigin("*")
public class ValorPermitidoController {

    
    @Autowired
    private ValorPermitidoService service;
    
    @Autowired
    private UnidadMedidaVariableService unidadMedidaVariableService;
    

    @PostMapping("/guardar-valor-permitido")
    public Object guardar(@RequestBody ValorPermitido oC)
    {
        return service.guardar(oC);    
    }
    
    @PostMapping("/guardar-datos-valor-permitodo")
    public Object guardarDatosVariable(@RequestParam("variable") String datosJson, @RequestParam("valoresPermitidos") String datosJsonVariables) throws JsonProcessingException{
        
        Variable variableAux = new ObjectMapper().readValue(datosJson, Variable.class);
        ValorPermitidoUnidadMedida valorPermitidoUnidadMedida = new ObjectMapper().readValue(datosJsonVariables, ValorPermitidoUnidadMedida.class);
        
        
        if(variableAux.getTipoVariable().getIdTipoVariable()==1){
                VariableUnidadMedida VUM=new VariableUnidadMedida();
                if(unidadMedidaVariableService.buscarPorUnidadMedidaAndVariableAndVigencia(valorPermitidoUnidadMedida.getIdUnidadMedida(), variableAux.getIdVariable(), true)==null){
                    UnidadMedida unidadMedidaAux=new UnidadMedida();
                    unidadMedidaAux.setIdUnidadMedida(valorPermitidoUnidadMedida.getIdUnidadMedida());
                    VUM.setVariable(variableAux);
                    VUM.setUnidadMedida(unidadMedidaAux);
                    VUM=(VariableUnidadMedida) unidadMedidaVariableService.guardar(VUM);
                }else{
                    VUM=(VariableUnidadMedida) unidadMedidaVariableService.buscarPorUnidadMedidaAndVariableAndVigencia(valorPermitidoUnidadMedida.getIdUnidadMedida(), variableAux.getIdVariable(), true);
                }
                ValorPermitido ValorPermitidoGuardar=new ValorPermitido();
                ValorPermitidoGuardar.setVariableUnidadMedida(VUM);
                ValorPermitidoGuardar.setValorMaximo(valorPermitidoUnidadMedida.getValorMaximo());
                ValorPermitidoGuardar.setValorMinimo(valorPermitidoUnidadMedida.getValorMinimo());
                ValorPermitidoGuardar.setValorPermitido(valorPermitidoUnidadMedida.getValorPermitido());
                service.guardar(ValorPermitidoGuardar);
        }else{
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
                ValorPermitidoGuardar.setValorMaximo(valorPermitidoUnidadMedida.getValorMaximo());
                ValorPermitidoGuardar.setValorMinimo(valorPermitidoUnidadMedida.getValorMinimo());
                ValorPermitidoGuardar.setValorPermitido(valorPermitidoUnidadMedida.getValorPermitido());
                service.guardar(ValorPermitidoGuardar);
        }
        return variableAux;
    }
    
    @GetMapping("/obtener-valor-permitido/{id}")
    public Object obtener(@PathVariable Integer id)
    {
        return service.buscarPorId(id);
    }
    
    @GetMapping("/listar-valor-permitido")
    public List<ValorPermitido> listar()
    {
        return service.buscarTodos();
    }
    
    @DeleteMapping("/eliminar-valor-permitido/{id}")
    public void eliminar(@PathVariable Integer id)
    {
        ValorPermitido valorPermitido=(ValorPermitido) service.buscarPorId(id);
        valorPermitido.setVigencia(false);
        service.guardar(valorPermitido);    
    }
    
    @GetMapping("/listar-valor-permitido/por-variable/{id}")
    public List<ValorPermitido> listarPorVariable(@PathVariable Integer id)
    {
        return service.obtenerPorVariableUnidadMedida(id);
    }
    
    @GetMapping("/por-variable/{id}")
    public List<ValorPermitido> listarPorVariableIdVariable(@PathVariable Integer id)
    {
        return service.listarPorVariable(id);
    }
    
    
}
