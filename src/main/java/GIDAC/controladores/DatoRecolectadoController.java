package GIDAC.controladores;


import GIDAC.modelo.Altura;
import GIDAC.modelo.Area;
import GIDAC.modelo.Conglomerado;
import GIDAC.modelo.Dataset;
import GIDAC.modelo.Dato;
import GIDAC.modelo.DatoRecolectado;
import GIDAC.modelo.EquivalenciaVariable;
import GIDAC.modelo.MapaCoordenadas;
import GIDAC.modelo.MapaProfundidadValor;
import GIDAC.modelo.MapaVariable;
import GIDAC.modelo.UnidadMedida;
import GIDAC.modelo.ModeloDatosAgrupados;
import GIDAC.modelo.Parcela;
import GIDAC.modelo.Perfilado;
import GIDAC.modelo.Profundidad;
import GIDAC.modelo.ProfundidadParcela;
import GIDAC.modelo.ProyectoInvestigacion;
import GIDAC.modelo.ValorPermitido;
import GIDAC.modelo.Variable;
import GIDAC.modelo.VariableUnidadMedida;
import GIDAC.modelo.VariablesEncontradas;
import GIDAC.modelo.modeloDescarga;
import GIDAC.modelo.valoresDescarga;
import GIDAC.servicios.AlturaService;
import GIDAC.servicios.AreaService;
import GIDAC.servicios.ConglomeradoService;
import GIDAC.servicios.DataSetService;
import GIDAC.servicios.DatoRecolectadoService;
import GIDAC.servicios.EquivalenciaVariableService;
import GIDAC.servicios.ParcelaService;
import GIDAC.servicios.ProfundidadParcelaService;
import GIDAC.servicios.ProfundidadService;
import GIDAC.servicios.ValorPermitidoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import jxl.Sheet;
import jxl.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import static org.hibernate.criterion.Projections.id;
import org.springframework.http.ResponseEntity;
import GIDAC.servicios.UsuarioService;
import GIDAC.servicios.UnidadMedidaService;
        

@RestController
@RequestMapping("/dato-recolectado")
@CrossOrigin("*")
public class DatoRecolectadoController {

    
    
    @Autowired
    private DatoRecolectadoService service;
    
    @Autowired
    private EquivalenciaVariableService equivalenciaVariableService;
    
    @Autowired
    private ValorPermitidoService valorPermitidoService;
    
    @Autowired
    private AlturaService alturaService;
    
    @Autowired
    private ConglomeradoService conglomeradoService;
    
    @Autowired
    private ParcelaService parcelaService;
    
    @Autowired
    private ProfundidadParcelaService profundidadParcelaService ;
    
    @Autowired
    private ProfundidadService profundidadService ;
    
    @Autowired
    private DataSetService datasetService;
    
    @Autowired
    private AreaService areaService;
    
    @Autowired
    private UnidadMedidaService medidaService;
    
    //ejemplo .--eliminar
    @Autowired
    private UsuarioService usuarioService;
    

    @PostMapping("/guardar-dato-recolectado")
    public Object guardar(@RequestBody DatoRecolectado oC)
    {
        cValidaciones oV=new cValidaciones();
        oC.setFechaCreacion(oV.fechaActual());
        return service.guardar(oC);    
    }
   
    @GetMapping("/listar-todos-dato")
    public  List<Parcela>  listarTodosLosDatosN(){
        return parcelaService.buscarTodos();
    }
    
    //-----------------------------------------------------------------------------------------------------
    @GetMapping("/listar-todos-datos-catalogo/{id}")
    public  Object  listarTodosLosDatoCatalogo(@PathVariable String id)
    {
        //return service.listarTodosLosDatos();
        System.out.println("-----------------------------------------------------------");
        System.out.println("-----------------------------------------------------------"+id);
        List<Object[]> datos; 
        if(id.equals("0")){
            datos = service.listarTodosLosDatosNumerico();   
        }else{
            datos = service.listarTodosLosDatosVariableNumerico(id);
        }
        // Mapa para almacenar los datos agrupados
        Map<String, Map<String, List<Dato>>> datosAgrupados = new HashMap<>();
        // Agrupar los datos por coordenadas, tipos de variables y profundidades
        for (Object[] dato : datos) {
            String coordenadaX = (String) dato[0];
            String coordenadaY = (String) dato[1];
            Double profundidadMaxima = (Double) dato[2];
            Double profundidadMinima = (Double) dato[3];
            String unidadMedidaVariable = (String) dato[4];
            String unidadMedida = (String) dato[5];
            String tipoVariable = (String) dato[6];
            Double valorAux = (Double) dato[7];
            tipoVariable=tipoVariable+" ("+unidadMedidaVariable+")";
            BigDecimal bd = new BigDecimal(valorAux);
            bd = bd.setScale(3, RoundingMode.HALF_UP);
            valorAux = bd.doubleValue();
            String valor=valorAux.toString();
            String coordenadas = coordenadaX + "," + coordenadaY;
            String profundidades = profundidadMinima+" - "+profundidadMaxima+" "+unidadMedida;
            //double profundidades = (Double.parseDouble(profundidadMinima)+ Double.parseDouble(profundidadMaxima))/2;
            // Verificar si las coordenadas existen en el mapa
            if (!datosAgrupados.containsKey(coordenadas)) {
                datosAgrupados.put(coordenadas, new HashMap<>());
            }
            // Verificar si el tipo de variable existe en el mapa de coordenadas
            Map<String, List<Dato>> variablesMapa = datosAgrupados.get(coordenadas);
            if (!variablesMapa.containsKey(tipoVariable)) {
                variablesMapa.put(tipoVariable, new ArrayList<>());
            }
            // Agregar el dato a la lista correspondiente en el mapa
            List<Dato> listaDatos = variablesMapa.get(tipoVariable);
            listaDatos.add(new Dato(profundidades, valor));
        }
        return ResponseEntity.ok(datosAgrupados);  
    }
    
    @GetMapping("/listar-todos-datos-catalogo-nominal/{id}")
    public  Object  listarTodosLosDatoCatalogoNominal(@PathVariable String id)
    {
        //return service.listarTodosLosDatos();
        System.out.println("-----------------------------------------------------------");
        System.out.println("-----------------------------------------------------------"+id);
        List<Object[]> datos; 
        if(id.equals("0")){
            datos = service.listarTodosLosDatosNominal();   
        }else{
            datos = service.listarTodosLosDatosVariableNominal(id);
        }
        // Mapa para almacenar los datos agrupados
        Map<String, Map<String, List<Dato>>> datosAgrupados = new HashMap<>();
        // Agrupar los datos por coordenadas, tipos de variables y profundidades
        for (Object[] dato : datos) {
            String coordenadaX = (String) dato[0];
            String coordenadaY = (String) dato[1];
            Double profundidadMaxima = (Double) dato[2];
            Double profundidadMinima = (Double) dato[3];
            String unidadMedida = (String) dato[4];
            String tipoVariable = (String) dato[5];
            String valor = (String) dato[6];
            String coordenadas = coordenadaX + "," + coordenadaY;
            String profundidades = profundidadMinima+" - "+profundidadMaxima+" "+unidadMedida;
            //double profundidades = (Double.parseDouble(profundidadMinima)+ Double.parseDouble(profundidadMaxima))/2;
            // Verificar si las coordenadas existen en el mapa
            if (!datosAgrupados.containsKey(coordenadas)) {
                datosAgrupados.put(coordenadas, new HashMap<>());
            }
            // Verificar si el tipo de variable existe en el mapa de coordenadas
            Map<String, List<Dato>> variablesMapa = datosAgrupados.get(coordenadas);
            if (!variablesMapa.containsKey(tipoVariable)) {
                variablesMapa.put(tipoVariable, new ArrayList<>());
            }
            // Agregar el dato a la lista correspondiente en el mapa
            List<Dato> listaDatos = variablesMapa.get(tipoVariable);
            listaDatos.add(new Dato(profundidades, valor));
        }
        
        
        return ResponseEntity.ok(datosAgrupados);  
    }
    
