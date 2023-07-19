package GIDAC.repositorios;

import GIDAC.modelo.Altura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlturaRepository extends JpaRepository<Altura,Integer> {
     Altura findByAlturaMinimaAndAlturaMaximaAndUnidadMedida_Abreviatura(float alturaMinima, float alturaMaxima, String abreviatura);
}
