package GIDAC.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "unidad_medida_variable")
@IdClass(UnidadMedidaVariableId.class)
public class UnidadMedidaVariable {

    @Id
    @Column(name = "id_unidad_medida")
    private Integer idUnidadMedida;
    
    @Id
    @Column(name = "id_variable")
    private Integer idVariable;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_variable", insertable = false, updatable = false)
    private Variable variable;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_unidad_medida", insertable = false, updatable = false)
    private UnidadMedida unidadMedida;

    public UnidadMedidaVariable(Integer idUnidadMedida, Integer idVariable, Variable variable, UnidadMedida unidadMedida) {
        this.idUnidadMedida = idUnidadMedida;
        this.idVariable = idVariable;
        this.variable = variable;
        this.unidadMedida = unidadMedida;
    }

    public UnidadMedidaVariable() {
    }
    
    public Integer getIdUnidadMedida() {
        return idUnidadMedida;
    }

    public Integer getIdVariable() {
        return idVariable;
    }

    public Variable getVariable() {
        return variable;
    }

    public UnidadMedida getUnidadMedida() {
        return unidadMedida;
    }

    public void setIdUnidadMedida(Integer idUnidadMedida) {
        this.idUnidadMedida = idUnidadMedida;
    }

    public void setIdVariable(Integer idVariable) {
        this.idVariable = idVariable;
    }

    public void setVariable(Variable variable) {
        this.variable = variable;
    }

    public void setUnidadMedida(UnidadMedida unidadMedida) {
        this.unidadMedida = unidadMedida;
    }


    

}
