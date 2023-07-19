/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GIDAC.modelo;

import java.util.Date;

/**
 *
 * @author My Notebook
 */
public class CorreoElectronico {
    private String destinatario;
    private String asunto;
    private String contenido;
    
    public String registrarUsuarioMensaje(String nombre, String apellido, String cedula, String correo, String contrasenia, Date fecha){
        String mailBody = "";
        mailBody += "<center><h2 style=background-color:#231C1D;color:#E8DDB5>" + "EcoAndes" + "</h2></center>";
        mailBody += "<center><h4 style=background-color:#E8DDB5;color:#231C1D>" + "Datos Registrados" + "</h4>";
        mailBody += "<center><h5 style=color:#231C1D;>" + "<b>Nombre: </b>" + nombre +"</h5><br>";
        mailBody += "<center><h5 style=color:#231C1D;>" + "<b>Apellido: </b>" + apellido +"</h5><br>";
        mailBody += "<center><h5 style=color:#231C1D;>" + "<b>Cédula: </b>" + cedula +"</h5><br>";
        mailBody += "<center><h5 style=color:#231C1D;>" + "<b>Correo: </b>" + correo +"</h5><br>";
        mailBody += "<center><h5 style=color:#231C1D;>" + "<b>Contrasenia: </b>" + contrasenia +"</h5><br>";
        mailBody += "<center><h5 style=color:#231C1D;>" + "<b>Fecha y hora del registro: </b>" + fecha.toString() +"</h5><br>";        
        return mailBody;
    }
    
    public String reseteoContraseniaUsuarioMensaje(String nombre, String apellido, String cedula, String correo, String contrasenia, Date fecha){
        String mailBody = "";
        mailBody += "<center><h2 style=background-color:#231C1D;color:#E8DDB5>" + "EcoAndes" + "</h2></center>";
        mailBody += "<center><h4 style=background-color:#E8DDB5;color:#231C1D>" + "Actuaización de datos" + "</h4>";
        mailBody += "<center><h5 style=color:#231C1D;>" + "<b>Nombre: </b>" + nombre +"</h5><br>";
        mailBody += "<center><h5 style=color:#231C1D;>" + "<b>Apellido: </b>" + apellido +"</h5><br>";
        mailBody += "<center><h5 style=color:#231C1D;>" + "<b>Cédula: </b>" + cedula +"</h5><br>";
        mailBody += "<center><h5 style=color:#231C1D;>" + "<b>Correo: </b>" + correo +"</h5><br>";
        mailBody += "<center><h5 style=color:#231C1D;>" + "<b>Contrasenia nueva: </b>" + contrasenia +"</h5><br>";
        mailBody += "<center><h5 style=color:#231C1D;>" + "<b>Fecha y hora del actualización: </b>" + fecha.toString() +"</h5><br>";        
        return mailBody;
    }
    
    public String editarUsuarioMensajeConNuevoCorreo(String nombre, String apellido, String cedula, String correoAntiguo, String correoNuevo, String contrasenia, Date fecha){
        String mailBody = "";
        mailBody += "<center><h2 style=background-color:#231C1D;color:#E8DDB5>" + "EcoAndes" + "</h2></center>";
        mailBody += "<center><h4 style=background-color:#E8DDB5;color:#231C1D>" + "Actuaización de datos" + "</h4>";
        mailBody += "<center><h5 style=color:#231C1D;>" + "<b>Nombre: </b>" + nombre +"</h5><br>";
        mailBody += "<center><h5 style=color:#231C1D;>" + "<b>Apellido: </b>" + apellido +"</h5><br>";
        mailBody += "<center><h5 style=color:#231C1D;>" + "<b>Cédula: </b>" + cedula +"</h5><br>";
        mailBody += "<center><h5 style=color:#231C1D;>" + "<b>Correo antiguo: </b>" + correoAntiguo +"</h5><br>";
        mailBody += "<center><h5 style=color:#231C1D;>" + "<b>Correo nuevo: </b>" + correoAntiguo +"</h5><br>";
        mailBody += "<center><h5 style=color:#231C1D;>" + "<b>Contrasenia: </b>" + contrasenia +"</h5><br>";
        mailBody += "<center><h5 style=color:#231C1D;>" + "<b>Fecha y hora de la actualización: </b>" + fecha.toString() +"</h5><br>";        
        return mailBody;
    }
    
    public String editarUsuarioMensaje(String nombre, String apellido, String cedula, String correo, String contrasenia, Date fecha){
        String mailBody = "";
        mailBody += "<center><h2 style=background-color:#231C1D;color:#E8DDB5>" + "EcoAndes" + "</h2></center>";
        mailBody += "<center><h4 style=background-color:#E8DDB5;color:#231C1D>" + "Actuaización de datos" + "</h4>";
        mailBody += "<center><h5 style=color:#231C1D;>" + "<b>Nombre: </b>" + nombre +"</h5><br>";
        mailBody += "<center><h5 style=color:#231C1D;>" + "<b>Apellido: </b>" + apellido +"</h5><br>";
        mailBody += "<center><h5 style=color:#231C1D;>" + "<b>Cédula: </b>" + cedula +"</h5><br>";
        mailBody += "<center><h5 style=color:#231C1D;>" + "<b>Correo antiguo: </b>" + correo +"</h5><br>";
        mailBody += "<center><h5 style=color:#231C1D;>" + "<b>Contrasenia: </b>" + contrasenia +"</h5><br>";
        mailBody += "<center><h5 style=color:#231C1D;>" + "<b>Fecha y hora de la actualización: </b>" + fecha.toString() +"</h5><br>";        
        return mailBody;
    }
    
    public String rechazoSolicutudMensaje(String nombre, String apellido, String institucion, String correo, String motivo, Date fecha){
        String mailBody = "";
        mailBody += "<center><h2 style=background-color:#231C1D;color:#E8DDB5>" + "EcoAndes" + "</h2></center>";
        mailBody += "<center><h4 style=background-color:#E8DDB5;color:#231C1D>" + "Rechazo de solicitud de descarga de datos" + "</h4>";
        mailBody += "<center><h5 style=color:#231C1D;>" + "<b>Datos personales del solicitante</b></h5><br>";
        mailBody += "<center><h5 style=color:#231C1D;>" + "<b>Nombre: </b>" + nombre +"</h5><br>";
        mailBody += "<center><h5 style=color:#231C1D;>" + "<b>Apellido: </b>" + apellido +"</h5><br>";
        mailBody += "<center><h5 style=color:#231C1D;>" + "<b>Institución: </b>" + institucion +"</h5><br>";
        mailBody += "<center><h5 style=color:#231C1D;>" + "<b>Correo antiguo: </b>" + correo +"</h5><br>";
        mailBody += "<center><h5 style=color:#231C1D;>" + "<b>Fecha y hora de la respuesta: </b>" + fecha.toString() +"</h5><br><br>";    
        mailBody += "<center><h5 style=color:#231C1D;>" + "<b>Motivo de rechazo: </b>" + motivo +"</h5><br>";    
        return mailBody;
    }
    
    
    
    public String getDestinatario() {
        return destinatario;
    }
    
    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }
    
    public String getAsunto() {
        return asunto;
    }
    
    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }
    
    public String getContenido() {
        return contenido;
    }
    
    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
}
