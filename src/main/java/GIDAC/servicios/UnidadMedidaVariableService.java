/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package GIDAC.servicios;


import java.util.List;

/**
 *
 * @author My Notebook
 */
public interface UnidadMedidaVariableService<T>{
    public T guardar(T objeto);
    public T buscarPorId(Integer id);
    public T buscarPorUnidadMedidaAndVariableAndVigencia(Integer idUnidadMedida, Integer idVariable, Boolean vigencia);
    public List<T> buscarTodos(Boolean vigencia);
    public void eliminar(Integer id);
}

