package GIDAC.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "profundidad_parcela")
@IdClass(ProfundidadParcelaId.class)
public class ProfundidadParcela {
    
    @Id
    @Column(name = "id_profundidad")
    private Integer idProfundidad;
    
    @Id
    @Column(name = "id_parcela")
    private Integer idParcela;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_profundidad", insertable = false, updatable = false)
    private Profundidad profundidad;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_parcela", insertable = false, updatable = false)
    private Parcela parcela;

    public Integer getIdProfundidad() {
        return idProfundidad;
    }

    public void setIdProfundidad(Integer idProfundidad) {
        this.idProfundidad = idProfundidad;
    }

    public void setIdParcela(Integer idParcela) {
        this.idParcela = idParcela;
    }

    public void setProfundidad(Profundidad profundidad) {
        this.profundidad = profundidad;
    }

    public void setParcela(Parcela parcela) {
        this.parcela = parcela;
    }

    public Integer getIdParcela() {
        return idParcela;
    }

    public Profundidad getProfundidad() {
        return profundidad;
    }

    public Parcela getParcela() {
        return parcela;
    }

    
    

    
}
