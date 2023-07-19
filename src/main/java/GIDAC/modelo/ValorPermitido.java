package GIDAC.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
@Table(name = "valor_permitido")
@Entity
public class ValorPermitido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idValorPermitido;
    
    private float valorMaximo;
    private float valorMinimo;
    private String ValorPermitido;
    
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_variable")
    private Variable variable;

    

    public float getValorMaximo() {
        return valorMaximo;
    }

    public float getValorMinimo() {
        return valorMinimo;
    }

    public String getValorPermitido() {
        return ValorPermitido;
    }

    public Variable getVariable() {
        return variable;
    }

    public Integer getIdValorPermitido() {
        return idValorPermitido;
    }

    public void setIdValorPermitido(Integer idValorPermitido) {
        this.idValorPermitido = idValorPermitido;
    }

   

    public void setValorMaximo(float valorMaximo) {
        this.valorMaximo = valorMaximo;
    }

    public void setValorMinimo(float valorMinimo) {
        this.valorMinimo = valorMinimo;
    }

    public void setValorPermitido(String ValorPermitido) {
        this.ValorPermitido = ValorPermitido;
    }

    public void setVariable(Variable variable) {
        this.variable = variable;
    }

    
    
    
    
}
