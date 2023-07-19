package GIDAC.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
@Table(name = "provincia")
@Entity
public class Provincia {
    
    @Id
    @Column(name = "codigo_provincia")
    private String codigoProvincia;
    
    @OneToMany(mappedBy = "provincia",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Canton> canton = new HashSet<>();
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "codigo_pais")
    private Pais pais;

    private String nombreProvincia;

    public void setCodigoProvincia(String codigoProvincia) {
        this.codigoProvincia = codigoProvincia;
    }

    public String getCodigoProvincia() {
        return codigoProvincia;
    }

    
    public Set<Canton> getCanton() {
        return canton;
    }

    public Pais getPais() {
        return pais;
    }

    public String getNombreProvincia() {
        return nombreProvincia;
    }

   
    public void setCanton(Set<Canton> canton) {
        this.canton = canton;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public void setNombreProvincia(String nombreProvincia) {
        this.nombreProvincia = nombreProvincia;
    }

    
    
    
    
    
}
