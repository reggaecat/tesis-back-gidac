package GIDAC.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
@Table(name = "institucion")
@Entity
public class Institucion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idInstitucion;
    
    @OneToMany(mappedBy = "institucion",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<InstitucionEjecutora> institucionEjecutora = new HashSet<>();
    
    @OneToMany(mappedBy = "institucion",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<InstitucionParticipante> institucionParticipante = new HashSet<>();
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_localizacion")
    private Localizacion localizacion;

    private String nombreInstitucion;
    private String siglas;
    private String contacto;
    private String pagina;
    private String correo;
    private String telefono;

    public Integer getIdInstitucion() {
        return idInstitucion;
    }

    public Set<InstitucionEjecutora> getInstitucionEjecutora() {
        return institucionEjecutora;
    }

    public Set<InstitucionParticipante> getInstitucionParticipante() {
        return institucionParticipante;
    }

   

    public String getNombreInstitucion() {
        return nombreInstitucion;
    }

    public String getSiglas() {
        return siglas;
    }

    public String getContacto() {
        return contacto;
    }

    public String getPagina() {
        return pagina;
    }

    public String getCorreo() {
        return correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setIdInstitucion(Integer idInstitucion) {
        this.idInstitucion = idInstitucion;
    }

    public void setInstitucionEjecutora(Set<InstitucionEjecutora> institucionEjecutora) {
        this.institucionEjecutora = institucionEjecutora;
    }

    public void setInstitucionParticipante(Set<InstitucionParticipante> institucionParticipante) {
        this.institucionParticipante = institucionParticipante;
    }

    

    public void setNombreInstitucion(String nombreInstitucion) {
        this.nombreInstitucion = nombreInstitucion;
    }

    public void setSiglas(String siglas) {
        this.siglas = siglas;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public void setPagina(String pagina) {
        this.pagina = pagina;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Localizacion getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(Localizacion localizacion) {
        this.localizacion = localizacion;
    }

    
    
    
    
}
