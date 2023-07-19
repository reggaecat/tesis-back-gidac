package GIDAC.modelo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "solicitud_actualizar_dato")
public class SolicitudActualizarDato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSolicitudActualizar;

    private String nombre;
    private String apellido;
    private String motivo;
    private String investigacion;
    private Date fechaEnvioSolicitud;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_dato_recolectado")
    private DatoRecolectado datoRecolectado;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_estado_solictud_actualizar")
    private EstadoSolicitudActualizar estadoSolicitudActualizar;
    
    @OneToMany(mappedBy = "solicitudActualizarDato",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<RespuestaSolicitudActualizar> respuestaSolicitudActualizar = new HashSet<>();
    
    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario"),
        @JoinColumn(name = "id_proyecto", referencedColumnName = "id_proyecto")
    })
    private GrupoInvestigacion grupoInvestigacion;
    

    public Integer getIdSolicitudActualizar() {
        return idSolicitudActualizar;
    }

    public void setIdSolicitudActualizar(Integer idSolicitudActualizar) {
        this.idSolicitudActualizar = idSolicitudActualizar;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getMotivo() {
        return motivo;
    }

    

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getInvestigacion() {
        return investigacion;
    }

    public void setInvestigacion(String investigacion) {
        this.investigacion = investigacion;
    }

    public EstadoSolicitudActualizar getEstadoSolicitudActualizar() {
        return estadoSolicitudActualizar;
    }

    public void setEstadoSolicitudActualizar(EstadoSolicitudActualizar estadoSolicitudActualizar) {
        this.estadoSolicitudActualizar = estadoSolicitudActualizar;
    }

    public Date getFechaEnvioSolicitud() {
        return fechaEnvioSolicitud;
    }

    public DatoRecolectado getDatoRecolectado() {
        return datoRecolectado;
    }

    public void setFechaEnvioSolicitud(Date fechaEnvioSolicitud) {
        this.fechaEnvioSolicitud = fechaEnvioSolicitud;
    }

    public void setDatoRecolectado(DatoRecolectado datoRecolectado) {
        this.datoRecolectado = datoRecolectado;
    }

    public Set<RespuestaSolicitudActualizar> getRespuestaSolicitudActualizar() {
        return respuestaSolicitudActualizar;
    }

    public void setRespuestaSolicitudActualizar(Set<RespuestaSolicitudActualizar> respuestaSolicitudActualizar) {
        this.respuestaSolicitudActualizar = respuestaSolicitudActualizar;
    }

    public GrupoInvestigacion getGrupoInvestigacion() {
        return grupoInvestigacion;
    }

    public void setGrupoInvestigacion(GrupoInvestigacion grupoInvestigacion) {
        this.grupoInvestigacion = grupoInvestigacion;
    }
    
    public SolicitudActualizarDato(){

    }
}
