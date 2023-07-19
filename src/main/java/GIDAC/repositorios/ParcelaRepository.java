package GIDAC.repositorios;

import GIDAC.modelo.Conglomerado;
import GIDAC.modelo.Parcela;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParcelaRepository extends JpaRepository<Parcela ,Integer> {
    List<Parcela> findByConglomerado(Conglomerado conglomerado);
    Parcela findByCodigoParcelaAndConglomerado(String codigoConglomerado,Conglomerado conglomerado);
    
}
