package GIDAC.controladores;


import GIDAC.modelo.InstitucionParticipante;
import GIDAC.servicios.InstitucionParticipanteService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/institucion-participante")
@CrossOrigin("*")
public class InstitucionParticipanteController {

    
    @Autowired
    private InstitucionParticipanteService service;

    @PostMapping("/guardar")
    public Object guardar(@RequestBody InstitucionParticipante oC)
    {
        return service.guardar(oC);    
    }
    
    @PostMapping("/actualizar")
    public Object actualizar(@RequestBody InstitucionParticipante oC)
    {
        return service.guardar(oC);    
    }
    
    @GetMapping("/listar")
    public List<InstitucionParticipante> listar()
    {
        return service.buscarTodos();
    }
    
}