    @GetMapping("/listar-todos-datos-catalogo-unidos/{id}")
    public  Object  listarTodosLosDatoCatalogoUnido(@PathVariable String id)
    {
        
        List<Object[]> datos;
        List<Object[]> datosNominales;
        if(id.equals("0")){
            datos = service.listarTodosLosDatosNumerico();
            datosNominales = service.listarTodosLosDatosNominal();   
        }else{
            datos = service.listarTodosLosDatosVariableNumerico(id);
            datosNominales = service.listarTodosLosDatosVariableNominal(id);
        }
        // Mapa para almacenar los datos agrupados
        Map<String, Map<String, List<Dato>>> datosAgrupados = new HashMap<>();
        // Agrupar los datos por coordenadas, tipos de variables y profundidades
        for (Object[] dato : datos) {
            String coordenadaX = (String) dato[0];
            String coordenadaY = (String) dato[1];
            Double profundidadMaxima = (Double) dato[2];
            Double profundidadMinima = (Double) dato[3];
            String unidadMedidaVariable = (String) dato[4];
            String unidadMedida = (String) dato[5];
            String tipoVariable = (String) dato[6];
            Double valorAux = (Double) dato[7];
            tipoVariable=tipoVariable+" ("+unidadMedidaVariable+")";
            BigDecimal bd = new BigDecimal(valorAux);
            bd = bd.setScale(3, RoundingMode.HALF_UP);
            valorAux = bd.doubleValue();
            String valor=valorAux.toString();
            String coordenadas = coordenadaX + "," + coordenadaY;
            String profundidades = profundidadMinima+" - "+profundidadMaxima+" "+unidadMedida;
            //double profundidades = (Double.parseDouble(profundidadMinima)+ Double.parseDouble(profundidadMaxima))/2;
            // Verificar si las coordenadas existen en el mapa
            if (!datosAgrupados.containsKey(coordenadas)) {
                datosAgrupados.put(coordenadas, new HashMap<>());
            }
            // Verificar si el tipo de variable existe en el mapa de coordenadas
            Map<String, List<Dato>> variablesMapa = datosAgrupados.get(coordenadas);
            if (!variablesMapa.containsKey(tipoVariable)) {
                variablesMapa.put(tipoVariable, new ArrayList<>());
            }
            // Agregar el dato a la lista correspondiente en el mapa
            List<Dato> listaDatos = variablesMapa.get(tipoVariable);
            listaDatos.add(new Dato(profundidades, valor));
        }
        
        for (Object[] dato : datosNominales) {
            String coordenadaX = (String) dato[0];
            String coordenadaY = (String) dato[1];
            Double profundidadMaxima = (Double) dato[2];
            Double profundidadMinima = (Double) dato[3];
            String unidadMedida = (String) dato[4];
            String tipoVariable = (String) dato[5];
            String valor = (String) dato[6];
            String coordenadas = coordenadaX + "," + coordenadaY;
            String profundidades = profundidadMinima+" - "+profundidadMaxima+" "+unidadMedida;
            //double profundidades = (Double.parseDouble(profundidadMinima)+ Double.parseDouble(profundidadMaxima))/2;
            // Verificar si las coordenadas existen en el mapa
            if (!datosAgrupados.containsKey(coordenadas)) {
                datosAgrupados.put(coordenadas, new HashMap<>());
            }
            // Verificar si el tipo de variable existe en el mapa de coordenadas
            Map<String, List<Dato>> variablesMapa = datosAgrupados.get(coordenadas);
            if (!variablesMapa.containsKey(tipoVariable)) {
                variablesMapa.put(tipoVariable, new ArrayList<>());
            }
            // Agregar el dato a la lista correspondiente en el mapa
            List<Dato> listaDatos = variablesMapa.get(tipoVariable);
            listaDatos.add(new Dato(profundidades, valor));
        }
        return ResponseEntity.ok(datosAgrupados);  
    }
    
    //-----------------------------------------------------------------------------------------------------
    
    @GetMapping("/listar-todos-datos/{id}")
    public  Object  listarTodosLosDato(@PathVariable Integer id)
    {
        //return service.listarTodosLosDatos
        
        System.out.println("-------------------------------------------------------llega a listar los datos");
        List<Object[]> datos;
        if(id==0){
            datos = service.listarTodosLosDatosNumerico();   
        }else{
            datos = service.listarTodosLosDatosProyectoNumerico(id);   
        }
        // Mapa para almacenar los datos agrupados
        Map<String, Map<String, List<Dato>>> datosAgrupados = new HashMap<>();
        // Agrupar los datos por coordenadas, tipos de variables y profundidades
        for (Object[] dato : datos) {
            String coordenadaX = (String) dato[0];
            String coordenadaY = (String) dato[1];
            Double profundidadMaxima = (Double) dato[2];
            Double profundidadMinima = (Double) dato[3];
            String unidadMedidaVariable = (String) dato[4];
            String unidadMedida = (String) dato[5];
            String tipoVariable = (String) dato[6];
            Double valorAux = (Double) dato[7];
            tipoVariable=tipoVariable+" ("+unidadMedidaVariable+")";
            BigDecimal bd = new BigDecimal(valorAux);
            bd = bd.setScale(3, RoundingMode.HALF_UP);
            valorAux = bd.doubleValue();
            String valor=valorAux.toString();
            String coordenadas = coordenadaX + "," + coordenadaY;
            String profundidades = profundidadMinima+" - "+profundidadMaxima+" "+unidadMedida;
            //double profundidades = (Double.parseDouble(profundidadMinima)+ Double.parseDouble(profundidadMaxima))/2;
            // Verificar si las coordenadas existen en el mapa
            if (!datosAgrupados.containsKey(coordenadas)) {
                datosAgrupados.put(coordenadas, new HashMap<>());
            }
            // Verificar si el tipo de variable existe en el mapa de coordenadas
            Map<String, List<Dato>> variablesMapa = datosAgrupados.get(coordenadas);
            if (!variablesMapa.containsKey(tipoVariable)) {
                variablesMapa.put(tipoVariable, new ArrayList<>());
            }
            // Agregar el dato a la lista correspondiente en el mapa
            List<Dato> listaDatos = variablesMapa.get(tipoVariable);
            listaDatos.add(new Dato(profundidades, valor));
        }
        return ResponseEntity.ok(datosAgrupados);  
    }
    
    @GetMapping("/listar-todos-datos-nominal/{id}")
    public  Object  listarTodosLosDatoNominal(@PathVariable Integer id)
    {
        //return service.listarTodosLosDatos
        
        System.out.println("-------------------------------------------------------llega a listar los datos");
        List<Object[]> datos;
        if(id==0){
            datos = service.listarTodosLosDatosNominal();   
        }else{
            datos = service.listarTodosLosDatosProyectoNominal(id);   
        }
        // Mapa para almacenar los datos agrupados
        Map<String, Map<String, List<Dato>>> datosAgrupados = new HashMap<>();
        // Agrupar los datos por coordenadas, tipos de variables y profundidades
        for (Object[] dato : datos) {
            String coordenadaX = (String) dato[0];
            String coordenadaY = (String) dato[1];
            Double profundidadMaxima = (Double) dato[2];
            Double profundidadMinima = (Double) dato[3];
            String unidadMedida = (String) dato[4];
            String tipoVariable = (String) dato[5];
            String valor = (String) dato[6];
            String coordenadas = coordenadaX + "," + coordenadaY;
            String profundidades = profundidadMinima+" - "+profundidadMaxima+" "+unidadMedida;
            //double profundidades = (Double.parseDouble(profundidadMinima)+ Double.parseDouble(profundidadMaxima))/2;
            // Verificar si las coordenadas existen en el mapa
            if (!datosAgrupados.containsKey(coordenadas)) {
                datosAgrupados.put(coordenadas, new HashMap<>());
            }
            // Verificar si el tipo de variable existe en el mapa de coordenadas
            Map<String, List<Dato>> variablesMapa = datosAgrupados.get(coordenadas);
            if (!variablesMapa.containsKey(tipoVariable)) {
                variablesMapa.put(tipoVariable, new ArrayList<>());
            }
            // Agregar el dato a la lista correspondiente en el mapa
            List<Dato> listaDatos = variablesMapa.get(tipoVariable);
            listaDatos.add(new Dato(profundidades, valor));
        }
        return ResponseEntity.ok(datosAgrupados);  
    }
    
