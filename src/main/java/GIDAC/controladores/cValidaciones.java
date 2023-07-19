/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GIDAC.controladores;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**
 *
 * @author My Notebook
 */
public class cValidaciones {

    public cValidaciones() {
    }
    
    
    public boolean validarDosFechas(Date fecha1, Date fecha2) {
        if (fecha1.compareTo(fecha2) > 0) {
            return false;
        } else if (fecha1.compareTo(fecha2) < 0) {
            return true;
        } else {
            return false;
        }
    }
    public boolean validarFecha(int year, int month, int day) {
        try {
            LocalDate date = LocalDate.of(year, month, day);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
    public Date Fecha(String dateString) {
        try {
            LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            int day = date.getDayOfMonth();
            int month = date.getMonthValue();
            int year = date.getYear();
            if(validarFecha(year,month,day)){
                return fechaFormato(dateString);
            }else{
                return null;
            }
        } catch (Exception ex) {
            return null;
        }
    }
    
    public Date fechaFormato(String stringFecha){
        try{
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            return formato.parse(stringFecha);
        }catch(Exception e){
            return null;
        }
    }
    public Date fechaDumi(){
        try{
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            return formato.parse("1900-1-1");
        }catch(Exception e){
            return null;
        }
    }
    public boolean validarCedula(String id) {
        if (id == null || id.length() != 10) {
            return false;
        }

        int sum = 0;
        for (int i = 0; i < 9; i++) {
            int digit = id.charAt(i) - '0';
            if ((i+1) % 2 == 0) {
                sum += digit;
            } else {
                sum += digit * 2;
                if (digit >= 5) {
                    sum -= 9;
                }
            }
        }
        int lastDigit = id.charAt(9) - '0';
        return lastDigit == (10 - sum % 10) % 10;
    }
    
    public boolean validarEmail(String email) {
        try {
            InternetAddress emailAddress = new InternetAddress(email);
            emailAddress.validate();
            if(email.endsWith("@espoch.edu.ec")){
                return true;
            }else{
                return false;
            }
        } catch (AddressException ex) {
            return false;
        }
    }
    
    public Date fechaActual(){
        Date fechaActual = new Date();
        return fechaActual;
    }
    
    public boolean conpararFechaHora(Date fechaCreacion , Date fechaActual){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaCreacion);
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date fechaCreacionMasUnDia = calendar.getTime();
        
        if (fechaCreacionMasUnDia.before(fechaActual)) {
            return true;
        } else{
            return false;
        }
    }
}
