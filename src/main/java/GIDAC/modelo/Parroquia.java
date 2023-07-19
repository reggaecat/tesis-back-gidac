package GIDAC.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
@Table(name = "parroquia")
@Entity
public class Parroquia {

    @Id
    @Column(name = "codigo_parroquia")
    private String codigoParroquia;
    
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "codigo_canton")
    private Canton canton;


    private String nombreParroquia;

    public String getCodigoParroquia() {
        return codigoParroquia;
    }


   

    public Canton getCanton() {
        return canton;
    }

    public String getNombreParroquia() {
        return nombreParroquia;
    }

    public void setCodigoParroquia(String codigoParroquia) {
        this.codigoParroquia = codigoParroquia;
    }


    public void setCanton(Canton canton) {
        this.canton = canton;
    }

    public void setNombreParroquia(String nombreParroquia) {
        this.nombreParroquia = nombreParroquia;
    }

    
}
