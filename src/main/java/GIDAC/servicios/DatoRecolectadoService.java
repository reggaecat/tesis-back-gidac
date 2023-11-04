/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package GIDAC.servicios;


import GIDAC.modelo.DatoRecolectado;
import java.math.BigInteger;
import java.util.List;

/**
 *
 * @author My Notebook
 */
public interface DatoRecolectadoService<T>{
    public T guardar(T objeto);
    public T buscarPorId(Integer id);
    public List<T> buscarTodos();
    public void eliminar(Integer id);
    public List<T> buscarPorVigenciaAndVigenciaDatasetAndIdParcela(Boolean vigenciaAux ,Boolean vigencia, Integer id);
    public List<T> buscarPorVigenciaDataset(Boolean vigencia, Integer id);
    public List<T> buscarPorVigenciaProyecto(Boolean vigencia, Integer id);
//    public List<T> buscarPorVigenciaVariable(Boolean vigencia, Integer id);
    public List<T> buscarPorVigenciaVariableUnidadMedida(Boolean vigencia, Integer id);
    public List<T> buscarPorEditable();
    
    
    public List<Object[]> listarTodosLosDatosNumerico();
    public List<Object[]> listarTodosLosDatosNominal();
    
    public List<Object[]> listarTodosLosDatosProyectoNumerico(Integer idProyecto);
    public List<Object[]> listarTodosLosDatosProyectoNominal(Integer idProyecto);
    
    public List<Object[]> listarTodosLosDatosNumericoVariable(Integer idVariable);
    public List<Object[]> listarTodosLosDatosNominalVariable(Integer idVariable);
    
    public List<Object[]> listarTodosLosDatosProyectoNumericoVariable(Integer idProyecto, Integer idVariable);
    public List<Object[]> listarTodosLosDatosProyectoNominalVariable(Integer idProyecto, Integer idVariable);
    
    
    public List<Object[]> listarTodosLosDatosVariableNumerico(Integer idVariable);
    public List<Object[]> listarTodosLosDatosProyectoVariableNumerico(Integer idProyecto, Integer idVariable);
    
    
    public List<Object[]> listarTodosLosDatosVariableNominal(Integer idVariable);
    public List<Object[]> listarTodosLosDatosProyectoVariableNominal(Integer idProyecto, Integer idVariable);
    public List<Object[]> obtenerAlturasMasUsuadas();
    public List<Object[]> obtenerUnidadesDeMedidaMasUsuadas();
    public List<Object[]> obteneProfundidadesMasUsuadas();
    public List<Object[]> obteneValorPromedioVariablesNumericas();
    public List<Object[]> obteneCantidadDatosPorVariable();
    public List<Object[]> obtenerCantidadProyectosPorAreaInvestigacion();
    public List<Object[]> obtenerCantidadProyectosPorSectorImpacto();
    public List<Object[]> obtenerCantidadProyectosPorLineaInvestigacion();
    public List<Object[]> obtenerCantidadProyectosPorTipoProyecto();
    public List<Object[]> obtenerCantidadProyectosPorTipoInvestigacion();
    public List<Object[]> obtenerCantidadProyectosPorEstadoProyecto();
    public List<Object[]> obtenerCantidadProyectosPorDirector();
    
    public List<Object[]> obtenerCantidadProyectosPorAreaInvestigacionNulos();
    public List<Object[]> obtenerCantidadProyectosPorSectorImpactoNulos();
    public List<Object[]> obtenerCantidadProyectosPorLineaInvestigacionNulos();
    public List<Object[]> obtenerCantidadProyectosPorTipoProyectoNulos();
    public List<Object[]> obtenerCantidadProyectosPorTipoInvestigacionNulos();
    public List<Object[]> obtenerCantidadProyectosPorEstadoProyectoNulos();
    public List<Object[]> obtenerCantidadProyectosPorDirectorNulos();
    
    
    public BigInteger obtenerCantidadProyectosPorEstadoPublico(Integer idUsuario);
    public BigInteger obtenerCantidadProyectosPorEstadoPrivado(Integer idUsuario);
    public BigInteger obtenerCantidadProyectosVigentes(Integer idUsuario);
    public BigInteger obtenerCantidadProyectosEliminados(Integer idUsuario);
    public BigInteger obtenerCantidadSolicitudesDescarga(Integer idUsuario);
    public BigInteger obtenerCantidadSolicitudesDescargaSolicitado(Integer idUsuario);
    public BigInteger obtenerCantidadSolicitudesDescargaAprobadas(Integer idUsuario);
    public BigInteger obtenerCantidadSolicitudesDescargaRechazadas(Integer idUsuario);
    public BigInteger obtenerCantidadSolicitudesActualizar(Integer idUsuario);
    public BigInteger obtenerCantidadSolicitudesActualizarSolicitado(Integer idUsuario);
    public BigInteger obtenerCantidadSolicitudesActualizarAceptado(Integer idUsuario);
    public BigInteger obtenerCantidadSolicitudesRechazado(Integer idUsuario);
    
    public List<Object[]> obtenerGraficaSolicitudesPorAnio(Integer idUsuario);
    public List<Object[]> obtenerGraficaSolicitudesPorMes(Integer idUsuario);
    public List<Object[]> obtenerGraficaSolicitudesPorDia(Integer idUsuario);
    
    public List<Object[]> obtenerGraficaSolicitudesActualizarPorAnio(Integer idUsuario);
    public List<Object[]> obtenerGraficaSolicitudesActualizarPorMes(Integer idUsuario);
    public List<Object[]> obtenerGraficaSolicitudesActualizarPorDia(Integer idUsuario);
    
    public List<Object[]> obtenerGraficaAccesoPorAnio();
    public List<Object[]> obtenerGraficaAccesoPorMes();
    public List<Object[]> obtenerGraficaAccesoPorDia();
    
    public List<Object[]> obtenerGraficaUsuariosPorRol();
    
    
    public BigInteger obtenerCantidadSolicitudesActualizarInvestigador(Integer idUsuario);
    public BigInteger obtenerCantidadSolicitudesActualizarAcesptadoInvestigador(Integer idUsuario);
    public BigInteger obtenerCantidadSolicitudesActualizarRechazadoInvestigador(Integer idUsuario);
    
   
}