    @GetMapping("/listar-todos-datos-unidos/{id}")
    public  Object  listarTodosLosDatoUnidos(@PathVariable Integer id)
    {
        List<Object[]> datos;
        List<Object[]> datosNominales;
        
        if(id==0){
            datos = service.listarTodosLosDatosNumerico();
            datosNominales = service.listarTodosLosDatosNominal();   
        }else{
            datos = service.listarTodosLosDatosProyectoNumerico(id);
            datosNominales = service.listarTodosLosDatosProyectoNominal(id);
        }
        System.out.println("----------------------------------------------------------------------");
        System.out.println("id: "+id);
        System.out.println("tamaño a: "+datos.size());
        System.out.println("tamaño a: "+datosNominales.size());
        // Mapa para almacenar los datos agrupados
        Map<String, Map<String, List<Dato>>> datosAgrupados = new HashMap<>();
        // Agrupar los datos por coordenadas, tipos de variables y profundidades
        for (Object[] dato : datos) {
            String coordenadaX = (String) dato[0];
            String coordenadaY = (String) dato[1];
            Double profundidadMaxima = (Double) dato[2];
            Double profundidadMinima = (Double) dato[3];
            String unidadMedidaVariable = (String) dato[4];
            String unidadMedida = (String) dato[5];
            String tipoVariable = (String) dato[6];
            Double valorAux = (Double) dato[7];
            tipoVariable=tipoVariable+" ("+unidadMedidaVariable+")";
            BigDecimal bd = new BigDecimal(valorAux);
            bd = bd.setScale(3, RoundingMode.HALF_UP);
            valorAux = bd.doubleValue();
            String valor=valorAux.toString();
            String coordenadas = coordenadaX + "," + coordenadaY;
            String profundidades = profundidadMinima+" - "+profundidadMaxima+" "+unidadMedida;
            //double profundidades = (Double.parseDouble(profundidadMinima)+ Double.parseDouble(profundidadMaxima))/2;
            // Verificar si las coordenadas existen en el mapa
            if (!datosAgrupados.containsKey(coordenadas)) {
                datosAgrupados.put(coordenadas, new HashMap<>());
            }
            // Verificar si el tipo de variable existe en el mapa de coordenadas
            Map<String, List<Dato>> variablesMapa = datosAgrupados.get(coordenadas);
            if (!variablesMapa.containsKey(tipoVariable)) {
                variablesMapa.put(tipoVariable, new ArrayList<>());
            }
            // Agregar el dato a la lista correspondiente en el mapa
            List<Dato> listaDatos = variablesMapa.get(tipoVariable);
            listaDatos.add(new Dato(profundidades, valor));
            System.out.println("--------------------------------------------------------------------- numerico");
            System.out.println("--------------------------------------------------------------------- llega a los datos "+dato[7]);
        }
        
        for (Object[] dato : datosNominales) {
            String coordenadaX = (String) dato[0];
            String coordenadaY = (String) dato[1];
            Double profundidadMaxima = (Double) dato[2];
            Double profundidadMinima = (Double) dato[3];
            String unidadMedida = (String) dato[4];
            String tipoVariable = (String) dato[5];
            String valor = (String) dato[6];
            String coordenadas = coordenadaX + "," + coordenadaY;
            String profundidades = profundidadMinima+" - "+profundidadMaxima+" "+unidadMedida;
            //double profundidades = (Double.parseDouble(profundidadMinima)+ Double.parseDouble(profundidadMaxima))/2;
            // Verificar si las coordenadas existen en el mapa
            if (!datosAgrupados.containsKey(coordenadas)) {
                datosAgrupados.put(coordenadas, new HashMap<>());
            }
            // Verificar si el tipo de variable existe en el mapa de coordenadas
            Map<String, List<Dato>> variablesMapa = datosAgrupados.get(coordenadas);
            if (!variablesMapa.containsKey(tipoVariable)) {
                variablesMapa.put(tipoVariable, new ArrayList<>());
            }
            // Agregar el dato a la lista correspondiente en el mapa
            List<Dato> listaDatos = variablesMapa.get(tipoVariable);
            listaDatos.add(new Dato(profundidades, valor));
            System.out.println("--------------------------------------------------------------------- nominal");
            System.out.println("--------------------------------------------------------------------- llega a los datos "+dato[6]);
        }
        System.out.println("--------------------------------------------------------------------- nominal");
        return ResponseEntity.ok(datosAgrupados); 
    }
    
    //-----------------------------------------------------------------------------------------------------
    
    @GetMapping("/listar-todos-datos-proyecto-variable/{idProyecto}/{idVariable}")
    public  Object  listarTodosLosDatoProyectoVariable(@PathVariable Integer idProyecto, @PathVariable String idVariable)
    {
        //return service.listarTodosLosDatos
        
        System.out.println("-------------------------------------------------------llega a listar los datos");
        List<Object[]> datos;
        if(idVariable.equals("0")){
            datos = service.listarTodosLosDatosProyectoNumerico(idProyecto);   
        }else{
            datos = service.listarTodosLosDatosProyectoVariableNumerico(idProyecto, idVariable);   
        }
        // Mapa para almacenar los datos agrupados
        Map<String, Map<String, List<Dato>>> datosAgrupados = new HashMap<>();
        // Agrupar los datos por coordenadas, tipos de variables y profundidades
        for (Object[] dato : datos) {
            String coordenadaX = (String) dato[0];
            String coordenadaY = (String) dato[1];
            Double profundidadMaxima = (Double) dato[2];
            Double profundidadMinima = (Double) dato[3];
            String unidadMedidaVariable = (String) dato[4];
            String unidadMedida = (String) dato[5];
            String tipoVariable = (String) dato[6];
            Double valorAux = (Double) dato[7];
            tipoVariable=tipoVariable+" ("+unidadMedidaVariable+")";
            BigDecimal bd = new BigDecimal(valorAux);
            bd = bd.setScale(3, RoundingMode.HALF_UP);
            valorAux = bd.doubleValue();
            String valor=valorAux.toString();
            String coordenadas = coordenadaX + "," + coordenadaY;
            String profundidades = profundidadMinima+" - "+profundidadMaxima+" "+unidadMedida;
            //double profundidades = (Double.parseDouble(profundidadMinima)+ Double.parseDouble(profundidadMaxima))/2;
            // Verificar si las coordenadas existen en el mapa
            if (!datosAgrupados.containsKey(coordenadas)) {
                datosAgrupados.put(coordenadas, new HashMap<>());
            }
            // Verificar si el tipo de variable existe en el mapa de coordenadas
            Map<String, List<Dato>> variablesMapa = datosAgrupados.get(coordenadas);
            if (!variablesMapa.containsKey(tipoVariable)) {
                variablesMapa.put(tipoVariable, new ArrayList<>());
            }
            // Agregar el dato a la lista correspondiente en el mapa
            List<Dato> listaDatos = variablesMapa.get(tipoVariable);
            listaDatos.add(new Dato(profundidades, valor));
        }
        return ResponseEntity.ok(datosAgrupados);  
    }
    
    @GetMapping("/listar-todos-datos-proyecto-variable-nominal/{idProyecto}/{idVariable}")
    public  Object  listarTodosLosDatoProyectoVariableNominal(@PathVariable Integer idProyecto, @PathVariable String idVariable)
    {
        //return service.listarTodosLosDatos
        
        System.out.println("-------------------------------------------------------llega a listar los datos");
        List<Object[]> datos;
        if(idVariable.equals("0")){
            datos = service.listarTodosLosDatosProyectoNominal(idProyecto);   
        }else{
            datos = service.listarTodosLosDatosProyectoVariableNominal(idProyecto, idVariable);   
        }
        // Mapa para almacenar los datos agrupados
        Map<String, Map<String, List<Dato>>> datosAgrupados = new HashMap<>();
        // Agrupar los datos por coordenadas, tipos de variables y profundidades
        for (Object[] dato : datos) {
            String coordenadaX = (String) dato[0];
            String coordenadaY = (String) dato[1];
            Double profundidadMaxima = (Double) dato[2];
            Double profundidadMinima = (Double) dato[3];
            String unidadMedida = (String) dato[4];
            String tipoVariable = (String) dato[5];
            String valor = (String) dato[6];
            String coordenadas = coordenadaX + "," + coordenadaY;
            String profundidades = profundidadMinima+" - "+profundidadMaxima+" "+unidadMedida;
            //double profundidades = (Double.parseDouble(profundidadMinima)+ Double.parseDouble(profundidadMaxima))/2;
            // Verificar si las coordenadas existen en el mapa
            if (!datosAgrupados.containsKey(coordenadas)) {
                datosAgrupados.put(coordenadas, new HashMap<>());
            }
            // Verificar si el tipo de variable existe en el mapa de coordenadas
            Map<String, List<Dato>> variablesMapa = datosAgrupados.get(coordenadas);
            if (!variablesMapa.containsKey(tipoVariable)) {
                variablesMapa.put(tipoVariable, new ArrayList<>());
            }
            // Agregar el dato a la lista correspondiente en el mapa
            List<Dato> listaDatos = variablesMapa.get(tipoVariable);
            listaDatos.add(new Dato(profundidades, valor));
        }
        return ResponseEntity.ok(datosAgrupados);  
    }
    
