package GIDAC.modelo;


public class VariableDifusion {
    private String idVariable;
    private String nombreVariable;
    private String nombreSIglas;
    private String nombreOrganizacion;

    public String getIdVariable() {
        return idVariable;
    }

    public String getNombreVariable() {
        return nombreVariable;
    }

    public String getNombreSIglas() {
        return nombreSIglas;
    }

    public String getNombreOrganizacion() {
        return nombreOrganizacion;
    }

    public void setIdVariable(String idVariable) {
        this.idVariable = idVariable;
    }

    public void setNombreVariable(String nombreVariable) {
        this.nombreVariable = nombreVariable;
    }

    public void setNombreSIglas(String nombreSIglas) {
        this.nombreSIglas = nombreSIglas;
    }

    public void setNombreOrganizacion(String nombreOrganizacion) {
        this.nombreOrganizacion = nombreOrganizacion;
    }
    
    
}
