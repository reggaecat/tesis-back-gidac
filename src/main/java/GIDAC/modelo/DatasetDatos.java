/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GIDAC.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class DatasetDatos{
   
    private Integer codigoDataset;
    private Date fechaDataset;
    
    public DatasetDatos() {
        // Constructor sin argumentos
    }

    public DatasetDatos(Integer codigoDataset, Date fechaDataset) {
        this.codigoDataset = codigoDataset;
        this.fechaDataset = fechaDataset;
    }

    public Integer getCodigoDataset() {
        return codigoDataset;
    }

    public void setCodigoDataset(Integer codigoDataset) {
        this.codigoDataset = codigoDataset;
    }

    public Date getFechaDataset() {
        return fechaDataset;
    }

    public void setFechaDataset(Date fechaDataset) {
        this.fechaDataset = fechaDataset;
    }
    
    
}