    @GetMapping("/listar-todos-datos-proyecto-variable-unidos/{idProyecto}/{idVariable}")
    public  Object  listarTodosLosDatoProyectoVariableUnido(@PathVariable Integer idProyecto, @PathVariable String idVariable)
    {
        //return service.listarTodosLosDatos
        
        List<Object[]> datos;
        List<Object[]> datosNominales;
        if(idVariable.equals("0")){
            datos = service.listarTodosLosDatosProyectoNumerico(idProyecto);
            datosNominales = service.listarTodosLosDatosProyectoNominal(idProyecto);
        }else{
            datos = service.listarTodosLosDatosProyectoVariableNumerico(idProyecto, idVariable);   
            datosNominales = service.listarTodosLosDatosProyectoVariableNominal(idProyecto, idVariable);   
        }
        
        // Mapa para almacenar los datos agrupados
        Map<String, Map<String, List<Dato>>> datosAgrupados = new HashMap<>();
        // Agrupar los datos por coordenadas, tipos de variables y profundidades
        for (Object[] dato : datos) {
            String coordenadaX = (String) dato[0];
            String coordenadaY = (String) dato[1];
            Double profundidadMaxima = (Double) dato[2];
            Double profundidadMinima = (Double) dato[3];
            String unidadMedidaVariable = (String) dato[4];
            String unidadMedida = (String) dato[5];
            String tipoVariable = (String) dato[6];
            Double valorAux = (Double) dato[7];
            tipoVariable=tipoVariable+" ("+unidadMedidaVariable+")";
            BigDecimal bd = new BigDecimal(valorAux);
            bd = bd.setScale(3, RoundingMode.HALF_UP);
            valorAux = bd.doubleValue();
            String valor=valorAux.toString();
            String coordenadas = coordenadaX + "," + coordenadaY;
            String profundidades = profundidadMinima+" - "+profundidadMaxima+" "+unidadMedida;
            //double profundidades = (Double.parseDouble(profundidadMinima)+ Double.parseDouble(profundidadMaxima))/2;
            // Verificar si las coordenadas existen en el mapa
            if (!datosAgrupados.containsKey(coordenadas)) {
                datosAgrupados.put(coordenadas, new HashMap<>());
            }
            // Verificar si el tipo de variable existe en el mapa de coordenadas
            Map<String, List<Dato>> variablesMapa = datosAgrupados.get(coordenadas);
            if (!variablesMapa.containsKey(tipoVariable)) {
                variablesMapa.put(tipoVariable, new ArrayList<>());
            }
            // Agregar el dato a la lista correspondiente en el mapa
            List<Dato> listaDatos = variablesMapa.get(tipoVariable);
            listaDatos.add(new Dato(profundidades, valor));
        }
        
        for (Object[] dato : datosNominales) {
            String coordenadaX = (String) dato[0];
            String coordenadaY = (String) dato[1];
            Double profundidadMaxima = (Double) dato[2];
            Double profundidadMinima = (Double) dato[3];
            String unidadMedida = (String) dato[4];
            String tipoVariable = (String) dato[5];
            String valor = (String) dato[6];
            String coordenadas = coordenadaX + "," + coordenadaY;
            String profundidades = profundidadMinima+" - "+profundidadMaxima+" "+unidadMedida;
            //double profundidades = (Double.parseDouble(profundidadMinima)+ Double.parseDouble(profundidadMaxima))/2;
            // Verificar si las coordenadas existen en el mapa
            if (!datosAgrupados.containsKey(coordenadas)) {
                datosAgrupados.put(coordenadas, new HashMap<>());
            }
            // Verificar si el tipo de variable existe en el mapa de coordenadas
            Map<String, List<Dato>> variablesMapa = datosAgrupados.get(coordenadas);
            if (!variablesMapa.containsKey(tipoVariable)) {
                variablesMapa.put(tipoVariable, new ArrayList<>());
            }
            // Agregar el dato a la lista correspondiente en el mapa
            List<Dato> listaDatos = variablesMapa.get(tipoVariable);
            listaDatos.add(new Dato(profundidades, valor));
        }
        return ResponseEntity.ok(datosAgrupados);  
    }
    
    //-----------------------------------------------------------------------------------------------------
    
    @GetMapping("/obtener-dato-recolectado/{id}")
    public Object obtener(@PathVariable Integer id)
    {
        return service.buscarPorId(id);
    }
    
    @GetMapping("/listar-dato-recolectado/por-proyecto/{id}")
    public List<DatoRecolectado> obtenerPorProyecto(@PathVariable Integer id)
    {
        return service.buscarPorVigenciaProyecto(true, id);
    }
    
    @GetMapping("/listar-dato-recolectado")
    public List<DatoRecolectado> listar()
    {
        return service.buscarTodos();
    }
    
    @DeleteMapping("/eliminar-dato-recolectado/{id}")
    public void eliminar(@PathVariable Integer id)
    {
        DatoRecolectado oC=new DatoRecolectado();
        cValidaciones oV=new cValidaciones();
        oC.setFechaActualizacion(oV.fechaActual());
        oC.setVigencia(false);
        service.guardar(oC);    
    }
    
    @GetMapping("/obtener-por-dataset/{id}")
    public List obtenerPorDataset(@PathVariable Integer id)
    {
        return service.buscarPorVigenciaDataset(true, id);
    }
    
    @GetMapping("/actualizar-editables")
    public void actualizarEditables()
    {
        cValidaciones oV=new cValidaciones();
        Date fechaActual=oV.fechaActual();
        List<DatoRecolectado> datoRecolectado=service.buscarPorEditable();
        for(DatoRecolectado dato:datoRecolectado) {
            if(oV.conpararFechaHora(dato.getFechaCreacion(), fechaActual)){
                dato.setEditable(false);
                service.guardar(dato);
            }
        }
    }
    
    //comporbar archivo
    @PostMapping("/comprobar-estado-archivo")
    public boolean comprobarXLS(@RequestParam("proyectoInvestigacion") String datosJson, @RequestParam("file") MultipartFile file) throws JsonProcessingException{
        
        ProyectoInvestigacion oC1 = new ObjectMapper().readValue(datosJson, ProyectoInvestigacion.class);
        
        
        int fila=1;
        int cont=1;
        boolean estado=true;
       
        try{
            InputStream inputStream = file.getInputStream();
            Workbook archivoExcel= Workbook.getWorkbook(inputStream);
            Sheet hoja=archivoExcel.getSheet(0);
            String dato;
            for(int colm=0;colm<16;colm++){
                dato=hoja.getCell(colm,fila).getContents();
                System.out.println(dato);
                switch(cont){
                    case 1:
                        if(compararCadenasCaracteres(dato,"Altitud minima")){estado=true;}
                        else{ estado=false; break;}
                        cont=2;
                        break;
                    case 2:
                        if(compararCadenasCaracteres(dato,"Altitud maxima")){estado=true;}
                        else{ estado=false; break;}
                        cont=3;
                        break;
                    case 3:
                        if(compararCadenasCaracteres(dato,"Unidad de medidad altitud")){estado=true;}
                        else{ estado=false; break;}
                        cont=4;
                        break;
                    case 4:
                        if(compararCadenasCaracteres(dato,"Código conglomerado")){estado=true;}
                        else{ estado=false; break;}
                        cont=5;
                        break;
                    case 5:
                        if(compararCadenasCaracteres(dato,"Nombre conglomerado")){estado=true;}
                        else{ estado=false; break;}
                        cont=6;
                        break;
                    case 6:
                        if(compararCadenasCaracteres(dato,"Sector")){estado=true;}
                        else{ estado=false; break;}
                        cont=7;
                        break;
                    case 7:
                        if(compararCadenasCaracteres(dato,"Código parcela")){estado=true;}
                        else{ estado=false; break;}
                        cont=8;
                        break;
                    case 8:
                        if(compararCadenasCaracteres(dato,"Nombre parcela")){estado=true;}
                        else{ estado=false; break;}
                        cont=9;
                        break;
                    case 9:
                        if(compararCadenasCaracteres(dato,"Coordenada x")){estado=true;}
                        else{ estado=false; break;}
                        cont=10;
                        break;
                    case 10:
                        if(compararCadenasCaracteres(dato,"Coordenada y")){estado=true;}
                        else{ estado=false; break;}
                        cont=11;
                        break;
                    case 11:
                        if(compararCadenasCaracteres(dato,"Area parcela")){estado=true;}
                        else{ estado=false; break;}
                        cont=12;
                        break;
                    case 12:
                        if(compararCadenasCaracteres(dato,"Unidad de medida parcela")){estado=true;}
                        else{ estado=false; break;}
                        cont=13;
                        break;
                    case 13:
                        if(compararCadenasCaracteres(dato,"Profundidad minima")){estado=true;}
                        else{ estado=false; break;}
                        cont=14;
                        break;
                    case 14:
                        if(compararCadenasCaracteres(dato,"Profundidad maxima")){estado=true;}
                        else{ estado=false; break;}
                        cont=15;
                        break;
                    case 15:
                        if(compararCadenasCaracteres(dato,"Unidad de medidad profundidad")){estado=true;}
                        else{ estado=false; break;}
                        cont=16;
                        break;
                    case 16:
                        if(compararCadenasCaracteres(dato,"Fecha inicial")){estado=true;}
                        else{ estado=false; break;}
                        cont=17;
                        break;
                    case 17:
                        if(compararCadenasCaracteres(dato,"Fecha final")){estado=true;}
                        else{ estado=false; break;}
                        cont=1;
                        break;
                    
                }
                if(estado==false) {
                    System.out.println("llega al false-----------------------------------------------");
                    break;
                }
                    
            }
            
            
            archivoExcel.close();
            inputStream.close();
                        
            return estado;
        }catch(Exception e){
            System.out.println("Error "+e);
            return false;
        }
    }
    
