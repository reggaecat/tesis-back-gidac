package GIDAC.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
@Table(name = "catalogo_organizacion")
@Entity
public class CatalogoOrganizacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idVariableOrganizacion;
    
    
    private String codigoVariableOrganizacion;
    
    private String nombreVariableOrganizacion;
    
    private String descripcion;
    
   
    
    private boolean vigencia=true;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_organizacion")
    private Organizacion organizacion;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_variable")
    private Variable variable;

    
    
    public Integer getIdVariableOrganizacion() {
        return idVariableOrganizacion;
    }

    public void setIdVariableOrganizacion(Integer idVariableOrganizacion) {
        this.idVariableOrganizacion = idVariableOrganizacion;
    }

    public String getCodigoVariableOrganizacion() {
        return codigoVariableOrganizacion;
    }

    public void setCodigoVariableOrganizacion(String codigoVariableOrganizacion) {
        this.codigoVariableOrganizacion = codigoVariableOrganizacion;
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

    public boolean isVigencia() {
        return vigencia;
    }

    public void setVigencia(boolean vigencia) {
        this.vigencia = vigencia;
    }

    public Variable getVariable() {
        return variable;
    }

    public void setVariable(Variable variable) {
        this.variable = variable;
    }

    
    
}
