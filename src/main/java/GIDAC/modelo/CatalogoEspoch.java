package GIDAC.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
@Table(name = "catalogo_espoch")
@Entity
public class CatalogoEspoch {

    @Id
    private String codigoVariableEspoch;
    
    private String nombreVariableEspoch;
    private String descripcion;
    
    @OneToMany(mappedBy = "catalogoEspoch",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<EquivalenciaVariable> equivalenciaVariable = new HashSet<>();

    public String getCodigoVariableEspoch() {
        return codigoVariableEspoch;
    }

    public void setCodigoVariableEspoch(String codigoVariableEspoch) {
        this.codigoVariableEspoch = codigoVariableEspoch;
    }
    
    public Set<EquivalenciaVariable> getEquivalenciaVariable() {
        return equivalenciaVariable;
    }

    

    public void setEquivalenciaVariable(Set<EquivalenciaVariable> equivalenciaVariable) {
        this.equivalenciaVariable = equivalenciaVariable;
    }

    public String getNombreVariableEspoch() {
        return nombreVariableEspoch;
    }

    public void setNombreVariableEspoch(String nombreVariableEspoch) {
        this.nombreVariableEspoch = nombreVariableEspoch;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    
    
    
    
}