    //buscar datos para perfilar
     @PostMapping("/buscar-datos-para-perfilado")
    public List buscarDatosParaPerfilarXLS(@RequestParam("proyectoInvestigacion") String datosJson, @RequestParam("file") MultipartFile file) throws JsonProcessingException{
        
        ProyectoInvestigacion oC1 = new ObjectMapper().readValue(datosJson, ProyectoInvestigacion.class);
        System.out.println("Llega el id proyecto "+oC1.getIdProyecto());
        
        //List<EquivalenciaVariable> listaEquivalencia=equivalenciaVariableService.buscarTodos();
        List<VariablesEncontradas> variablesEncontradas=new ArrayList();
        
        List<Object[]> listaObject= equivalenciaVariableService.listarCatalogoParaPerfilado();
        List<VariablesEncontradas> listaEquivalencia = new ArrayList<>();
        for (Object[] objeto : listaObject) {
            VariablesEncontradas variable = new VariablesEncontradas();
            variable.setIdVariableUnidadMedida((Integer) objeto[0]);
            variable.setIdVariable((String) objeto[1]);
            variable.setNombreVariable((String) objeto[2]);
            variable.setNombreOrganizacion((String) objeto[3]);
            variable.setNombreVariableEspoch((String) objeto[4]);
            variable.setNombreTipoVariable((String) objeto[5]);
            variable.setUnidadMedida((String) objeto[6]);

            listaEquivalencia.add(variable);
        }
        
        int fila=1;
        int numColumnas=0;
        int numFilas=0;
       
        try{
            InputStream inputStream = file.getInputStream();
            Workbook archivoExcel= Workbook.getWorkbook(inputStream);
            Sheet hoja=archivoExcel.getSheet(0);
            numColumnas=hoja.getColumns();
            numFilas=hoja.getRows();
            System.out.println("--------------------------------------------------llegan las columnas: "+numFilas);
            if(numColumnas<=16 || numFilas<=2){
                System.out.println("--------------------------------------------no tiene mas de 15 lineas");
                return null;
                
            }else{
                String dato;
                for(int colm=17;colm<numColumnas;colm++){
                    boolean controladorEncontrado=false;
                    dato=hoja.getCell(colm,fila).getContents();
                    System.out.println("------------------------dato analizar "+dato);
                    for(VariablesEncontradas equivalencia:listaEquivalencia) {
                        if(equivalencia.getNombreTipoVariable().equals("Numérico")){
                            String datoAux="";
                            try{
                                datoAux=hoja.getCell(colm+1,fila).getContents();
                            }catch(Exception E){
                                System.out.println("Carga incorrecta");
                            }
                            if(compararCadenasCaracteres(dato,equivalencia.getNombreVariable()) && compararCadenasCaracteres(datoAux,equivalencia.getUnidadMedida())){
                                VariablesEncontradas variableEncontrada=new VariablesEncontradas();
                                variableEncontrada.setIdVariableUnidadMedida(equivalencia.getIdVariableUnidadMedida());
                                variableEncontrada.setIdVariable(equivalencia.getIdVariable());
                                variableEncontrada.setNombreVariable(equivalencia.getNombreVariable());
                                variableEncontrada.setNombreVariableEspoch(equivalencia.getNombreVariableEspoch());
                                variableEncontrada.setNombreTipoVariable(equivalencia.getNombreTipoVariable());
                                variableEncontrada.setNombreOrganizacion(equivalencia.getNombreOrganizacion());
                                variableEncontrada.setUnidadMedida(equivalencia.getUnidadMedida());
                                variableEncontrada.setNumeroColumna(colm+1);
                                variableEncontrada.setCantidadDatos(numFilas-2);
                                variablesEncontradas.add(variableEncontrada);
                                System.out.println("------------------------llega a ser iguales "+dato);
                                colm=colm+1;
                                controladorEncontrado=true;
                                break;
                            } 
                        }else{
                            if(compararCadenasCaracteres(dato,equivalencia.getNombreVariable())){
                                VariablesEncontradas variableEncontrada=new VariablesEncontradas();
                                variableEncontrada.setIdVariableUnidadMedida(equivalencia.getIdVariableUnidadMedida());
                                variableEncontrada.setIdVariable(equivalencia.getIdVariable());
                                variableEncontrada.setNombreVariable(equivalencia.getNombreVariable());
                                variableEncontrada.setNombreVariableEspoch(equivalencia.getNombreVariableEspoch());
                                variableEncontrada.setNombreTipoVariable(equivalencia.getNombreTipoVariable());
                                variableEncontrada.setNombreOrganizacion(equivalencia.getNombreOrganizacion());
                                variableEncontrada.setUnidadMedida(equivalencia.getUnidadMedida());
                                variableEncontrada.setNumeroColumna(colm+1);
                                variableEncontrada.setCantidadDatos(numFilas-2);
                                variablesEncontradas.add(variableEncontrada);
                                System.out.println("------------------------llega a ser iguales "+dato);
                                controladorEncontrado=true;
                                break;
                            } 
                            
                        }
                    }
                    
                    if(controladorEncontrado==false){
                        for(VariablesEncontradas equivalencia:listaEquivalencia) {
                            if(equivalencia.getNombreTipoVariable().equals("Numérico")){
                                String datoAux="";
                                try{
                                    datoAux=hoja.getCell(colm+1,fila).getContents();
                                }catch(Exception E){
                                    System.out.println("Carga incorrecta");
                                }
                                if(compararCadenasCaracteres(dato,equivalencia.getNombreVariableEspoch()) && compararCadenasCaracteres(datoAux,equivalencia.getUnidadMedida())){
                                    VariablesEncontradas variableEncontrada=new VariablesEncontradas();
                                    variableEncontrada.setIdVariableUnidadMedida(equivalencia.getIdVariableUnidadMedida());
                                    variableEncontrada.setIdVariable(equivalencia.getIdVariable());
                                    variableEncontrada.setNombreVariable(equivalencia.getNombreVariable());
                                    variableEncontrada.setNombreVariableEspoch(equivalencia.getNombreVariableEspoch());
                                    variableEncontrada.setNombreTipoVariable(equivalencia.getNombreTipoVariable());
                                    variableEncontrada.setNombreOrganizacion(equivalencia.getNombreOrganizacion());
                                    variableEncontrada.setUnidadMedida(equivalencia.getUnidadMedida());
                                    variableEncontrada.setNumeroColumna(colm+1);
                                    variableEncontrada.setCantidadDatos(numFilas-2);
                                    variablesEncontradas.add(variableEncontrada);
                                    System.out.println("------------------------llega a ser iguales "+dato);
                                    colm=colm+1;
                                    controladorEncontrado=true;
                                    break;
                                }
                            }else{
                                if(compararCadenasCaracteres(dato,equivalencia.getNombreVariableEspoch())){
                                    VariablesEncontradas variableEncontrada=new VariablesEncontradas();
                                    variableEncontrada.setIdVariableUnidadMedida(equivalencia.getIdVariableUnidadMedida());
                                    variableEncontrada.setIdVariable(equivalencia.getIdVariable());
                                    variableEncontrada.setNombreVariable(equivalencia.getNombreVariable());
                                    variableEncontrada.setNombreVariableEspoch(equivalencia.getNombreVariableEspoch());
                                    variableEncontrada.setNombreTipoVariable(equivalencia.getNombreTipoVariable());
                                    variableEncontrada.setNombreOrganizacion(equivalencia.getNombreOrganizacion());
                                    variableEncontrada.setUnidadMedida(equivalencia.getUnidadMedida());
                                    variableEncontrada.setNumeroColumna(colm+1);
                                    variableEncontrada.setCantidadDatos(numFilas-2);
                                    variablesEncontradas.add(variableEncontrada);
                                    System.out.println("------------------------llega a ser iguales "+dato);
                                    controladorEncontrado=true;
                                    break;
                                } 
                                 
                            }
                        }
                    }
                } 
                System.out.println("--------------------------------------------tiene mas de 15 lienas");
                return variablesEncontradas;
            }
        }catch(Exception e){
            System.out.println("Error "+e);
            return null;
        }
    }
    
    
     public static boolean compararCadenasCaracteres(String cadena1, String cadena2) {
        String formattedCadena1 = cadena1.replaceAll("\\s", "").toLowerCase();
        String formattedCadena2 = cadena2.replaceAll("\\s", "").toLowerCase();
        return formattedCadena1.equals(formattedCadena2);
    }

