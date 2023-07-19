package GIDAC.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
@Table(name = "linea_investigacion")
@Entity
public class LineaInvestigacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idLineaInvestigacion;
    
    @OneToMany(mappedBy = "lineaInvestigacion",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<LineaInvestigacionProyecto> lineaInvestigacionProyecto = new HashSet<>();
    
    private String nombreLineaInvestigacion;
    
    private boolean vigencia=true;

    public void setIdLineaInvestigacion(Integer idLineaInvestigacion) {
        this.idLineaInvestigacion = idLineaInvestigacion;
    }

    public Integer getIdLineaInvestigacion() {
        return idLineaInvestigacion;
    }

   

    public void setLineaInvestigacionProyecto(Set<LineaInvestigacionProyecto> lineaInvestigacionProyecto) {
        this.lineaInvestigacionProyecto = lineaInvestigacionProyecto;
    }

    public void setNombreLineaInvestigacion(String nombreLineaInvestigacion) {
        this.nombreLineaInvestigacion = nombreLineaInvestigacion;
    }

    

    public Set<LineaInvestigacionProyecto> getLineaInvestigacionProyecto() {
        return lineaInvestigacionProyecto;
    }

    public String getNombreLineaInvestigacion() {
        return nombreLineaInvestigacion;
    }

    public boolean isVigencia() {
        return vigencia;
    }

    public void setVigencia(boolean vigencia) {
        this.vigencia = vigencia;
    }
    
    

    
}
