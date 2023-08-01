package GIDAC.controladores;

import GIDAC.modelo.Familia;
import GIDAC.servicios.FamiliaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/familia")
@CrossOrigin("*")
public class FamiliaController {

    
    @Autowired
    private FamiliaService service;

    @PostMapping("/guardar")
    public Object guardar(@RequestBody Familia oC)
    {
        System.out.println("dato llega---------------------------------------------------------------");
        
        return service.guardar(oC);    
    }
    
    @PutMapping("/actualizar")
    public Object actualizar(@RequestBody Familia oC)
    {
        return service.guardar(oC);    
    }
    
    @GetMapping("/obtener/{id}")
    public Object obtener(@PathVariable Integer id)
    {
        return service.buscarPorId(id);
    }
    
    @GetMapping("/listar")
    public List<Familia> listar()
    {
        return service.buscarTodos();
    }
    
    @GetMapping("/listar-por-categoria/{id}")
    public List<Object[]>listarPorCategoria(@PathVariable Integer id)
    {
        if(id==0){
            return service.buscarPadres();
        }else{
            System.out.println("----------------------------------------------------------------");
            System.out.println("ingresa al 2");
            return service.buscarHijos(id);
        }
        
    }
    
    @GetMapping("/listar-por-id-aux/{id}")
    public List<Object[]>listarPorIdAux(@PathVariable Integer id)
    {
            return service.obtenerPorIdAux(id);
        
    }
    
    @GetMapping("/listar-vigentes")
    public List<Familia> listarVigentes()
    {
        return service.buscarPorVigencia(true);
    }
    
    @GetMapping("/listar-eliminados")
    public List<Familia> listarEliminados()
    {
        return service.buscarPorVigencia(false);
    }
    
    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable Integer id)
    {
        service.eliminar(id);
    }
    
    
    @GetMapping("/hijos-finales")
    public ResponseEntity<List<Familia>> getHijosFinales() {
        List<Familia> hijosFinales = service.findAllFinalChildren();
        for (Familia familia : hijosFinales) {
            familia.setDescripcionCompleta(getDescripcionCompleta(familia));
        }
        return new ResponseEntity<>(hijosFinales, HttpStatus.OK);
    }

    private String getDescripcionCompleta(Familia familia) {
        StringBuilder sb = new StringBuilder();
        sb.append(familia.getDescripcion());
        while (familia.getFamilia() != null) {
            sb.insert(0, " > ");
            sb.insert(0, familia.getFamilia().getDescripcion());
            familia = familia.getFamilia();
        }
        return sb.toString();
        
    }
    
    
    
}