    //perfilar datos
    @PostMapping("/perfilar-datos")
    public List PerfilarXLS(@RequestParam("proyectoInvestigacion") String datosJson, @RequestParam("variablesEncontradas") String datosJsonVariables, @RequestParam("file") MultipartFile file) throws JsonProcessingException{
        
        ObjectMapper objectMapper=new ObjectMapper();
        ProyectoInvestigacion oC1 = new ObjectMapper().readValue(datosJson, ProyectoInvestigacion.class);
        List<EquivalenciaVariable> listaEquivalencia=equivalenciaVariableService.buscarTodos();
        List<VariablesEncontradas> variablesEncontradas = objectMapper.readValue(datosJsonVariables, new TypeReference<List<VariablesEncontradas>>() {});
        
        List<Perfilado> perfilado=new ArrayList();
        
        int numColumnas=0;
        int numFilas=0;
       
        try{
            InputStream inputStream = file.getInputStream();
            Workbook archivoExcel= Workbook.getWorkbook(inputStream);
            Sheet hoja=archivoExcel.getSheet(0);
            numColumnas=hoja.getColumns();
            numFilas=hoja.getRows();
            String dato;
            for(VariablesEncontradas variableEncontrada:variablesEncontradas) {
                Perfilado perfil=new Perfilado();
                
                perfil.setIdVariable(variableEncontrada.getIdVariable());
                perfil.setNombreVariable(variableEncontrada.getNombreVariable());
                perfil.setNombreVariableEspoch(variableEncontrada.getNombreVariableEspoch());
                perfil.setNombreTipoVariable(variableEncontrada.getNombreTipoVariable());
                
                perfil.setNumeroColumna(variableEncontrada.getNumeroColumna());
                perfil.setCantidadDato(numFilas-2);
                //aqui guardar datos
                
                int contadorRestantes=0;
                List<ValorPermitido> valoresPermitidos=valorPermitidoService.obtenerPorVariableUnidadMedida(variableEncontrada.getIdVariableUnidadMedida());
                
                int contadorFaltantes=0;
                int contadorFueraRango=0;
                int contadorRepetidos=0;
                for(int fila=2;fila<numFilas;fila++){
                    
                    dato=hoja.getCell(variableEncontrada.getNumeroColumna()-1,fila).getContents();
                    if(dato.equals("")){
                        contadorFaltantes++;
                    }else{
                        boolean aux=false;
                        if(compararCadenasCaracteres(variableEncontrada.getNombreTipoVariable(),"Nominal")){
                            for(ValorPermitido valor:valoresPermitidos){
                                if(compararCadenasCaracteres(valor.getValorPermitido(),dato)){
                                    aux=true;
                                    contadorRestantes++;
                                    break;
                                }
                            }
                            if(aux==false){
                                contadorFueraRango++;
                                //aqui guardar la columna con problema
                            }
                        }else{
                            dato=dato.replaceAll("\\,",".");
                            float numeroFloat = Float.parseFloat(dato);
                            for(ValorPermitido valor:valoresPermitidos){
                                if(numeroFloat>=valor.getValorMinimo() && numeroFloat<=valor.getValorMaximo()){
                                    aux=true;
                                    contadorRestantes++;
                                    break;
                                }
                            }
                            if(aux==false){
                                contadorFueraRango++;
                                //aqui guardar la columna con problema
                            }
                        }
                    }
                    
                }
                perfil.setCantidadDatosCorrectos(contadorRestantes);
                perfil.setCantidadFueraRanngo(contadorFueraRango);
                perfil.setCantidadNulos(contadorFaltantes); 
                perfilado.add(perfil);
            }
        }catch(Exception e){
            System.out.println("Error "+e);
            return null;
        }
        return perfilado;
    }
    
    public boolean compararTextos(String texto1, String texto2) {
        return texto1.equalsIgnoreCase(texto2);
    }
    
    //altura
    private float alturaMaxima=0;
    private float alturaMinima=0;
    private String unidadMedidaAltura="";
    
    //conglomerado
    private String codigoConglomerado="";
    private String nombreConglomerado="";
    private String sector="";
    
    //parcela
    private String codigoParcela;
    private String nombreParcela;
    private float areaParcela;
    private String coordenadaX;
    private String coordenadaY;
    private String sistemaCoordenada;
    private String unidadMedidaArea;
    
    //purfundidad
    private Double profundidadMinima;
    private Double profundidadMaxima;
    private String unidadMedidaProfundidad;
    
    //dataset
    private Date fechaInicio;
    private Date fechaFin;
    
