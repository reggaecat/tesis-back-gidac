package GIDAC.repositorios;

import GIDAC.modelo.Dataset;
import GIDAC.modelo.DatoRecolectado;
import GIDAC.modelo.Variable;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DatoRecolectadoRepository extends JpaRepository<DatoRecolectado,Integer> {
    List<DatoRecolectado> findByVigenciaAndDataset(Boolean vigencia,Dataset dataset);
    List<DatoRecolectado> findByVigenciaAndVariable(Boolean vigencia, Variable variable);
    List<DatoRecolectado> findByVigenciaAndDatasetProyectoInvestigacionIdProyecto(Boolean vigencia, Integer idProyecto);
    List<DatoRecolectado> findByEditable(Boolean editable);
    
    @Query(value="SELECT p.coordenadaX, p.coordenadaY,   pr.profundidad_maxima, pr.profundidad_minima,m.abreviatura, v.nombre_variable, AVG(d.valor) AS promedioValor" +
            " FROM parcela p" +
            " JOIN profundidad_parcela pp ON p.id_parcela = pp.id_parcela" +
            " JOIN profundidad pr ON pp.id_profundidad = pr.id_profundidad" +
            " JOIN unidad_medida m ON pr.id_unidad_medida = m.id_unidad_medida" +
            " JOIN dataset ds on (ds.id_parcela=pp.id_parcela AND ds.id_profundidad=pp.id_profundidad)" +
            " JOIN proyecto_investigacion pi ON pi.id_proyecto=ds.id_proyecto"+
            " JOIN estado_proyecto_investigacion ep ON ep.id_estado_proyecto=pi.id_estado_proyecto"+
            " JOIN variable v" +
            " JOIN dato_recolectado d ON (d.id_dataset = ds.id_dataset AND d.id_variable=v.id_variable)" +
            " WHERE d.vigencia=true AND pi.vigencia=true AND pi.id_estado_proyecto=2" +
            " GROUP BY p.coordenadaX, p.coordenadaY,  pr.profundidad_maxima,pr.profundidad_minima, m.abreviatura, v.nombre_variable"+
            " ORDER BY 3 ASC",
            nativeQuery=true)
    List<Object[]> obtenerPromedioValores();
    
    @Query(value="SELECT p.coordenadaX, p.coordenadaY, pr.profundidad_maxima, pr.profundidad_minima, m.abreviatura, v.nombre_variable, AVG(d.valor) AS promedioValor" +
            " FROM parcela p" +
            " JOIN profundidad_parcela pp ON p.id_parcela = pp.id_parcela" +
            " JOIN profundidad pr ON pp.id_profundidad = pr.id_profundidad" +
            " JOIN unidad_medida m ON pr.id_unidad_medida = m.id_unidad_medida" +
            " JOIN dataset ds on (ds.id_parcela=pp.id_parcela AND ds.id_profundidad=pp.id_profundidad)" +
            " JOIN proyecto_investigacion pi ON pi.id_proyecto=ds.id_proyecto"+
            " JOIN estado_proyecto_investigacion ep ON ep.id_estado_proyecto=pi.id_estado_proyecto"+
            " JOIN variable v" +
            " JOIN dato_recolectado d ON (d.id_dataset = ds.id_dataset AND d.id_variable=v.id_variable)" +
            " WHERE d.vigencia=true AND pi.vigencia=true AND pi.id_estado_proyecto=2 AND pi.id_proyecto= :idProy" +
            " GROUP BY p.coordenadaX, p.coordenadaY, pr.profundidad_maxima,pr.profundidad_minima, m.abreviatura, v.nombre_variable"+
            " ORDER BY 3",
            nativeQuery=true)
    List<Object[]> obtenerPromedioValoresProyecto(@Param("idProy") Integer idProy);
    
     @Query(value="SELECT p.coordenadaX, p.coordenadaY, pr.profundidad_maxima, pr.profundidad_minima, m.abreviatura, v.nombre_variable, AVG(d.valor) AS promedioValor" +
            " FROM parcela p" +
            " JOIN profundidad_parcela pp ON p.id_parcela = pp.id_parcela" +
            " JOIN profundidad pr ON pp.id_profundidad = pr.id_profundidad" +
            " JOIN unidad_medida m ON pr.id_unidad_medida = m.id_unidad_medida" +
            " JOIN dataset ds on (ds.id_parcela=pp.id_parcela AND ds.id_profundidad=pp.id_profundidad)" +
            " JOIN proyecto_investigacion pi ON pi.id_proyecto=ds.id_proyecto"+
            " JOIN estado_proyecto_investigacion ep ON ep.id_estado_proyecto=pi.id_estado_proyecto"+
            " JOIN dato_recolectado d ON d.id_dataset = ds.id_dataset" +
            " JOIN variable v on v.id_variable= d.id_variable"+
            " WHERE d.vigencia=true AND pi.vigencia=true AND pi.id_estado_proyecto=2 AND v.id_variable = :idVar" +
            " GROUP BY p.coordenadaX, p.coordenadaY, pr.profundidad_maxima,pr.profundidad_minima, m.abreviatura, v.nombre_variable"+
             " ORDER BY 3",
            nativeQuery=true)
    List<Object[]> obtenerPromedioValoresCatalogo(@Param("idVar") Integer idvariable);
    
    
    


}
