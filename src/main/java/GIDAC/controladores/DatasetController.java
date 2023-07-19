package GIDAC.controladores;


import GIDAC.modelo.Dataset;
import GIDAC.modelo.ProfundidadParcela;
import GIDAC.servicios.DataSetService;
import GIDAC.servicios.ProfundidadParcelaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dataset")
@CrossOrigin("*")
public class DatasetController {

    
    @Autowired
    private DataSetService service;
     
    @Autowired
    private ProfundidadParcelaService profundidadParcelaService;
     

    @PostMapping("/guardar-dataset")
    public Object guardar(@RequestBody Dataset oC)
    {
        ProfundidadParcela profundidadParcela=new ProfundidadParcela();
        profundidadParcela.setIdParcela(oC.getProfundidadParcela().getIdParcela());
        profundidadParcela.setIdProfundidad(oC.getProfundidadParcela().getIdProfundidad());
        profundidadParcela.setProfundidad(oC.getProfundidadParcela().getProfundidad());
        profundidadParcela.setParcela(oC.getProfundidadParcela().getParcela());
        profundidadParcelaService.guardar(profundidadParcela);
        return service.guardar(oC);    
    }
    
    @GetMapping("/obtener-dataset/{id}")
    public Object obtener(@PathVariable Integer id)
    {
        return service.buscarPorId(id);
    }
    
    @GetMapping("/listar-dataset")
    public List<Dataset> listar()
    {
        return service.buscarTodos();
    }
    
    @DeleteMapping("/eliminar-dataset/{id}")
    public void eliminar(@PathVariable Integer id)
    {
        service.eliminar(id);
    }
    
    @GetMapping("/obtener-dataset/por-parcela/{id}")
    public List obtenerPorParcela(@PathVariable Integer id)
    {
        return service.buscarPorParcela(id);
    }
    
}
