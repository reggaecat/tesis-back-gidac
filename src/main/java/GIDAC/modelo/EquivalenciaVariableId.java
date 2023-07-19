/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GIDAC.modelo;

import java.io.Serializable;
import java.util.Objects;

public class EquivalenciaVariableId implements Serializable {
    
    
    private Integer idVariable;
    private String codigoVariableOrganizacion;
    private String codigoVariableEspoch;

    public EquivalenciaVariableId() {
        // Constructor sin argumentos
    }

    public EquivalenciaVariableId(Integer idVariable, String codigoVariableOrganizacion, String codigoVariableEspoch) {
        this.idVariable = idVariable;
        this.codigoVariableOrganizacion = codigoVariableOrganizacion;
        this.codigoVariableEspoch = codigoVariableEspoch;
    }

    

    public Integer getIdVariable() {
        return idVariable;
    }

    

    public void setIdVariable(Integer idVariable) {
        this.idVariable = idVariable;
    }

    public String getCodigoVariableOrganizacion() {
        return codigoVariableOrganizacion;
    }

    public String getCodigoVariableEspoch() {
        return codigoVariableEspoch;
    }

    public void setCodigoVariableOrganizacion(String codigoVariableOrganizacion) {
        this.codigoVariableOrganizacion = codigoVariableOrganizacion;
    }

    public void setCodigoVariableEspoch(String codigoVariableEspoch) {
        this.codigoVariableEspoch = codigoVariableEspoch;
    }

    

    

    // Equals y HashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EquivalenciaVariableId that = (EquivalenciaVariableId) o;
        return Objects.equals(idVariable, that.idVariable) &&
                Objects.equals(codigoVariableEspoch, that.codigoVariableEspoch) &&
                Objects.equals(codigoVariableOrganizacion, that.codigoVariableOrganizacion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idVariable, codigoVariableEspoch, codigoVariableOrganizacion);
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