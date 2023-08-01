/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package GIDAC.servicios;


import GIDAC.modelo.Variable;
import java.util.List;

/**
 *
 * @author My Notebook
 */
public interface VariableService<T>{
    public T guardar(T objeto);
    public T buscarPorId(String id);
    public List<T> buscarTodos();
    public List<Object[]> litsarVairbalesCompletas();
    public List<Object[]> litsarVairbalesConDatosSinFamilia();
    public List<Object[]> litsarVairbalesConDatosConFiltroFamilia(Integer idFamilia);
    public List<Object[]> litsarVairbalesConDatosSinFamiliaInvestigador(Integer idProyecto);
    public List<Object[]> litsarVairbalesConDatosConFiltroFamiliaInvestigador(Integer idFamilia, Integer idProyecto);
    public List<Object[]> litsarVairbalesIncompletas();
    public void eliminar(String id);
}