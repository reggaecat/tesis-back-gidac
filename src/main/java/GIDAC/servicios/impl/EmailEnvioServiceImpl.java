package GIDAC.servicios.impl;


import GIDAC.controladores.cValidaciones;
import GIDAC.modelo.CorreoElectronico;
import GIDAC.modelo.EmailEnvio;
import GIDAC.repositorios.EmailEnvioRepository;
import GIDAC.servicios.EmailEnvioService;
import java.util.List;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import org.springframework.context.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class EmailEnvioServiceImpl implements EmailEnvioService {

    @Autowired
    private EmailEnvioRepository repository;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private JavaMailSender javaMailSender;

    public EmailEnvioServiceImpl() {
    }

    
    @Override
    public EmailEnvio save(Object objeto) {
        EmailEnvio oA=(EmailEnvio) objeto;
        cValidaciones ov = new cValidaciones();
        oA.setFechaCreacion(ov.fechaActual());
        updateJavaMailSenderConfiguration(oA);
        return repository.save(oA);
    }
    
    private void updateJavaMailSenderConfiguration(EmailEnvio emailEnvio) {
        if (javaMailSender instanceof JavaMailSenderImpl) {
            JavaMailSenderImpl mailSender = (JavaMailSenderImpl) javaMailSender;
            mailSender.setHost(emailEnvio.getHost());
            mailSender.setPort(Integer.parseInt(emailEnvio.getPort()));
            mailSender.setUsername(emailEnvio.getEmail());
            mailSender.setPassword(emailEnvio.getContrasenia());
        } else {
            throw new IllegalArgumentException("javaMailSender no es una instancia de JavaMailSenderImpl");
        }
    }
    
    @Override
    public EmailEnvio findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List findAll() {
        return repository.findAll();
    }

    @Override
    public EmailEnvio update(Object objeto) {
        EmailEnvio oA=(EmailEnvio) objeto;
        EmailEnvio oC=repository.findById(oA.getIdEmailEnvio()).orElse(null);
        oA.setFechaCreacion(oC.getFechaCreacion());
        cValidaciones ov = new cValidaciones();
        oA.setFechaActualizacion(ov.fechaActual());
        oA.setVigencia(oC.isVigencia());
        updateJavaMailSenderConfiguration(oA);
        return repository.save(oA);
    }

    @Override
    public void delete(Integer id) {
        EmailEnvio oA=repository.findById(id).orElse(null);
        cValidaciones ov = new cValidaciones();
        oA.setFechaActualizacion(ov.fechaActual());
        oA.setVigencia(false);
        repository.save(oA);
    }

    @Override
    public void restore(Integer id) {
        EmailEnvio oA=repository.findById(id).orElse(null);
        cValidaciones ov = new cValidaciones();
        oA.setFechaActualizacion(ov.fechaActual());
        oA.setVigencia(true);
        updateJavaMailSenderConfiguration(oA);
        repository.save(oA);
    }

    @Override
    public List<EmailEnvio> findByVigencia(Boolean vigencia) {
        return repository.findByVigencia(vigencia);
    }

    @Override
    public void enviarEmailResetearContrasenia(String email, String clave)throws Exception  {
        List<EmailEnvio> oLista=repository.findByVigencia(true);
        if(!oLista.isEmpty()){
            EmailEnvio emailEnvio= oLista.get(0);    
            final String username = emailEnvio.getEmail(); 
            final String password = emailEnvio.getContrasenia(); 

            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", emailEnvio.getHost());
            props.put("mail.smtp.port", emailEnvio.getPort());

            Session session = Session.getInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(username));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email)); 
                message.setSubject("Resetear contraseña");
                
                CorreoElectronico oCor=new CorreoElectronico();
                String correoHTML = oCor.reseteoContraseniaUsuarioMensaje(email, clave);
    
                message.setContent(correoHTML, "text/html; charset=utf-8");
                //message.setText(oCor.reseteoContraseniaUsuarioMensaje(email, clave));
                
                Transport.send(message);
                System.out.println("..........................................");
                System.out.println("El correo 1 se ha enviado correctamente.");
                System.out.println("..........................................");
            } catch (MessagingException e) {
                System.err.println("No se pudo enviar el correo 1: " + e.getMessage());
            } catch (Exception ex) {
                System.err.println("Ocurrió un error inesperado 1: " + ex.getMessage());
            }       
        }else{
            throw new Exception("No existe email para enviar");
        }
        
    }

    @Override
    public void enviarEmailResetearContrasenia1(String email, String clave) throws Exception {
        List<EmailEnvio> oLista=repository.findByVigencia(true);
        if(!oLista.isEmpty()){
            EmailEnvio emailEnvio= oLista.get(0);    
            final String username = emailEnvio.getEmail(); 
            final String password = emailEnvio.getContrasenia(); 

            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", emailEnvio.getHost());
            props.put("mail.smtp.port", emailEnvio.getPort());

            Session session = Session.getInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(username));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email)); // Reemplaza con el destinatario
                message.setSubject("Resetear contraseña");
                
                CorreoElectronico oCor=new CorreoElectronico();
                
                message.setText(oCor.reseteoContraseniaUsuarioMensaje1(email, clave));
                
                Transport.send(message);
                System.out.println("..........................................");
                System.out.println("El correo 2 se ha enviado correctamente.");
                System.out.println("..........................................");
            } catch (MessagingException e) {
                System.err.println("No se pudo enviar el correo 2: " + e.getMessage());
            } catch (Exception ex) {
                System.err.println("Ocurrió un error inesperado 2: " + ex.getMessage());
            }       
        }else{
            throw new Exception("No existe email para enviar");
        }
    }

    @Override
    public void enviarEmailResetearContrasenia2(String email, String clave) throws Exception {
        List<EmailEnvio> oLista=repository.findByVigencia(true);
        if(!oLista.isEmpty()){
            EmailEnvio emailEnvio= oLista.get(0);    
            final String username = emailEnvio.getEmail(); 
            final String password = emailEnvio.getContrasenia(); 

            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", emailEnvio.getHost());
            props.put("mail.smtp.port", emailEnvio.getPort());
            

            Session session = Session.getInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(username));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email)); // Reemplaza con el destinatario
                message.setSubject("Resetear contraseña");
                
                CorreoElectronico oCor=new CorreoElectronico();
                
                message.setText(oCor.reseteoContraseniaUsuarioMensaje2(email, clave));
                
                Transport.send(message);
                System.out.println("..........................................");
                System.out.println("El correo 3 se ha enviado correctamente.");
                System.out.println("..........................................");
            } catch (MessagingException e) {
                System.err.println("No se pudo enviar el correo 3: " + e.getMessage());
            } catch (Exception ex) {
                System.err.println("Ocurrió un error inesperado 3: " + ex.getMessage());
            }       
        }else{
            throw new Exception("No existe email para enviar");
        }
    }
}
