package GIDAC.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
@Table(name = "sector_impacto")
@Entity
public class SectorImpacto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSectorImpacto;
    
    
    @OneToMany(mappedBy = "sectorImpacto",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<SectorImpactoProyecto> sectorImpactoProyecto = new HashSet<>();
    
    private String nombreSectorImpacto;

    private boolean vigencia=true;
    
    public Integer getIdSectorImpacto() {
        return idSectorImpacto;
    }

    public void setIdSectorImpacto(Integer idSectorImpacto) {
        this.idSectorImpacto = idSectorImpacto;
    }

   

    public Set<SectorImpactoProyecto> getSectorImpactoProyecto() {
        return sectorImpactoProyecto;
    }

    public String getNombreSectorImpacto() {
        return nombreSectorImpacto;
    }

   

    public void setSectorImpactoProyecto(Set<SectorImpactoProyecto> sectorImpactoProyecto) {
        this.sectorImpactoProyecto = sectorImpactoProyecto;
    }

    public void setNombreSectorImpacto(String nombreSectorImpacto) {
        this.nombreSectorImpacto = nombreSectorImpacto;
    }

    public boolean isVigencia() {
        return vigencia;
    }

    public void setVigencia(boolean vigencia) {
        this.vigencia = vigencia;
    }

    
    
}
