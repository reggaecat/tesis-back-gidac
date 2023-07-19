package GIDAC.controladores;


import GIDAC.modelo.contadorDashAdmin;
import GIDAC.servicios.AccesoService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import GIDAC.servicios.VisitantesService;
import GIDAC.servicios.UsuarioService;


@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
public class DashAdminController {

    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private AccesoService accesoService;
 
    @GetMapping("/contador-dash")
    public List<contadorDashAdmin> obtenerTotal(){
        contadorDashAdmin oC=new contadorDashAdmin();
        List<contadorDashAdmin> oCC=new ArrayList();
        oC.setContAdmin(usuarioService.usuarioPorRol(1).size());
        oC.setContDirec(usuarioService.usuarioPorRol(2).size());
        oC.setContInves(usuarioService.usuarioPorRol(3).size());
        oC.setContVisit(accesoService.findAll().size());
        oCC.add(oC);
        return oCC;
    }

}
