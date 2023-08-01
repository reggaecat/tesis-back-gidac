package GIDAC.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "variable")
public class Variable {

    @Id
    private String idVariable;
    
    
    private String nombreVariable;
    
    @OneToMany(mappedBy = "variable",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<EquivalenciaVariable> equivalenciaVariable = new HashSet<>();
    
    @OneToMany(mappedBy = "variable",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<VariableFamilia> variableFamilia = new HashSet<>();
    
    @OneToMany(mappedBy = "variable",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<VariableUnidadMedida> unidadMedidaVariable = new HashSet<>();

    @OneToMany(mappedBy = "variable",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<DatoRecolectado> datoRecolectado = new HashSet<>();
    
    @OneToMany(mappedBy = "variable",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<ValorPermitido> valorPermitido = new HashSet<>();
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_tipo_variable")
    private TipoVariable tipoVariable;
    

    public String getIdVariable() {
        return idVariable;
    }

    public void setIdVariable(String idVariable) {
        this.idVariable = idVariable;
    }

    public Set<EquivalenciaVariable> getEquivalenciaVariable() {
        return equivalenciaVariable;
    }
    
    public void setEquivalenciaVariable(Set<EquivalenciaVariable> equivalenciaVariable) {
        this.equivalenciaVariable = equivalenciaVariable;
    }

    public String getNombreVariable() {
        return nombreVariable;
    }

    public void setNombreVariable(String nombreVariable) {
        this.nombreVariable = nombreVariable;
    }
   
    
    public Set<DatoRecolectado> getDatoRecolectado() {
        return datoRecolectado;
    }

    public void setDatoRecolectado(Set<DatoRecolectado> datoRecolectado) {
        this.datoRecolectado = datoRecolectado;
    }

    public Set<ValorPermitido> getValorPermitido() {
        return valorPermitido;
    }

    public TipoVariable getTipoVariable() {
        return tipoVariable;
    }

    public void setValorPermitido(Set<ValorPermitido> valorPermitido) {
        this.valorPermitido = valorPermitido;
    }

    public void setTipoVariable(TipoVariable tipoVariable) {
        this.tipoVariable = tipoVariable;
    }

    public Set<VariableUnidadMedida> getUnidadMedidaVariable() {
        return unidadMedidaVariable;
    }

    public void setUnidadMedidaVariable(Set<VariableUnidadMedida> unidadMedidaVariable) {
        this.unidadMedidaVariable = unidadMedidaVariable;
    }

    public Set<VariableFamilia> getVariableFamilia() {
        return variableFamilia;
    }

    public void setVariableFamilia(Set<VariableFamilia> variableFamilia) {
        this.variableFamilia = variableFamilia;
    }
    
    
    
}
