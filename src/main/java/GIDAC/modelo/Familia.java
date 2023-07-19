package GIDAC.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
@Table(name = "familia")
@Entity
public class Familia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idFamilia;
    private String nombreFamilia;
    private String descripcion;
    private boolean vigencia=true;

    public Integer getIdFamilia() {
        return idFamilia;
    }

    public String getNombreFamilia() {
        return nombreFamilia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public boolean isVigencia() {
        return vigencia;
    }

    public void setIdFamilia(Integer idFamilia) {
        this.idFamilia = idFamilia;
    }

    public void setNombreFamilia(String nombreFamilia) {
        this.nombreFamilia = nombreFamilia;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setVigencia(boolean vigencia) {
        this.vigencia = vigencia;
    }

    
    
}
