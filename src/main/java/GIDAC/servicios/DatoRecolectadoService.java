/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package GIDAC.servicios;


import GIDAC.modelo.DatoRecolectado;
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
    public List<T> buscarPorVigenciaDataset(Boolean vigencia, Integer id);
    public List<T> buscarPorVigenciaProyecto(Boolean vigencia, Integer id);
    public List<T> buscarPorVigenciaVariable(Boolean vigencia, String id);
    public List<T> buscarPorEditable();
    public List<Object[]> listarTodosLosDatosNumerico();
    public List<Object[]> listarTodosLosDatosProyectoNumerico(Integer idProyecto);
    public List<Object[]> listarTodosLosDatosVariableNumerico(String idVariable);
    public List<Object[]> listarTodosLosDatosProyectoVariableNumerico(Integer idProyecto, String idVariable);
    public List<Object[]> listarTodosLosDatosNominal();
    public List<Object[]> listarTodosLosDatosProyectoNominal(Integer idProyecto);
    public List<Object[]> listarTodosLosDatosVariableNominal(String idVariable);
    public List<Object[]> listarTodosLosDatosProyectoVariableNominal(Integer idProyecto, String idVariable);
}

