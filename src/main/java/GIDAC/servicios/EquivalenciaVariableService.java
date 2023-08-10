/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package GIDAC.servicios;


import GIDAC.modelo.EquivalenciaVariable;
import java.util.List;

/**
 *
 * @author My Notebook
 */
public interface EquivalenciaVariableService<T>{
    public T guardar(T objeto);
    public List<T> buscarTodos();
    public List<Object[]> listarCatalogoParaPerfilado();
    public List<Object[]> listarCatalogoParaPerfiladoPorProyecto(Integer id);
    //public List<T> buscarPorProyecto(Integer idProyecto);
}

