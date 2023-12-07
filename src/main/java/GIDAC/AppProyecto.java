package GIDAC;



import GIDAC.controladores.cValidaciones;
import GIDAC.utils.UsuarioFoundException;
import GIDAC.modelo.GrupoInvestigacion;
import GIDAC.modelo.ProyectoInvestigacion;
import GIDAC.modelo.Rol;
import GIDAC.modelo.Usuario;
import GIDAC.servicios.EmailEnvioService;
import GIDAC.servicios.GrupoInvestigacionService;
import GIDAC.servicios.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.Set;
import GIDAC.servicios.UsuarioService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;




@SpringBootApplication
public class AppProyecto implements CommandLineRunner {

	@Autowired
	private UsuarioService usuarioService;
        
        @Autowired
	private RolService rolService;
        
        @Autowired
	private EmailEnvioService emailEnvioService;
        
        @Autowired
	private GrupoInvestigacionService grupoInvestigacionService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(AppProyecto.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
            
            try{  
                emailEnvioService.enviarEmailResetearContrasenia("cjdmaigua@gmail.com", "12345");
            }catch(Exception exception){
                throw new Exception("Error al enviar el mensaje 1: "+exception);
            }
            
            try{  
                emailEnvioService.enviarEmailResetearContrasenia1("cjdmaigua@gmail.com", "12345");
            }catch(Exception exception){
                throw new Exception("Error al enviar el mensaje 2: "+exception);
            }
            
            try{  
                emailEnvioService.enviarEmailResetearContrasenia2("cjdmaigua@gmail.com", "12345");
            }catch(Exception exception){
                throw new Exception("Error al enviar el mensaje 3: "+exception);
            }
//            
            try{
                            
                            
                            
//				Usuario usuario = new Usuario();
//				usuario.setNombreUsuario("Juan Carlos");
//				usuario.setApellidoUsuario("Maigua Rizo");
//				usuario.setContrasenia(bCryptPasswordEncoder.encode("12345"));
//				usuario.setEmail("cjd@gmail.com");
//
//				Rol rol = new Rol();
//				rol.setIdRol(1);
//				rol.setNombreRol("ADMINISTRADOR");
//                                Rol rolGuardado=(Rol) rolService.save(rol);
//                                usuario.setRol(rol);
//				Usuario usuarioGuardado = usuarioService.guardarUsuario(usuario);
//				System.out.println(usuarioGuardado.getUsername());
//                                
//                                
//                                rol.setIdRol(2);
//				rol.setNombreRol("DIRECTOR");
//                                rolGuardado=(Rol) rolService.save(rol);
//                                
//                                rol.setIdRol(3);
//				rol.setNombreRol("INVESTIGADOR");
//                                rolGuardado=(Rol) rolService.save(rol);
                                
                                
                                

			}catch(Exception exception){
                            System.out.println("error al guardar-------------------------------------");
				                        System.out.println(exception);
			}
        
			try{
                            
                            
//                            GrupoInvestigacion grupoInvestigacion = new GrupoInvestigacion();
//                            Usuario usuario = new Usuario();
//                            ProyectoInvestigacion proyectoInvestigacion = new ProyectoInvestigacion();
//                            
//                            usuario.setIdUsuario(7);
//                            proyectoInvestigacion.setIdProyectoInvestigacion(1);
//                            
//                            grupoInvestigacion.setUsuario(usuario);
//                            grupoInvestigacion.setProyectoInvestigacion(proyectoInvestigacion);
//                            
//                            grupoInvestigacion.setIdUsuario(usuario.getIdUsuario());
//                            grupoInvestigacion.setIdProyectoInvestigacion(proyectoInvestigacion.getIdProyectoInvestigacion());
//                            
//                            grupoInvestigacionService.save(grupoInvestigacion);


//                            GrupoInvestigacion grupoInvestigacion=new GrupoInvestigacion();
//                            Usuario usuario=new Usuario();
//                            ProyectoInvestigacion proyectoInvestigacion = new ProyectoInvestigacion();
//                            usuario.setIdUsuario(6);
//                            proyectoInvestigacion.setIdProyectoInvestigacion(1);
//                            
//                            grupoInvestigacion.setUsuario(usuario);
//                            grupoInvestigacion.setProyectoInvestigacion(proyectoInvestigacion);
//                            grupoInvestigacionService.save(grupoInvestigacion);
//                            System.out.println("guardardado-------------------------------------");
                            
                            
                            
//				Usuario usuario = new Usuario();
//				usuario.setNombreUsuario("Juan Carlos");
//				usuario.setApellidoUsuario("Maigua Rizo");
//				usuario.setContrasenia(bCryptPasswordEncoder.encode("12345"));
//				usuario.setEmail("cjd@gmail.com");
//
//				Rol rol = new Rol();
//				rol.setIdRol(1);
//				rol.setNombreRol("ADMINISTRADOR");
//                                Rol rolGuardado=(Rol) rolService.save(rol);
//                                usuario.setRol(rol);
//				Usuario usuarioGuardado = usuarioService.guardarUsuario(usuario);
//				System.out.println(usuarioGuardado.getUsername());
//                                
//                                
//                                rol.setIdRol(2);
//				rol.setNombreRol("DIRECTOR");
//                                rolGuardado=(Rol) rolService.save(rol);
//                                
//                                rol.setIdRol(3);
//				rol.setNombreRol("INVESTIGADOR");
//                                rolGuardado=(Rol) rolService.save(rol);
                                
                                
                                

			}catch(Exception exception){
                            System.out.println("error al guardar-------------------------------------");
				                        System.out.println(exception);
			}


	}

	
}
