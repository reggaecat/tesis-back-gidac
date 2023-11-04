package GIDAC.controladores;

import GIDAC.configuraciones.JwtUtils;
import GIDAC.excepciones.UsuarioNotFoundException;
import GIDAC.modelo.CorreoElectronico;
import GIDAC.modelo.JwtRequest;
import GIDAC.modelo.JwtResponse;
import GIDAC.modelo.Usuario;
import GIDAC.modelo.Visitantes;
import GIDAC.servicios.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.security.SecureRandom;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import GIDAC.servicios.VisitantesService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.multipart.MultipartFile;
import GIDAC.servicios.UsuarioService;

@RestController
@CrossOrigin("*")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private VisitantesService crudVisitante;
    
    
    @Autowired
    private JavaMailSender mailSender;
    
    @Autowired
    private JwtUtils jwtUtils;

     @PostMapping("/generate-token")
    public ResponseEntity<?> generarToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        try{
            autenticar(jwtRequest.getEmail(),jwtRequest.getContrasenia());
        }catch (UsuarioNotFoundException exception){
            exception.printStackTrace();
            throw new Exception("Usuario no encontrado");
        }

        UserDetails userDetails =  this.userDetailsService.loadUserByUsername(jwtRequest.getEmail());
        String token = this.jwtUtils.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void autenticar(String email,String contrasenia) throws Exception {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email,contrasenia));
        }catch (DisabledException exception){
            throw  new Exception("USUARIO DESHABILITADO " + exception.getMessage());
        }catch (BadCredentialsException e){
            throw  new Exception("Credenciales invalidas " + e.getMessage());
        }
    }

    @GetMapping("/actual-usuario")
    public Usuario obtenerUsuarioActual(Principal principal){
        return (Usuario) this.userDetailsService.loadUserByUsername(principal.getName());
    }
    
    
    
    
    @GetMapping("/listar-visitante")
    public List<Visitantes> listar()
    {
        return crudVisitante.findAll();
    }
    //guardar
    @PostMapping("/guardar-visitante")
    public Object guardar(@RequestBody Visitantes oC)
    {
        if(oC.getIp().equals("")){
            return null;
        }else{
            return crudVisitante.save(oC);    
        }
    }
    @PostMapping("/editar-perfil")    
    public ResponseEntity<Usuario> editarUsuarioActual(@RequestParam("user") String datosJson, @RequestParam("imagen") MultipartFile imagen) throws JsonProcessingException{
        cValidaciones validaciones =new cValidaciones();
        Usuario usuario = new ObjectMapper().readValue(datosJson, Usuario.class);
        usuario.setFechaActualizacion(validaciones.fechaActual());
        usuario.setContrasenia(this.bCryptPasswordEncoder.encode(usuario.getPassword()));
        if(!imagen.isEmpty()){
            try{
                usuario.setImagenPerfil(imagen.getBytes());
                
            }catch(Exception e){
                System.out.println("Error al ingresar la imagen");
            }
        }
        return ResponseEntity.ok(usuarioService.actualizarUsuario(usuario)); 
    } 
    
//     public ResponseEntity<Usuario> editarUsuarioActual(@RequestPart("usuario") Usuario usuario, @RequestPart("imagen") MultipartFile imagen) throws IOException{
//        usuario.setPassword(this.bCryptPasswordEncoder.encode(usuario.getPassword()));
//        System.out.println("Imagen guardada__________________________________");
//        if(!imagen.isEmpty()){
//            try{
//                usuario.setDatos(imagen.getBytes());
//                
//            }catch(Exception e){
//                System.out.println("Error al ingresar la imagen");
//            }
//        }
//        return ResponseEntity.ok(usuarioService.actualizarUsuario(usuario)); 
//    } 
//    
    
    
    
    
//         public void editarUsuarioActual(@RequestParam("id") Long id, @RequestParam("file") MultipartFile file) throws IOException{
//            Usuario usuarioLocal = usuarioService.obtenerUsuarioId(id);
//            try{
//                usuarioLocal.setDatos(file.getBytes());
//                usuarioService.actualizarUsuario(usuarioLocal);
//                System.out.println("________________________________________guardadro");
//            }catch(Exception e){
//                System.out.println("error----------------------------------------------------------");
//            }
//    } 

    
    
    @PostMapping("/enviar-email")
    public ResponseEntity<?> enviarCorreoElectronico1(@RequestBody CorreoElectronico correoElectronico) {
        Usuario usuarioLocal = usuarioService.obtenerPorEmail(correoElectronico.getDestinatario());
        cValidaciones validaciones =new cValidaciones();
        if(usuarioLocal != null){
            String clave=generarClave();
            String mensaje=correoElectronico.reseteoContraseniaUsuarioMensaje(usuarioLocal.getNombreUsuario(), usuarioLocal.getApellidoUsuario(), "cedula", usuarioLocal.getEmail(), clave, validaciones.fechaActual());
            //String mensaje="Correo: "+usuarioLocal.getEmail();
            //mensaje=mensaje +"<br> Contraseña: "+clave;
            usuarioLocal.setContrasenia(this.bCryptPasswordEncoder.encode(clave));
            
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(correoElectronico.getDestinatario());
            message.setFrom("espoch.gidac@outlook.com");
            message.setSubject("Reseteo de contraseña");
            message.setText(mensaje);
            System.out.println("llega--------------------------------------------------------------");
            
            try {
                mailSender.send(message);
                usuarioService.actualizarUsuario(usuarioLocal);
                
                return ResponseEntity.ok("Correo electrónico enviado correctamente.");
            } catch (MailException e) {
                System.out.println("error "+e);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al enviar el correo electrónico.");
            }
        }
        else{
            System.out.println("El usuario no existe");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("El correo no esta registrado en el sistema.");
        }
    }
    
    private static final String CARACTERES =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    public String generarClave() {
        StringBuilder sb = new StringBuilder(10);
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < 10; i++) {
            sb.append(CARACTERES.charAt(random.nextInt(CARACTERES.length())));
        }
        return sb.toString();
    }

    
    
}