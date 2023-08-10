package GIDAC.repositorios;

import GIDAC.modelo.Variable;
import GIDAC.modelo.VariableView;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VariableRepository extends JpaRepository<Variable,String> {
    @Query(value="SELECT DISTINCT co.codigo_variable_organizacion as idVariable, co.nombre_variable_organizacion as nombreVariable, o.siglas as siglas" +
            "   FROM variable v LEFT OUTER JOIN variable_unidad_medida vum on vum.id_variable=v.id_variable" +
            "	LEFT OUTER JOIN valor_permitido vp ON vp.id_variable_unidad_medida=vum.id_variable_unidad_medida" +
            "	JOIN equivalencia_variable eq ON v.id_variable=eq.id_variable" +
            "	JOIN catalogo_organizacion co ON co.codigo_variable_organizacion=eq.codigo_variable_organizacion" +
            "   JOIN organizacion o ON o.id_organizacion=co.id_organizacion" +
            "   WHERE vum.id_variable IS null and vp.id_variable_unidad_medida IS null",
            nativeQuery=true)
    List<Object[]> obtenerVariablesImcompletas();
    
    @Query(value="SELECT DISTINCT co.codigo_variable_organizacion as idVariable, co.nombre_variable_organizacion as nombreVariable, o.siglas as siglas, tv.nombre_tipo_variable, ce.nombre_variable_espoch, um.abreviatura" +
            "   FROM variable v JOIN variable_unidad_medida vum on vum.id_variable=v.id_variable" +
            "   JOIN unidad_medida um on um.id_unidad_medida=vum.id_unidad_medida"+
            "	JOIN valor_permitido vp ON vp.id_variable_unidad_medida=vum.id_variable_unidad_medida" +
            "   JOIN tipo_variable tv ON tv.id_tipo_variable=v.id_tipo_variable"+
            "   JOIN variable_familia vf ON vf.id_variable=v.id_variable" +
            "	JOIN equivalencia_variable eq ON v.id_variable=eq.id_variable" +
            "	JOIN catalogo_organizacion co ON co.codigo_variable_organizacion=eq.codigo_variable_organizacion" +
            "   JOIN catalogo_espoch ce on ce.codigo_variable_espoch=eq.codigo_variable_espoch"+
            "   JOIN organizacion o ON o.id_organizacion=co.id_organizacion",
            nativeQuery=true)
    List<Object[]> obtenerVariablesCompletasInvestigador();
    
    @Query(value="SELECT DISTINCT co.codigo_variable_organizacion as idVariable, co.nombre_variable_organizacion as nombreVariable, o.siglas as siglas, tv.nombre_tipo_variable" +
            "   FROM variable v JOIN variable_unidad_medida vum on vum.id_variable=v.id_variable" +
            "	JOIN valor_permitido vp ON vp.id_variable_unidad_medida=vum.id_variable_unidad_medida" +
            "   JOIN tipo_variable tv ON tv.id_tipo_variable=v.id_tipo_variable"+
            "   JOIN variable_familia vf ON vf.id_variable=v.id_variable" +
            "	JOIN equivalencia_variable eq ON v.id_variable=eq.id_variable" +
            "	JOIN catalogo_organizacion co ON co.codigo_variable_organizacion=eq.codigo_variable_organizacion" +
            "   JOIN organizacion o ON o.id_organizacion=co.id_organizacion",
            nativeQuery=true)
    List<Object[]> obtenerVariablesCompletas();
    
    @Query(value="SELECT DISTINCT v.id_variable, v.nombre_variable, o.siglas, o.nombre_organizacion" +
                    "	FROM proyecto_investigacion pi JOIN dataset dt on dt.id_proyecto=pi.id_proyecto" +
                    "	JOIN dato_recolectado dr on dt.id_dataset=dr.id_dataset" +
                    "	JOIN variable_unidad_medida vum on dr.id_variable_unidad_medida=vum.id_variable_unidad_medida" +
                    "	JOIN variable v ON vum.id_variable =v.id_variable" +
                    "	JOIN unidad_medida um on um.id_unidad_medida=vum.id_unidad_medida" +
                    "    JOIN variable_familia vf on vf.id_variable = v.id_variable" +
                    "    JOIN familia f on f.id_familia=vf.id_familia" +
                    "    JOIN equivalencia_variable eq on eq.id_variable=v.id_variable" +
                    "    JOIN catalogo_organizacion co on co.codigo_variable_organizacion=eq.codigo_variable_organizacion" +
                    "    JOIN organizacion o on o.id_organizacion=co.id_organizacion" +
                    " WHERE pi.vigencia=1 AND pi.id_estado_proyecto=2",
            nativeQuery=true)
    List<Object[]> obtenerVariablesConDatosSinFamilia();
    
    @Query(value="SELECT DISTINCT v.id_variable, v.nombre_variable, o.siglas, o.nombre_organizacion" +
                    "	FROM proyecto_investigacion pi JOIN dataset dt on dt.id_proyecto=pi.id_proyecto" +
                    "	JOIN dato_recolectado dr on dt.id_dataset=dr.id_dataset" +
                    "	JOIN variable_unidad_medida vum on dr.id_variable_unidad_medida=vum.id_variable_unidad_medida" +
                    "	JOIN variable v ON vum.id_variable =v.id_variable" +
                    "	JOIN unidad_medida um on um.id_unidad_medida=vum.id_unidad_medida" +
                    "    JOIN variable_familia vf on vf.id_variable = v.id_variable" +
                    "    JOIN familia f on f.id_familia=vf.id_familia" +
                    "    JOIN equivalencia_variable eq on eq.id_variable=v.id_variable" +
                    "    JOIN catalogo_organizacion co on co.codigo_variable_organizacion=eq.codigo_variable_organizacion" +
                    "    JOIN organizacion o on o.id_organizacion=co.id_organizacion" +
                " WHERE f.id_familia = :idFamilia AND pi.vigencia=1 AND pi.id_estado_proyecto=2",
            nativeQuery=true)
    List<Object[]> obtenerVariablesConDatosFiltradoPorFamilia(@Param("idFamilia") Integer idFamilia);
    
    @Query(value="SELECT DISTINCT v.id_variable, v.nombre_variable, o.siglas, o.nombre_organizacion" +
                    "	FROM proyecto_investigacion pi JOIN dataset dt on dt.id_proyecto=pi.id_proyecto" +
                    "	JOIN dato_recolectado dr on dt.id_dataset=dr.id_dataset" +
                    "	JOIN variable_unidad_medida vum on dr.id_variable_unidad_medida=vum.id_variable_unidad_medida" +
                    "	JOIN variable v ON vum.id_variable =v.id_variable" +
                    "	JOIN unidad_medida um on um.id_unidad_medida=vum.id_unidad_medida" +
                    "    JOIN variable_familia vf on vf.id_variable = v.id_variable" +
                    "    JOIN familia f on f.id_familia=vf.id_familia" +
                    "    JOIN equivalencia_variable eq on eq.id_variable=v.id_variable" +
                    "    JOIN catalogo_organizacion co on co.codigo_variable_organizacion=eq.codigo_variable_organizacion" +
                    "    JOIN organizacion o on o.id_organizacion=co.id_organizacion" +
                    " WHERE pi.vigencia=1 AND pi.id_proyecto = :idProyecto",
            nativeQuery=true)
    List<Object[]> obtenerVariablesConDatosSinFamiliaInvestigador(@Param("idProyecto") Integer idProyecto);
    
    @Query(value="SELECT DISTINCT v.id_variable, v.nombre_variable, o.siglas, o.nombre_organizacion" +
                    "	FROM proyecto_investigacion pi JOIN dataset dt on dt.id_proyecto=pi.id_proyecto" +
                    "	JOIN dato_recolectado dr on dt.id_dataset=dr.id_dataset" +
                    "	JOIN variable_unidad_medida vum on dr.id_variable_unidad_medida=vum.id_variable_unidad_medida" +
                    "	JOIN variable v ON vum.id_variable =v.id_variable" +
                    "	JOIN unidad_medida um on um.id_unidad_medida=vum.id_unidad_medida" +
                    "    JOIN variable_familia vf on vf.id_variable = v.id_variable" +
                    "    JOIN familia f on f.id_familia=vf.id_familia" +
                    "    JOIN equivalencia_variable eq on eq.id_variable=v.id_variable" +
                    "    JOIN catalogo_organizacion co on co.codigo_variable_organizacion=eq.codigo_variable_organizacion" +
                    "    JOIN organizacion o on o.id_organizacion=co.id_organizacion" +
                " WHERE f.id_familia = :idFamilia AND pi.vigencia=1 AND pi.id_proyecto = :idProyecto",
            nativeQuery=true)
    List<Object[]> obtenerVariablesConDatosFiltradoPorFamiliaInvestigador(@Param("idFamilia") Integer idFamilia, @Param("idProyecto") Integer idProyecto);
    
    
}
