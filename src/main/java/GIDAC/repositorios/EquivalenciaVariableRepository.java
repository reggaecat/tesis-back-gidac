package GIDAC.repositorios;

import GIDAC.modelo.EquivalenciaVariable;
import GIDAC.modelo.EquivalenciaVariableId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EquivalenciaVariableRepository extends JpaRepository<EquivalenciaVariable, EquivalenciaVariableId> {
    @Query(value="SELECT DISTINCT vum.id_variable_unidad_medida, v.id_variable, v.nombre_variable, o.siglas, ce.nombre_variable_espoch, tv.nombre_tipo_variable, um.abreviatura" +
                "    FROM variable_unidad_medida vum JOIN unidad_medida um ON vum.id_unidad_medida=um.id_unidad_medida" +
                "    JOIN variable v ON v.id_variable=vum.id_variable" +
                "    JOIN tipo_variable tv ON tv.id_tipo_variable=v.id_tipo_variable" +
                "    JOIN equivalencia_variable ev ON ev.id_variable=v.id_variable" +
                "    JOIN catalogo_espoch ce ON ce.codigo_variable_espoch=ev.codigo_variable_espoch" +
                "    JOIN catalogo_organizacion co ON co.codigo_variable_organizacion=ev.codigo_variable_organizacion" +
                "    JOIN organizacion o ON o.id_organizacion=co.id_organizacion" +
                "    WHERE vum.vigencia=true",
            nativeQuery=true)
    List<Object[]> obtenerVariablesParaCatalogo();
}
