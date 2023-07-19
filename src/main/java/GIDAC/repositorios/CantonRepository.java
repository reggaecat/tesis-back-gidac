package GIDAC.repositorios;

import GIDAC.modelo.Canton;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CantonRepository extends JpaRepository<Canton,String> {
    List<Canton> findByProvinciaCodigoProvincia(String codigoProvincia);
}
