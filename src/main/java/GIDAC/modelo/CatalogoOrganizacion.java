package GIDAC.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
@Table(name = "catalogo_organizacion")
@Entity
public class CatalogoOrganizacion {

    @Id
    private String codigoVariableOrganizacion;
    
    private String nombreVariableOrganizacion;
    
    private String descripcion;
    
    @OneToMany(mappedBy = "catalogoOrganizacion",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<EquivalenciaVariable> equivalenciaVariable = new HashSet<>();
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_organizacion")
    private Organizacion organizacion;

    public Set<EquivalenciaVariable> getEquivalenciaVariable() {
        return equivalenciaVariable;
    }

    public String getCodigoVariableOrganizacion() {
        return codigoVariableOrganizacion;
    }

    public void setCodigoVariableOrganizacion(String codigoVariableOrganizacion) {
        this.codigoVariableOrganizacion = codigoVariableOrganizacion;
    }
    
    public void setEquivalenciaVariable(Set<EquivalenciaVariable> equivalenciaVariable) {
        this.equivalenciaVariable = equivalenciaVariable;
    }

    public String getNombreVariableOrganizacion() {
        return nombreVariableOrganizacion;
    }

    public void setNombreVariableOrganizacion(String nombreVariableOrganizacion) {
        this.nombreVariableOrganizacion = nombreVariableOrganizacion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setOrganizacion(Organizacion organizacion) {
        this.organizacion = organizacion;
    }

    public Organizacion getOrganizacion() {
        return organizacion;
    }

}
