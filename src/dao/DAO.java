/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import conexion.Conexion;
import java.util.List;
/**
 *
 * @author Eduardo Alberto López Torres <Carnet: 00092117>
 */
public interface DAO<T> {
    Conexion con= Conexion.getInstanceConexion();
    List<T> findAll();
    List<T> findBy(T buscar, String destinatario);
    boolean insert(T insertarObjeto);
    boolean delete(T borrarObjeto);
    boolean update(T actualizarObjeto);
}
