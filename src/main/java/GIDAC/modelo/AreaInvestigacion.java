package GIDAC.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
@Table(name = "area_investigacion")
@Entity
public class AreaInvestigacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAreaInvestigacion;

    private String nombreAreaInvestigacion;
    
    private boolean vigencia=true;

    
    
    
    @OneToMany(mappedBy = "areaInvestigacion",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<AreaInvestigacionProyecto> areaInvestigacionProyecto = new HashSet<>();
    

    public Integer getIdAreaInvestigacion() {
        return idAreaInvestigacion;
    }

    public String getNombreAreaInvestigacion() {
        return nombreAreaInvestigacion;
    }

    public void setIdAreaInvestigacion(Integer idAreaInvestigacion) {
        this.idAreaInvestigacion = idAreaInvestigacion;
    }

    public void setNombreAreaInvestigacion(String nombreAreaInvestigacion) {
        this.nombreAreaInvestigacion = nombreAreaInvestigacion;
    }
    
    public boolean isVigencia() {
        return vigencia;
    }

    public void setVigencia(boolean vigencia) {
        this.vigencia = vigencia;
    }

    public Set<AreaInvestigacionProyecto> getAreaInvestigacionProyecto() {
        return areaInvestigacionProyecto;
    }

    public void setAreaInvestigacionProyecto(Set<AreaInvestigacionProyecto> areaInvestigacionProyecto) {
        this.areaInvestigacionProyecto = areaInvestigacionProyecto;
    }
    
    
    
    

}
