/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import modelo.Filtro;

/**
 *
 * @author Roberto
 */
public class FiltroDao extends BaseDao<Filtro> {

    public FiltroDao(){
        tabla = new DatosTabla(
        "alumnos","id", new String[]{"carnet", "nombres", "apellidos", "edad", "universidad", "estado"}
        );
    }
    @Override
    Filtro mapaObjeto(ResultSet resultSet) {
        //Creo el tipo objeto o sea la INSTANCIA
        Filtro filtro = new Filtro();
        try{
            filtro.setId(resultSet.getInt(tabla.PRIMARY_KEY));
            filtro.setCarnet(resultSet.getString(tabla.fields[0]));
            filtro.setNombres(resultSet.getString(tabla.fields[1]));
            filtro.setApellidos(resultSet.getString(tabla.fields[2]));
            filtro.setEdad(resultSet.getInt(tabla.fields[3]));
            filtro.setUniversidad(resultSet.getString(tabla.fields[4]));
            filtro.setEstado(resultSet.getBoolean(tabla.fields[5]));
        }
        catch(SQLException error){
            error.printStackTrace();
        }
        return filtro;
    }

    @Override
    PreparedStatement getSelectStatement(Connection con, Filtro find, String by) {
       String query = "SELECT * FROM "+tabla.TABLE_NAME+" WHERE "+by+"=?";
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = con.prepareStatement(query);
            if(by.equals(tabla.PRIMARY_KEY)){
                preparedStatement.setInt(1, find.getId());
            }
            else if(by.equals(tabla.fields[0])){
                preparedStatement.setString(1, find.getCarnet());
            }
            
            else if(by.equals(tabla.fields[1])){
                preparedStatement.setString(1, "%"+find.getNombres()+"%");
            }
            else if(by.equals(tabla.fields[2])){
                preparedStatement.setString(1, "%"+find.getApellidos()+"%");
            }
            
            else if(by.equals(tabla.fields[3])){
                preparedStatement.setInt(1, find.getEdad());
            }
            
            else if(by.equals(tabla.fields[4])){
                preparedStatement.setString(1, "%"+find.getUniversidad()+"%");
            }
            
            else if(by.equals(tabla.fields[5])){
                preparedStatement.setBoolean(1, find.getEstado());
            }

        }
        catch(SQLException error){
            error.printStackTrace();
        }
        return preparedStatement;
    }

    @Override
    PreparedStatement getInsertStatement(Connection con, Filtro _new) {
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement=con.prepareStatement(
            "INSERT INTO "+ tabla.TABLE_NAME+" ("+tabla.fields[0]+" , "+tabla.fields[1]+" , "+tabla.fields[2]+" , "+tabla.fields[3]
                    +" , "+tabla.fields[4]+" , "+tabla.fields[5]+") VALUES(?,?,?,?,?,?)"  
            );
            preparedStatement.setString(1, _new.getCarnet());
            preparedStatement.setString(2, _new.getNombres());
            preparedStatement.setString(3, _new.getApellidos());
            preparedStatement.setInt(4, _new.getEdad());
            preparedStatement.setString(5, _new.getUniversidad());
            preparedStatement.setBoolean(6, _new.getEstado());
        }
        catch(SQLException error){
            error.printStackTrace();
        }
        return preparedStatement;
    }

    @Override
    PreparedStatement getUpdateStatement(Connection con, Filtro _new) {
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement=con.prepareStatement(
                    "UPDATE "+tabla.TABLE_NAME+" SET "+tabla.fields[1]+" = ?,"+tabla.fields[2]+"= ?,"+tabla.fields[3]
            +" = ?,"+tabla.fields[4]+" = ?,"+tabla.fields[5]+" = ? WHERE "+tabla.fields[0]   + " = ?"
            );
            
            preparedStatement.setString(1, _new.getNombres());
            preparedStatement.setString(2, _new.getApellidos());
            preparedStatement.setInt(3, _new.getEdad());
            preparedStatement.setString(4, _new.getUniversidad());
            preparedStatement.setBoolean(5, _new.getEstado());
            preparedStatement.setString(6, _new.getCarnet());
        }
        catch(SQLException error){
            error.printStackTrace();
        }
        return preparedStatement;
    }

    @Override
    PreparedStatement getDeleteStatement(Connection con, Filtro deleteObject) {
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = con.prepareStatement(
                    "DELETE FROM "+tabla.TABLE_NAME+" WHERE "+tabla.fields[0]+" = ?");
            preparedStatement.setString(1, deleteObject.getCarnet()); 
        }
        catch(SQLException error){
            error.printStackTrace();
        }
        return preparedStatement;
    }
    
    public List<Filtro> findByIdProducto(Filtro filtro){
        return findBy(filtro,tabla.PRIMARY_KEY);
    }
    public List<Filtro> findByCarnet(Filtro filtro){
        return findBy(filtro,tabla.fields[0]);
    }
    public List<Filtro> findByNombre(Filtro filtro){
        return findBy(filtro,tabla.fields[1]);
    }
    public List<Filtro> findByApellido(Filtro filtro){
        return findBy(filtro,tabla.fields[2]);
    }
    public List<Filtro> findByEdad(Filtro filtro){
        return findBy(filtro,tabla.fields[3]);
    }
    public List<Filtro> findByUniversidad(Filtro filtro){
        return findBy(filtro,tabla.fields[4]);
    }
    public List<Filtro> findByEstado(Filtro filtro){
        return findBy(filtro,tabla.fields[5]);
    }
}
