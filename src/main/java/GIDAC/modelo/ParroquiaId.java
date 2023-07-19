/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GIDAC.modelo;

import java.io.Serializable;
import java.util.Objects;

public class ParroquiaId implements Serializable {
    private String codigoParroquia;
    private String codigoCanton;
    private String codigoProvincia;
    private String codigoPais;

    public ParroquiaId() {
        // Constructor sin argumentos
    }

    public ParroquiaId(String codigoCanton, String codigoParroquia, String codigoProvincia, String codigoPais) {
        this.codigoCanton = codigoCanton;
        this.codigoParroquia = codigoParroquia;
        this.codigoProvincia = codigoProvincia;
        this.codigoPais = codigoPais;
    }

    public String getCodigoCanton() {
        return codigoCanton;
    }

    public String getCodigoParroquia() {
        return codigoParroquia;
    }

    public String getCodigoProvincia() {
        return codigoProvincia;
    }

    public String getCodigoPais() {
        return codigoPais;
    }

    public void setCodigoCanton(String codigoCanton) {
        this.codigoCanton = codigoCanton;
    }

    public void setCodigoParroquia(String codigoParroquia) {
        this.codigoParroquia = codigoParroquia;
    }

    public void setCodigoProvincia(String codigoProvincia) {
        this.codigoProvincia = codigoProvincia;
    }

    public void setCodigoPais(String codigoPais) {
        this.codigoPais = codigoPais;
    }

    
    

    
    
    

    // Equals y HashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParroquiaId that = (ParroquiaId) o;
        return Objects.equals(codigoCanton, that.codigoCanton) &&
                Objects.equals(codigoParroquia, that.codigoParroquia)&&
                Objects.equals(codigoProvincia, that.codigoProvincia)&&
                Objects.equals(codigoPais, that.codigoPais);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigoPais, codigoProvincia,codigoCanton,codigoParroquia );
    }
}

//public class GrupoInvestigacionId implements Serializable {
//    private ProyectoInvestigacion proyectoInvestigacion;
//    private Usuario usuario;
//
//    public GrupoInvestigacionId() {
//        // Constructor sin argumentos
//    }
//    
//    // Constructor
//    public GrupoInvestigacionId(ProyectoInvestigacion proyectoInvestigacion, Usuario usuario) {
//        this.proyectoInvestigacion = proyectoInvestigacion;
//        this.usuario = usuario;
//    }
//
//    // Getters y Setters
//    public ProyectoInvestigacion getProyectoInvestigacion() {
//        return proyectoInvestigacion;
//    }
//
//    public void setProyectoInvestigacion(ProyectoInvestigacion proyectoInvestigacion) {
//        this.proyectoInvestigacion = proyectoInvestigacion;
//    }
//
//    public Usuario getUsuario() {
//        return usuario;
//    }
//
//    public void setUsuario(Usuario usuario) {
//        this.usuario = usuario;
//    }
//
//    // Equals y HashCode
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        GrupoInvestigacionId that = (GrupoInvestigacionId) o;
//        return Objects.equals(proyectoInvestigacion, that.proyectoInvestigacion) &&
//                Objects.equals(usuario, that.usuario);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(proyectoInvestigacion, usuario);
//    }
//}