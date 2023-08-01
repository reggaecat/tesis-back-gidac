package GIDAC.controladores;


import GIDAC.modelo.CatalogoEspoch;
import GIDAC.modelo.CatalogoOrganizacion;
import GIDAC.modelo.Organizacion;
import GIDAC.servicios.CatalogoEspochService;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.InputStream;
import java.util.List;
import jxl.Sheet;
import jxl.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalogo-espoch")
@CrossOrigin("*")
public class CatalogoEspochController {

    
    @Autowired
    private CatalogoEspochService service;

    @PostMapping("/guardar-catalogo-espoch")
    public Object guardar(@RequestBody CatalogoEspoch oC)
    {
        return service.guardar(oC);    
    }
    
    @GetMapping("/obtener-catalogo-espoch/{id}")
    public Object obtener(@PathVariable Integer id)
    {
        return service.buscarPorId(id);
    }
    
    @GetMapping("/listar-catalogo-espoch")
    public List<CatalogoEspoch> listar()
    {
        List<CatalogoEspoch> oC= service.buscarTodos();
        oC.sort(Comparator.comparing(CatalogoEspoch::getNombreVariableEspoch));
        return oC;
    }
    
    @DeleteMapping("/eliminar-catalogo-espoch/{id}")
    public void eliminar(@PathVariable Integer id)
    {
        service.eliminar(id);
    }
    
    @PostMapping("/comprobar-archivo")
    public boolean comprobarDataset(@RequestParam("file") MultipartFile file) throws JsonProcessingException{
        
        int fila=0;
        int cont=1;
        boolean estado=true;
       
        try{
            InputStream inputStream = file.getInputStream();
            Workbook archivoExcel= Workbook.getWorkbook(inputStream);
            Sheet hoja=archivoExcel.getSheet(0);
            String dato;
            for(int colm=0;colm<2;colm++){
                dato=hoja.getCell(colm,fila).getContents();
                System.out.println(dato);
                switch(cont){
                    case 1:
                        if(dato.equals("Nombre")){estado=true;}
                        else{ estado=false; break;}
                        cont=2;
                        break;
                    case 2:
                        if(dato.equals("Descripcion")){estado=true;}
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
    
    @PostMapping("/importar-archivo")
    public void importarDataset(@RequestParam("file") MultipartFile file) throws JsonProcessingException{
        
        int cont=1;
        boolean estado=true;
        
        
        String nombre="";
        String descripcion="";
       
        List<CatalogoEspoch> variablesEspoch= service.buscarPorVigencia(true);
        
        try{
            InputStream inputStream = file.getInputStream();
            Workbook archivoExcel= Workbook.getWorkbook(inputStream);
            Sheet hoja=archivoExcel.getSheet(0);
            int numFilas=hoja.getRows();
                
            String dato;
            for(int fila=1;fila<numFilas;fila++){
                CatalogoEspoch oC=new CatalogoEspoch();
            for(int colm=0;colm<2;colm++){
                dato=hoja.getCell(colm,fila).getContents();
                System.out.println(dato);
                switch(cont){
                    case 1:
                        if(dato.equals("")){nombre="NA";}
                        else{ nombre=dato;}
                        cont=2;
                        break;
                    case 2:
                        if(dato.equals("")){descripcion="NA";}
                        else{ descripcion=dato;}
                        String datoMayuscula=nombre.toLowerCase();
                        boolean estadoDato=true;
                        if(nombre.equals("NA")){
                            System.out.println("No tiene la estructura");
                        }else{
                            if(variablesEspoch.size()>0){
                                for (CatalogoEspoch variableEspoch : variablesEspoch) {
                                    String variableElemento = variableEspoch.getNombreVariableEspoch().toLowerCase();
                                    if (variableElemento.equals(datoMayuscula)) {
                                        estadoDato=false;
                                        break;
                                    }
                                }
                            }
                            if(estadoDato==true){
                                oC.setNombreVariableEspoch(nombre);
                                oC.setDescripcion(descripcion);
                                oC.setVigencia(true);
                                service.guardar(oC);
                            }
                        }
                        cont=1;
                        break;
                    }
                }
            }
           archivoExcel.close();
            inputStream.close();
        }catch(Exception e){
            System.out.println("Error "+e);
        }
    }
    
    
}
