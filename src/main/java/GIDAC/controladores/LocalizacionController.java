package GIDAC.controladores;


import GIDAC.modelo.ListaCanton;
import GIDAC.modelo.ListaPais;
import GIDAC.modelo.ListaProvincias;
import GIDAC.modelo.Localizacion;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import GIDAC.servicios.LocalizacionService;

@RestController
@RequestMapping("/ubicacion/")
@CrossOrigin("*")
public class LocalizacionController {

    
    @Autowired
    private LocalizacionService service;

    @PostMapping("/guardar-ubicacion")
    public Object guardar(@RequestBody Localizacion oC)
    {
        return service.guardar(oC);    
    }
    
    @GetMapping("/obtener-ubicacion/{id}")
    public Object obtener(@PathVariable Integer id)
    {
        return service.buscarPorId(id);
    }
    
    @GetMapping("/listar-ubicacion-vigente")
    public List<Localizacion> listarVigente()
    {
        return service.buscarVIgencia(true);
    }
    
    @GetMapping("/listar-ubicacion-eliminado")
    public List<Localizacion> listarEliminado()
    {
        return service.buscarVIgencia(false);
    }
    
    @DeleteMapping("/eliminar-ubicacion/{id}")
    public void eliminar(@PathVariable Integer id)
    {
        Localizacion ol=(Localizacion) service.buscarPorId(id);
        ol.setVigencia(false);
        service.guardar(ol);
    }
    
    @GetMapping("/obtener-ubicacion/por-pais/{id}")
    public Object obtenerPorPais(@PathVariable String id)
    {
        return service.buscarPorPais(id);
    }
    
     @GetMapping("/obtener-ubicacion/por-provincia/{id}")
    public Object obtenerPorProvincia(@PathVariable String id)
    {
        return service.buscarPorProvincia(id);
    }
    
     @GetMapping("/obtener-ubicacion/por-canton/{id}")
    public Object obtenerPorCanton(@PathVariable String id)
    {
        return service.buscarPorCanton(id);
    }
    
     @GetMapping("/obtener-ubicacion/por-parroquia/{id}")
    public Object obtenerPorParroquia(@PathVariable String id)
    {
        return service.buscarPorParroquia(id);
    }
    
    
    @GetMapping("/obtener-ubicacion-paises")
    public List<ListaPais> obtenerPaises()
    {
        List<Object[]> datos= service.buscarPaises();
        List<ListaPais> lista=new ArrayList();
        for (Object[] dato : datos) {
            ListaPais ol=new ListaPais();
            ol.setCodigoPais((String) dato[0]);
            ol.setNombrePais((String) dato[1]);
            lista.add(ol);
        }
        return lista;
    }
    
    @GetMapping("/obtener-ubicacion-provincias/{idPais}")
    public List<ListaProvincias> obtenerProvincias(@PathVariable("idPais") String idPais)
    {
        List<Object[]> datos= service.buscarPrvincias(idPais);
        List<ListaProvincias> lista=new ArrayList();
        for (Object[] dato : datos) {
            ListaProvincias ol=new ListaProvincias();
            ol.setCodigoProvincia((String) dato[0]);
            ol.setNombreProvincia((String) dato[1]);
            lista.add(ol);
        }
        return lista;
    }
    
    @GetMapping("/obtener-ubicacion-cantones/{idPais}/{idProv}")
    public List<ListaCanton> obtenerCantones(@PathVariable("idPais") String idPais, @PathVariable("idProv") String idProv)
    {
        
        List<Object[]> datos= service.buscarCantones(idPais, idProv);
        List<ListaCanton> lista=new ArrayList();
        
        for (Object[] dato : datos) {
            ListaCanton ol=new ListaCanton();
            ol.setCodigoCanton((String) dato[0]);
            ol.setNombreCanton((String) dato[1]);
            lista.add(ol);
        }
        return lista;
    }
    
    @GetMapping("/obtener-ubicacion-parroquias/{idPais}/{idProv}/{idCanton}")
    public List<Localizacion> obtenerParroquias(@PathVariable("idPais") String idPais, @PathVariable("idProv") String idProv, @PathVariable("idCanton") String idCanton )
    {
        return service.buscarParroquias(idPais, idProv, idCanton);
    }
    
}
