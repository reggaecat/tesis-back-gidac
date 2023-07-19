package GIDAC.modelo;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "institucion_ejecutoria")
@IdClass(InstitucionEjecutoraId.class)
public class InstitucionEjecutora{
    
    @Id
    @Column(name = "id_institucion")
    private Integer idInstitucion;
    
    @Id
    @Column(name = "id_proyecto")
    private Integer idProyecto;
    
    private Date fechaCreacion;
    private Date fechaActualizacion;
    private boolean vigencia=true;
            
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_institucion", insertable = false, updatable = false)
    private Institucion institucion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_proyecto", insertable = false, updatable = false)
    private ProyectoInvestigacion proyectoInvestigacion;


    public InstitucionEjecutora() {
    }

    public Integer getIdInstitucion() {
        return idInstitucion;
    }

    public Integer getIdProyecto() {
        return idProyecto;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public boolean isVigencia() {
        return vigencia;
    }

    public Institucion getInstitucion() {
        return institucion;
    }

    public ProyectoInvestigacion getProyectoInvestigacion() {
        return proyectoInvestigacion;
    }

    public void setIdInstitucion(Integer idInstitucion) {
        this.idInstitucion = idInstitucion;
    }

    public void setIdProyecto(Integer idProyecto) {
        this.idProyecto = idProyecto;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public void setVigencia(boolean vigencia) {
        this.vigencia = vigencia;
    }

    public void setInstitucion(Institucion institucion) {
        this.institucion = institucion;
    }

    public void setProyectoInvestigacion(ProyectoInvestigacion proyectoInvestigacion) {
        this.proyectoInvestigacion = proyectoInvestigacion;
    }

    
    
    
}