    private String valor;
    private boolean vigencia=true;
    
    
    
    
    
    
    //registrar datos
    @PostMapping("/registrar-datos-xls")
    public void registrarXLS(@RequestParam("proyectoInvestigacion") String datosJson, @RequestParam("variablesEncontradas") String datosJsonVariables, @RequestParam("file") MultipartFile file) throws JsonProcessingException{
        
        
        
        ObjectMapper objectMapper=new ObjectMapper();
        ProyectoInvestigacion oC1Aux = new ObjectMapper().readValue(datosJson, ProyectoInvestigacion.class);
        List<VariablesEncontradas> variablesEncontradas = objectMapper.readValue(datosJsonVariables, new TypeReference<List<VariablesEncontradas>>() {});
        ProyectoInvestigacion oC1=new ProyectoInvestigacion();
        
        oC1.setIdProyecto(oC1Aux.getIdProyecto());
        
        System.out.println("....................................................");
        System.out.println("proy: "+oC1.getIdProyecto());
        List<Perfilado> perfilado=new ArrayList();
        
        int numColumnas=0;
        int numFilas=0;
        //------------------------------------------------------------------
        int l=1;
        cValidaciones oF=new cValidaciones();
        int cont=1;
        
        int controlModulos=1;
        
       
        try{
            InputStream inputStream = file.getInputStream();
            Workbook archivoExcel= Workbook.getWorkbook(inputStream);
            
            for(int hojas=0;hojas<archivoExcel.getNumberOfSheets();hojas++){
                Sheet hoja=archivoExcel.getSheet(hojas);
                
                numColumnas=hoja.getColumns();
                numFilas=hoja.getRows();
                
                String dato;
                
                for(int fila=2;fila<numFilas;fila++){
                    
                    int controlColumnas=1;
                    Altura altura=new Altura();
                    Conglomerado conglomerado=new Conglomerado(); 
                    Area area=new Area();
                    Parcela parcela=new Parcela();
                    Profundidad profundidad=new Profundidad();
                    ProfundidadParcela profundidadParcela=new ProfundidadParcela();
                    Dataset dataset=new Dataset();
                    Variable variable=new Variable();
                    VariableUnidadMedida variableUnidadMedida=new VariableUnidadMedida();
                    
                
                    for(int colm=0;colm<numColumnas;colm++){
                        dato=hoja.getCell(colm,fila).getContents();
                        System.out.println("dato columna: --------------------   "+dato);
                        System.out.println("......................"+controlColumnas);
                        switch(controlColumnas){
                            
                            case 1:
                                if(dato.equals("")){alturaMinima=0;}
                                else{dato=dato.replaceAll("\\,","."); alturaMinima=Float.parseFloat(dato);}
                                controlColumnas=2;
                                break;
                            case 2:
                                if(dato.equals("")){alturaMaxima=0;}
                                else{dato=dato.replaceAll("\\,","."); alturaMaxima=Float.parseFloat(dato);}
                                controlColumnas=3;
                                break;
                            case 3:
                                if(dato.equals("")){unidadMedidaAltura="NA";}
                                else{ unidadMedidaAltura=dato.replaceAll("\\,",".");}
                                
                                if(alturaService.buscarPorAlturaMinimaAlturaMaximaAbreviatura(alturaMinima, alturaMaxima, unidadMedidaAltura)==null){
                                    
                                    UnidadMedida medida=(UnidadMedida) medidaService.buscarPorAbreviatura(dato);
                                    altura.setAlturaMaxima(alturaMaxima);
                                    altura.setAlturaMinima(alturaMinima);
                                    altura.setUnidadMedida(medida);
                                    altura=(Altura) alturaService.guardar(altura);                                   
                                    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++guarda");
                                }else{
                                    altura=(Altura) alturaService.buscarPorAlturaMinimaAlturaMaximaAbreviatura(alturaMinima, alturaMaxima, unidadMedidaAltura);
                                }
                                controlColumnas=4;
                                break;
                            case 4:
                                if(dato.equals("")){codigoConglomerado="NA";}
                                else{ codigoConglomerado=dato;}
                                controlColumnas=5;
                                break;
                            case 5:
                                if(dato.equals("")){nombreConglomerado="NA";}
                                else{ nombreConglomerado=dato;}
                                controlColumnas=6;
                                break;
                            case 6:
                                if(dato.equals("")){sector="NA";}
                                else{ sector=dato;}
                                //if(conglomeradoService.buscarPorCodigoConglomeradoProyectoInvestigacionAlturaMaximaAlturaMinima(codigoConglomerado, oC1.getIdProyecto(), alturaMinima, alturaMaxima)!=null){
                                if(conglomeradoService.buscarPorCodigoConglomeradoProyectoInvestigacion(codigoConglomerado, oC1.getIdProyecto())!=null){
                                    conglomerado=(Conglomerado) conglomeradoService.buscarPorCodigoConglomeradoProyectoInvestigacion(codigoConglomerado, oC1.getIdProyecto());
                                }else{
                                    
                                    conglomerado.setCodigoConglomerado(codigoConglomerado);
                                    conglomerado.setNombreConglomerado(nombreConglomerado);
                                    conglomerado.setProyectoInvestigacion(oC1);
                                    conglomerado.setSector(sector);
                                    conglomerado.setAltura(altura);
                                    conglomerado=(Conglomerado) conglomeradoService.guardar(conglomerado);
                                    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++guarda");
                                }
                                controlColumnas=7;
                                break;
                            case 7:
                                if(dato.equals("")){codigoParcela="NA";}
                                else{ codigoParcela=dato;}
                                controlColumnas=8;
                                break;
                            case 8:
                                if(dato.equals("")){nombreParcela="NA";}
                                else{ nombreParcela=dato;}
                                controlColumnas=9;
                                break;
                            case 9:
                                if(dato.equals("")){coordenadaX="NA";}
                                else{coordenadaX=dato.replaceAll("\\,",".");}
                                controlColumnas=10;
                                break;
                            case 10:
                                if(dato.equals("")){coordenadaY="NA";}
                                else{coordenadaY=dato.replaceAll("\\,",".");}
                                controlColumnas=11;
                                break;
                            case 11:
                                if(dato.equals("")){areaParcela=0;}
                                else{dato=dato.replaceAll("\\,","."); areaParcela=Float.parseFloat(dato);}
                                controlColumnas=12;
                                break;
                            case 12:
                                if(dato.equals("")){unidadMedidaArea="NA";}
                                else{ unidadMedidaArea=dato.replaceAll("\\,",".");}
                                
                                if(areaService.buscarPorAreaAbreviatura(areaParcela, unidadMedidaArea)!=null){
                                    area=(Area) areaService.buscarPorAreaAbreviatura(areaParcela, unidadMedidaArea);
                                }else{
                                    UnidadMedida medida=(UnidadMedida) medidaService.buscarPorAbreviatura(unidadMedidaArea);
                                    area.setArea(areaParcela);
                                    area.setUnidadMedida(medida);
                                    areaService.guardar(area);
                                    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++guarda area");
                                }
                                
                                if(parcelaService.buscarPorCodigoParcelaConglomerado(codigoParcela, conglomerado.getIdConglomerado())!=null){
                                    parcela=(Parcela) parcelaService.buscarPorCodigoParcelaConglomerado(codigoParcela, conglomerado.getIdConglomerado());
                                }else{
                                    parcela.setCodigoParcela(codigoParcela);
                                    parcela.setNombreParcela(nombreParcela);
                                    parcela.setConglomerado(conglomerado);
                                    parcela.setCoordenadaX(coordenadaX);
                                    parcela.setCoordenadaY(coordenadaY);
                                    parcela.setArea(area);
                                    parcela=(Parcela) parcelaService.guardar(parcela);
                                    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++guarda parcela");
                                }
                                controlColumnas=13;
                                break;
                            case 13:
                                if(dato.equals("")){profundidadMinima=0.0;}
                                else{dato=dato.replaceAll("\\,",".");profundidadMinima=Double.parseDouble(dato);}
                                controlColumnas=14;
                                break;
                            case 14:
                                if(dato.equals("")){profundidadMaxima=0.0;}
                                else{dato=dato.replaceAll("\\,",".");profundidadMaxima=Double.parseDouble(dato); }
                                controlColumnas=15;
                                break;
                            case 15:
                                if(dato.equals("")){unidadMedidaProfundidad="NA";}
                                else{ unidadMedidaProfundidad=dato.replaceAll("\\,",".");}
                                
                                if(profundidadService.buscarPorProfundidadMinimaProfundidadMaximaAbreviatura(profundidadMinima, profundidadMaxima, unidadMedidaProfundidad)!=null){
                                    profundidad=(Profundidad) profundidadService.buscarPorProfundidadMinimaProfundidadMaximaAbreviatura(profundidadMinima, profundidadMaxima, unidadMedidaProfundidad);
                                }else{
                                   UnidadMedida medida=(UnidadMedida) medidaService.buscarPorAbreviatura(unidadMedidaProfundidad);
                                   profundidad.setUnidadMedida(medida);
                                   profundidad.setProfundidadMaxima(profundidadMaxima);
                                   profundidad.setProfundidadMinima(profundidadMinima);
                                   profundidad=(Profundidad) profundidadService.guardar(profundidad);
                                   System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++guarda profundidad");
                                }
                                
                                if(profundidadParcelaService.buscarPorParcelaProfundidad(parcela.getIdParcela(), profundidad.getIdProfundidad())!=null){
                                    dataset=(Dataset) datasetService.buscarPorParcelaProfundidad(parcela.getIdParcela(), profundidad.getIdProfundidad());
                                }else{
                                    profundidadParcela.setIdParcela(parcela.getIdParcela());
                                    profundidadParcela.setIdProfundidad(profundidad.getIdProfundidad());
                                    profundidadParcela.setParcela(parcela);
                                    profundidadParcela.setProfundidad(profundidad);
                                    profundidadParcela=(ProfundidadParcela) profundidadParcelaService.guardar(profundidadParcela);
                                }
                                controlColumnas=16;
                                break;
                            case 16:
                                if(dato.equals("")){fechaInicio=oF.fechaDumi();}
                                else{ fechaInicio=oF.Fecha(dato);}
                                controlColumnas=17;
                                break;
                            case 17:
                                if(dato.equals("")){fechaFin=oF.fechaDumi();}
                                else{ fechaFin=oF.Fecha(dato);}
                                if(datasetService.buscarPorParcelaProfundidad(parcela.getIdParcela(), profundidad.getIdProfundidad())!=null){
                                    dataset=(Dataset) datasetService.buscarPorParcelaProfundidad(parcela.getIdParcela(), profundidad.getIdProfundidad());
                                }else{
                                    dataset.setProfundidadParcela(profundidadParcela);
                                    dataset.setFechaFin(fechaFin);
                                    dataset.setFechaInicio(fechaInicio);
                                    dataset.setProyectoInvestigacion(oC1);
                                    dataset=(Dataset) datasetService.guardar(dataset);
                                    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++guarda dataset" );
                                }
                                controlColumnas=18;
                                break;
                            case 18:
                                for(VariablesEncontradas variableEncontrada:variablesEncontradas) {
                                    
                                    int auxCol=colm+1;
                                        System.out.println("------------------------------------------------------------------columna: "+colm);
                                        System.out.println("------------------------------------------------------------------columna mas 1 : "+auxCol);
                                        System.out.println("------------------------------------------------------------------columna de la variable: "+variableEncontrada.getNumeroColumna());
                                    if((auxCol)==variableEncontrada.getNumeroColumna()){
                                        DatoRecolectado datoRecolectado=new DatoRecolectado(); 
                                        System.out.println("-------------------------------------------------------------------------------------");
                                        System.out.println("------------------------------------------------------------------columna registrada");
                                        System.out.println("------------------------------------------------------------------columna: "+auxCol);
                                        if(dato.equals("")){valor="NA";}
                                        else{dato=dato.replaceAll("\\,","."); valor=dato;}
                                        variableUnidadMedida.setIdVariableUnidadMedida(variableEncontrada.getIdVariableUnidadMedida());
                                        //variable.setIdVariable(variableEncontrada.getIdVariable());
                                        datoRecolectado.setDataset(dataset);
                                        datoRecolectado.setValor(valor);
                                        datoRecolectado.setVariableUnidadMedida(variableUnidadMedida);
                                        //datoRecolectado.setVariable(variable);
                                        datoRecolectado.setFechaCreacion(oF.fechaActual());
                                        datoRecolectado=(DatoRecolectado) service.guardar(datoRecolectado);
                                    }
                                }
                                break;
                        }
                    }   
                }
             }
            archivoExcel.close();
            inputStream.close();
        }catch(Exception e){
            System.out.println("Error "+e);
        }
        
    }
    
   
   
