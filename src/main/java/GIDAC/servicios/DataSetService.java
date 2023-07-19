/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package GIDAC.servicios;


import GIDAC.modelo.Dataset;
import java.util.List;

/**
 *
 * @author My Notebook
 */
public interface DataSetService<T>{
    public T guardar(T objeto);
    public T buscarPorId(Integer id);
    public T buscarPorParcelaProfundidad(Integer idParcela, Integer idProfundidad);
    public List<T> buscarTodos();
    public void eliminar(Integer id);
    public List<T> buscarPorParcela(Integer id);
    
}
