package GIDAC.modelo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;


@Entity
@Table(name = "dataset")
public class Dataset {

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDataset;
    private Date fechaSalidaCampo;
    private Date fechaCreacion;
    private Date fechaActualizacion;
    private boolean vigencia=true;
    
    @OneToMany(mappedBy = "dataset",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<DatoRecolectado> datoRecolectado = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_proyecto")
    private ProyectoInvestigacion proyectoInvestigacion;
    
    
    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "id_profundidad", referencedColumnName = "id_profundidad"),
        @JoinColumn(name = "id_parcela", referencedColumnName = "id_parcela")
    })
    private ProfundidadParcela profundidadParcela;
    
    private boolean editable=true;

    public boolean isEditable() {
        return editable;
    }
    
     public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public Integer getIdDataset() {
        return idDataset;
    }

    public boolean isVigencia() {
        return vigencia;
    }

    public Set<DatoRecolectado> getDatoRecolectado() {
        return datoRecolectado;
    }

    public ProyectoInvestigacion getProyectoInvestigacion() {
        return proyectoInvestigacion;
    }

    public ProfundidadParcela getProfundidadParcela() {
        return profundidadParcela;
    }

    public void setIdDataset(Integer idDataset) {
        this.idDataset = idDataset;
    }

    public void setVigencia(boolean vigencia) {
        this.vigencia = vigencia;
    }

    public void setDatoRecolectado(Set<DatoRecolectado> datoRecolectado) {
        this.datoRecolectado = datoRecolectado;
    }

    public void setProyectoInvestigacion(ProyectoInvestigacion proyectoInvestigacion) {
        this.proyectoInvestigacion = proyectoInvestigacion;
    }

    public void setProfundidadParcela(ProfundidadParcela profundidadParcela) {
        this.profundidadParcela = profundidadParcela;
    }

    public void setFechaSalidaCampo(Date fechaSalidaCampo) {
        this.fechaSalidaCampo = fechaSalidaCampo;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public Date getFechaSalidaCampo() {
        return fechaSalidaCampo;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }
    
    
    
    
}