    //Unir datos para descarga
    @PostMapping("/unir-datos")
    public List unirDatos(@RequestParam("equivalenciasVariables") String datosJsonVariables) throws JsonProcessingException{
        
        ObjectMapper objectMapper=new ObjectMapper();
        List<EquivalenciaVariable> equivalenciasVariables = objectMapper.readValue(datosJsonVariables, new TypeReference<List<EquivalenciaVariable>>() {});
        List<DatoRecolectado> listaCompleta = new ArrayList();
        List<modeloDescarga> modelosDescarga = new ArrayList();
        int aux=0;
        for(EquivalenciaVariable variable:equivalenciasVariables){
            List<DatoRecolectado> listaDatoRecolectadoVariable=service.buscarPorVigenciaVariable(true, variable.getIdVariable());            
            listaCompleta.addAll(listaDatoRecolectadoVariable);
        }
        List<DatoRecolectado> listaCompletaAux=listaCompleta;
        
        
        for(int i=0;i<listaCompleta.size();i++){
            
            DatoRecolectado dato=listaCompletaAux.get(i);
            if(dato.getIdDatoRecolectado()!=-1){
            modeloDescarga modeloDescargaDato=new modeloDescarga();
            List<valoresDescarga> valoresDescarga = new ArrayList();
            
            //aqui se cargan todo los datos de lal datset, parcela, conglomerado, altura
            modeloDescargaDato.setAlturaMinima(dato.getDataset().getProfundidadParcela().getParcela().getConglomerado().getAltura().getAlturaMinima());
            modeloDescargaDato.setAlturaMaxima(dato.getDataset().getProfundidadParcela().getParcela().getConglomerado().getAltura().getAlturaMaxima());
            modeloDescargaDato.setUnidadMedidaAltura(dato.getDataset().getProfundidadParcela().getParcela().getConglomerado().getAltura().getUnidadMedida().getAbreviatura());
            
            modeloDescargaDato.setCodigoConglomerado(dato.getDataset().getProfundidadParcela().getParcela().getConglomerado().getCodigoConglomerado());
            modeloDescargaDato.setNombreConglomerado(dato.getDataset().getProfundidadParcela().getParcela().getConglomerado().getNombreConglomerado());
            modeloDescargaDato.setSector(dato.getDataset().getProfundidadParcela().getParcela().getConglomerado().getSector());
            
            modeloDescargaDato.setCodigoParcel(dato.getDataset().getProfundidadParcela().getParcela().getCodigoParcela());
            modeloDescargaDato.setNombreParcel(dato.getDataset().getProfundidadParcela().getParcela().getNombreParcela());
            modeloDescargaDato.setCoordenadaX(dato.getDataset().getProfundidadParcela().getParcela().getCoordenadaX());
            modeloDescargaDato.setCoordenadaY(dato.getDataset().getProfundidadParcela().getParcela().getCoordenadaY());
            modeloDescargaDato.setAreaParcela(dato.getDataset().getProfundidadParcela().getParcela().getArea().getArea());
            modeloDescargaDato.setUnidadMedidaArea(dato.getDataset().getProfundidadParcela().getParcela().getArea().getUnidadMedida().getAbreviatura());
            
            
            modeloDescargaDato.setProfundidadMinima(dato.getDataset().getProfundidadParcela().getProfundidad().getProfundidadMaxima());
            modeloDescargaDato.setProfundidadMaxima(dato.getDataset().getProfundidadParcela().getProfundidad().getProfundidadMaxima());
            modeloDescargaDato.setUnidadMedidaProfundidad(dato.getDataset().getProfundidadParcela().getProfundidad().getUnidadMedida().getAbreviatura());
            
            
            for(int j=0;j<listaCompletaAux.size();j++){
                
                DatoRecolectado datoAux=listaCompletaAux.get(j);
                if(datoAux.getIdDatoRecolectado()!=-1){
                valoresDescarga valorDescarga=new valoresDescarga();
                if(dato.getDataset().getProfundidadParcela().getIdProfundidad()==datoAux.getDataset().getProfundidadParcela().getIdProfundidad() && dato.getDataset().getProfundidadParcela().getIdParcela()==datoAux.getDataset().getProfundidadParcela().getIdParcela()){
                    valorDescarga.setIdVariable(datoAux.getVariable().getIdVariable());
                    valorDescarga.setNombreVariable(datoAux.getVariable().getNombreVariable());
                    valorDescarga.setValor(datoAux.getValor());
                    valoresDescarga.add(valorDescarga);
                    datoAux.setIdDatoRecolectado(-1);
                    listaCompletaAux.set(j,datoAux);
                    listaCompleta.set(j,datoAux);
                    
                    }
                }
            }
            
            modeloDescargaDato.setValorDescarga(valoresDescarga);
            modelosDescarga.add(modeloDescargaDato);
            }
        }
        
        List<List<Object>> listaDeListas = new ArrayList<>();
        
        for(modeloDescarga mod:modelosDescarga){
            List<Object> lista = new ArrayList<>();
            lista.add(mod.getAlturaMinima());
            lista.add(mod.getAlturaMaxima());
            lista.add(mod.getUnidadMedidaAltura());
            
            lista.add(mod.getCodigoConglomerado());
            lista.add(mod.getNombreConglomerado());
            lista.add(mod.getSector());
            
            lista.add(mod.getCodigoParcel());
            lista.add(mod.getNombreParcel());
            lista.add(mod.getCoordenadaX());
            lista.add(mod.getCoordenadaY());
            lista.add(mod.getAreaParcela());
            lista.add(mod.getUnidadMedidaArea());
            
            lista.add(mod.getProfundidadMinima());
            lista.add(mod.getProfundidadMaxima());
            lista.add(mod.getUnidadMedidaProfundidad());
            
            for(valoresDescarga eq:mod.getValorDescarga()){
                lista.add(eq.getNombreVariable());
                lista.add(eq.getValor());
            }
            listaDeListas.add(lista);
        }
        return listaDeListas;
    }
}
