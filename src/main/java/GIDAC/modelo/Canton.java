package GIDAC.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
@Table(name = "canton")
@Entity
public class Canton {

    @Id
    @Column(name = "codigo_canton")
    private String codigoCanton;
    
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "codigo_provincia")
    private Provincia provincia;
    

    private String nombreCanton;

    public String getCodigoCanton() {
        return codigoCanton;
    }

    public void setCodigoCanton(String codigoCanton) {
        this.codigoCanton = codigoCanton;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public String getNombreCanton() {
        return nombreCanton;
    }



    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    public void setNombreCanton(String nombreCanton) {
        this.nombreCanton = nombreCanton;
    }


    
    
    
}
