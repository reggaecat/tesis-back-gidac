package GIDAC.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
@Table(name = "altura")
@Entity
public class Altura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAltura;
    
    private float alturaMinima;
    private float alturaMaxima;
    
    @OneToMany(mappedBy = "altura",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Conglomerado> conglomerado = new HashSet<>();
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_unidad_medida")
    private UnidadMedida unidadMedida;

    public void setIdAltura(Integer idAltura) {
        this.idAltura = idAltura;
    }

    public Integer getIdAltura() {
        return idAltura;
    }

    public float getAlturaMinima() {
        return alturaMinima;
    }

    public float getAlturaMaxima() {
        return alturaMaxima;
    }

    public void setAlturaMinima(float alturaMinima) {
        this.alturaMinima = alturaMinima;
    }

    public void setAlturaMaxima(float alturaMaxima) {
        this.alturaMaxima = alturaMaxima;
    }

    public Set<Conglomerado> getConglomerado() {
        return conglomerado;
    }

    public void setConglomerado(Set<Conglomerado> conglomerado) {
        this.conglomerado = conglomerado;
    }

    public UnidadMedida getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(UnidadMedida unidadMedida) {
        this.unidadMedida = unidadMedida;
    }
    
    

}
