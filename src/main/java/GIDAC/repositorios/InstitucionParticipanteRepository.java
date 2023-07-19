package GIDAC.repositorios;

import GIDAC.modelo.Institucion;
import GIDAC.modelo.InstitucionParticipante;
import GIDAC.modelo.InstitucionParticipanteId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstitucionParticipanteRepository extends JpaRepository<InstitucionParticipante,InstitucionParticipanteId> {
}
