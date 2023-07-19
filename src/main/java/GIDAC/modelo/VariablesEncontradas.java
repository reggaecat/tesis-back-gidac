/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GIDAC.modelo;

public class VariablesEncontradas{
    
    
    private Integer idVariable;
    private Integer numeroColumna;
    private Integer cantidadDatos;
    private String nombreVariable;
    private String nombreVariableOrganizacion;
    private String nombreTipoVariable;
    private String nombreOrganizacion;

    public void setIdVariable(Integer idVariable) {
        this.idVariable = idVariable;
    }

    public void setNumeroColumna(Integer numeroColumna) {
        this.numeroColumna = numeroColumna;
    }

    public Integer getIdVariable() {
        return idVariable;
    }

    public Integer getNumeroColumna() {
        return numeroColumna;
    }

    public void setNombreVariable(String nombreVariable) {
        this.nombreVariable = nombreVariable;
    }

    public void setNombreVariableOrganizacion(String nombreVariableOrganizacion) {
        this.nombreVariableOrganizacion = nombreVariableOrganizacion;
    }

    public void setNombreTipoVariable(String nombreTipoVariable) {
        this.nombreTipoVariable = nombreTipoVariable;
    }

    public String getNombreVariable() {
        return nombreVariable;
    }

    public String getNombreVariableOrganizacion() {
        return nombreVariableOrganizacion;
    }

    public String getNombreTipoVariable() {
        return nombreTipoVariable;
    }

    public String getNombreOrganizacion() {
        return nombreOrganizacion;
    }

    public void setNombreOrganizacion(String nombreOrganizacion) {
        this.nombreOrganizacion = nombreOrganizacion;
    }

    public void setCantidadDatos(Integer cantidadDatos) {
        this.cantidadDatos = cantidadDatos;
    }

    public Integer getCantidadDatos() {
        return cantidadDatos;
    }

    
    
    
    
}