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
    public List<T> buscarPorVigenciaVariable(Boolean vigencia, Integer id);
    public List<T> buscarPorEditable();
    public List<Object[]> listarTodosLosDatos();
    public List<Object[]> listarTodosLosDatosProyecto(Integer idProyecto);
    public List<Object[]> listarTodosLosDatosVariable(Integer idVariable);
}

