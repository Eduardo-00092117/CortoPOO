/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Eduardo Alberto López Torres <Carnet: 00092117>
 */
public abstract class BaseDao<T> implements DAO<T> {

    protected DatosTabla tabla;

    class DatosTabla {

        final String TABLE_NAME;
        final String PRIMARY_KEY;
        final String fields[];

        public DatosTabla(String table_name, String primary_key, String[] fields) {
            TABLE_NAME = table_name;
            PRIMARY_KEY = primary_key;
            this.fields = fields;
        }
    }

    private void cerrarConexion(Connection con) {
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public BaseDao() {

    }

    public BaseDao(DatosTabla tabla) {
        this.tabla = tabla;
    }

    abstract T mapaObjeto(ResultSet resultSet);

    abstract PreparedStatement getSelectStatement(Connection con, T find, String by);

    abstract PreparedStatement getInsertStatement(Connection con, T _new);
    
    abstract PreparedStatement getUpdateStatement(Connection con, T _new);

    abstract PreparedStatement getDeleteStatement(Connection con, T deleteObject);

    @Override
    public List<T> findAll() {
        Connection con = null;
        ArrayList<T> listObject = new ArrayList<>(); // Result in Objet format
        try {
            con = this.con.getCnx(); //Conect to database
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tabla.TABLE_NAME);

            while (resultSet.next()) {
                T row = mapaObjeto(resultSet);
                listObject.add(row);
            }

            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion(con);
        }
        return listObject;
    }

    @Override
    public List<T> findBy(T find, String by) {
        Connection con = null;
        ArrayList<T> list = new ArrayList<>();
        try {
            con = this.con.getCnx();
            PreparedStatement preparedStatement = getSelectStatement(con, find, by);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                T row = mapaObjeto(resultSet);
                list.add(row);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion(con);
        }
        return list;
    }

    @Override
    public boolean insert(T insertObject) {
        Connection con = null;
        boolean inserted = false;
        try {
            con = this.con.getCnx();
            PreparedStatement preparedStatement = getInsertStatement(con, insertObject);
            inserted = preparedStatement.execute();

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } finally {
            cerrarConexion(con);
        }
        return inserted;
    }

    @Override
    public boolean update(T updateObject) {
        Connection con = null;
        boolean updated = false;
        try {
            con = this.con.getCnx();
            PreparedStatement preparedStatement = getUpdateStatement(con, updateObject);
            updated = preparedStatement.execute();

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion(con);
        }
        return updated;
    }

    @Override
    public boolean delete(T deleteObject) {
        Connection con = null;
        boolean delete = false;
        try {
            con = this.con.getCnx();
            PreparedStatement preparedStatement = getDeleteStatement(con, deleteObject);
            if (preparedStatement.executeUpdate() > 0) {
                delete = true;
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion(con);
        }
        return delete;
    }

    protected Date getDate(java.util.Date date) {
        return new Date(date.getTime());
    }


}
