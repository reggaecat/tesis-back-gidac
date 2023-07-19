package GIDAC.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
@Table(name = "pais")
@Entity
public class Pais {

    @Id
    private String codigoPais;
    
    @OneToMany(mappedBy = "pais",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Provincia> provincia = new HashSet<>();

    private String nombrePais;
    
    private String normalizacion;

    public String getCodigoPais() {
        return codigoPais;
    }

    public void setCodigoPais(String codigoPais) {
        this.codigoPais = codigoPais;
    }

    
    
    public Set<Provincia> getProvincia() {
        return provincia;
    }

    public String getNombrePais() {
        return nombrePais;
    }

    public void setProvincia(Set<Provincia> provincia) {
        this.provincia = provincia;
    }

    public void setNombrePais(String nombrePais) {
        this.nombrePais = nombrePais;
    }

    public String getNormalizacion() {
        return normalizacion;
    }

    public void setNormalizacion(String normalizacion) {
        this.normalizacion = normalizacion;
    }

    
    
    
    
}
