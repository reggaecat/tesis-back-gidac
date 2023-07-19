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
    private String descripcion;
    private Date fechaInicio;
    private Date fechaFin;
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

    public Integer getIdDataset() {
        return idDataset;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
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

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
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


    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
    
    
}
