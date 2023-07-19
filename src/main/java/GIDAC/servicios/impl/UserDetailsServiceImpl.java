package GIDAC.servicios.impl;

import GIDAC.modelo.Usuario;
import GIDAC.repositorios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
//

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = this.usuarioRepository.findByEmail(username);
        if(usuario == null){
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
        return usuario;
    }

}


//package GIDAC.servicios.impl;
//
//import GIDAC.modelo.Usuario;
//import GIDAC.repositorios.UsuarioRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    @Autowired
//    private UsuarioRepository usuarioRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        Usuario usuario = this.usuarioRepository.findByEmail(email);
//        if(usuario == null){
//            throw new UsernameNotFoundException("Usuario no encontrado");
//        }
//        return usuario;
//    }
//    
//    
//    public UserDetails actualizarDatos(Usuario usuario) throws UsernameNotFoundException {
//        return usuarioRepository.save(usuario);
//    }
//    /*
//    public UserDetails actualizarDatos(Usuario usuario){
//        return usuarioRepository.save(usuario);
//    }
//*/
//
//}
