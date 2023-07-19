package GIDAC.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "equivalencia_variable")
@IdClass(EquivalenciaVariableId.class)
public class EquivalenciaVariable {

    
    
    @Id
    @Column(name = "codigo_variable_organizacion")
    private String codigoVariableOrganizacion;
    
    @Id
    @Column(name = "codigo_variable_espoch")
    private String codigoVariableEspoch;
        
    @Id
    @Column(name = "id_variable")
    private Integer idVariable;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_variable", insertable = false, updatable = false)
    private Variable variable;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "codigo_variable_organizacion", insertable = false, updatable = false)
    private CatalogoOrganizacion catalogoOrganizacion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "codigo_variable_espoch", insertable = false, updatable = false)
    private CatalogoEspoch catalogoEspoch;

    public Integer getIdVariable() {
        return idVariable;
    }
    
     public Variable getVariable() {
        return variable;
    }
     public void setIdVariable(Integer idVariable) {
        this.idVariable = idVariable;
    }
    public void setVariable(Variable variable) {
        this.variable = variable;
    }

    public String getCodigoVariableOrganizacion() {
        return codigoVariableOrganizacion;
    }

    public String getCodigoVariableEspoch() {
        return codigoVariableEspoch;
    }

    public void setCodigoVariableOrganizacion(String codigoVariableOrganizacion) {
        this.codigoVariableOrganizacion = codigoVariableOrganizacion;
    }

    public void setCodigoVariableEspoch(String codigoVariableEspoch) {
        this.codigoVariableEspoch = codigoVariableEspoch;
    }

    public CatalogoOrganizacion getCatalogoOrganizacion() {
        return catalogoOrganizacion;
    }

    public CatalogoEspoch getCatalogoEspoch() {
        return catalogoEspoch;
    }

    

   

    

    public void setCatalogoOrganizacion(CatalogoOrganizacion catalogoOrganizacion) {
        this.catalogoOrganizacion = catalogoOrganizacion;
    }

    public void setCatalogoEspoch(CatalogoEspoch catalogoEspoch) {
        this.catalogoEspoch = catalogoEspoch;
    }
    
    

}
