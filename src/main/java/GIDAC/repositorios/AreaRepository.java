package GIDAC.repositorios;


import GIDAC.modelo.Area;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AreaRepository extends JpaRepository<Area,Integer> {
     Area findByAreaAndUnidadMedida_Abreviatura(float area, String abreviatura);
}
