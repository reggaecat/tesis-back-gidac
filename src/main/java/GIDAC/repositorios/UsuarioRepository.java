package GIDAC.repositorios;

import GIDAC.modelo.Rol;
import GIDAC.modelo.Usuario;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

    public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {

    public Usuario findByEmail(String email);
    
    public List<Usuario> findByVigenciaAndRol(Boolean vigencia, Rol rol);
    //public Usuario findByUsername(String user);
    @Query(value="SELECT r.nombre_rol as rol, COUNT(u.id_usuario) AS cantidadUsuario FROM Rol r JOIN Usuario u ON r.id_rol = u.id_rol WHERE u.vigencia = true GROUP BY r.nombre_rol",nativeQuery=true)
    List<Object[]> obtenerCantidadUsuarioPorRol();
    
    //
//    @Query(value="SELECT nombre_rol, COUNT(id_usuario) AS cantidaUsuarios " +
//    
//            "FROM Rol r " +
//            "JOIN Usuario u" +
//            "WHERE r.id_rol=u.id_rol AND vigencia = true " +
//            "GROUP BY nombre_rol",
//            nativeQuery=true
//    )
    
}
