package GIDAC.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "variable")
public class Variable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idVariable;
    
    
    private String nombreVariable;
    
    @OneToMany(mappedBy = "variable",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<EquivalenciaVariable> equivalenciaVariable = new HashSet<>();
    
    @OneToMany(mappedBy = "variable",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<UnidadMedidaVariable> unidadMedidaVariable = new HashSet<>();

    @OneToMany(mappedBy = "variable",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<DatoRecolectado> datoRecolectado = new HashSet<>();
    
    @OneToMany(mappedBy = "variable",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<ValorPermitido> valorPermitido = new HashSet<>();
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_tipo_variable")
    private TipoVariable tipoVariable;
    

    public Integer getIdVariable() {
        return idVariable;
    }

    public void setIdVariable(Integer idVariable) {
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

    public Set<UnidadMedidaVariable> getUnidadMedidaVariable() {
        return unidadMedidaVariable;
    }

    public void setUnidadMedidaVariable(Set<UnidadMedidaVariable> unidadMedidaVariable) {
        this.unidadMedidaVariable = unidadMedidaVariable;
    }


    
    
}
