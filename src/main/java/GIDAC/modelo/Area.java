package GIDAC.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
@Table(name = "area")
@Entity
public class Area {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idArea;
    
    private float area;
    
    @OneToMany(mappedBy = "area",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Parcela> parcela = new HashSet<>();
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_unidad_medida")
    private UnidadMedida unidadMedida;

    public Integer getIdArea() {
        return idArea;
    }

    public float getArea() {
        return area;
    }

    public Set<Parcela> getParcela() {
        return parcela;
    }

    public void setIdArea(Integer idArea) {
        this.idArea = idArea;
    }

    public void setArea(float area) {
        this.area = area;
    }

    public void setParcela(Set<Parcela> parcela) {
        this.parcela = parcela;
    }

    public UnidadMedida getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(UnidadMedida unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    
    
}
