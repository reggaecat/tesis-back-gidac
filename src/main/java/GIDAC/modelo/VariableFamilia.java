package GIDAC.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "variable_familia")
@IdClass(VariableFamiliaId.class)
public class VariableFamilia {

    @Id
    @Column(name = "id_familia")
    private Integer idFamilia;
    
    @Id
    @Column(name = "id_variable")
    private String idVariable;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_variable", insertable = false, updatable = false)
    private Variable variable;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_familia", insertable = false, updatable = false)
    private Familia familia;
    
    private boolean vigencia=true;

    public VariableFamilia(Integer idFamilia, String idVariable, Variable variable, Familia familia) {
        this.idFamilia = idFamilia;
        this.idVariable = idVariable;
        this.variable = variable;
        this.familia = familia;
    }

    public VariableFamilia() {
    }
    
    public String getIdVariable() {
        return idVariable;
    }

    public Variable getVariable() {
        return variable;
    }

    public void setIdVariable(String idVariable) {
        this.idVariable = idVariable;
    }

    public void setVariable(Variable variable) {
        this.variable = variable;
    }

    public Integer getIdFamilia() {
        return idFamilia;
    }

    public Familia getFamilia() {
        return familia;
    }

    public void setIdFamilia(Integer idFamilia) {
        this.idFamilia = idFamilia;
    }

    public void setFamilia(Familia familia) {
        this.familia = familia;
    }

    public boolean isVigencia() {
        return vigencia;
    }

    public void setVigencia(boolean vigencia) {
        this.vigencia = vigencia;
    }
    
    
}
