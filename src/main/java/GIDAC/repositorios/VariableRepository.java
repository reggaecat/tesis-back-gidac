package GIDAC.repositorios;

import GIDAC.modelo.Variable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VariableRepository extends JpaRepository<Variable,Integer> {
    
}
