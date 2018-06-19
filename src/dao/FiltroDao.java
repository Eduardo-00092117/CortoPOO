
package dao;
/**
 *
 * @author UCA
 */
import conexion.Conexion;
import interfaces.metodos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import modelo.Filtro;

public class FiltroDao implements metodos<Filtro>{
    private static final String SQL__INSERT = "INSERT INTO alumnos (carnet, nombres, apellidos, edad, universidad, estado) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SQL__UPDATE = "UPDATE alumnos SET nombres=?, apellidos=?, edad=?, universidad=?, estado=? WHERE carnet=?";
    private static final String SQL__DELETE = "DELETE FROM alumnos WHERE carnet=?";
    private static final String SQL__READ = "SELECT * FROM alumnos WHERE carnet=?";
    private static final String SQL__READALL = "SELECT * FROM alumnos";
    
    private static final Conexion con = Conexion.conectar();
    
    @Override
    public boolean create(Filtro g) {
        PreparedStatement ps;
        try{
            ps = con.getCnx().prepareStatement(SQL__INSERT);
            ps.setInt(1, g.getCarnet());
            ps.setString(2, g.getNombres());
            ps.setString(3, g.getApellidos());
            ps.setInt(4, g.getEdad());
            ps.setString(5, g.getUniversidad());
            ps.setBoolean(6, g.getEstado());
            if(ps.executeUpdate() > 0){
                return true;
            }
        }catch(Exception ex){
            
        }finally{
            con.cerrarConexion();
        }
        return false;
    }

    @Override
    public boolean delete(int key) {
        PreparedStatement ps;
        try{
            ps = con.getCnx().prepareStatement(SQL__DELETE);
            ps.setInt(1, key);
            if(ps.executeUpdate() > 0){
                return true;
            }
        }catch(Exception ex){
            
        }finally{
            con.cerrarConexion();
        }
        return false;
    }

    @Override
    public boolean update(Filtro c) {
        PreparedStatement ps;
        try{
            ps = con.getCnx().prepareStatement(SQL__UPDATE);
            ps.setString(1, c.getNombres());
            ps.setString(2, c.getApellidos());
            ps.setInt(3, c.getEdad());
            ps.setString(4, c.getUniversidad());
            ps.setBoolean(5, c.getEstado());
            ps.setInt(6, c.getCarnet());
            if(ps.executeUpdate() > 0){
                return true;
            }
        }catch(Exception ex){
            
        }finally{
            con.cerrarConexion();
        }
        return false;
    }

    @Override
    public Filtro read(int key) {
        Filtro f = null;
        PreparedStatement ps;
        ResultSet rs;
        try{
            ps = con.getCnx().prepareStatement(SQL__READ);
            ps.setInt(1, key);
            rs = ps.executeQuery();
            
            while(rs.next()){
                //int id, String nombre, String apellido, String universidad, int edad, boolean estado, int carnet;
                f = new Filtro(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6), rs.getBoolean(7));
            }
            rs.close();
        }catch(Exception ex){}
        finally{
            con.cerrarConexion();
        }
        return f;
    }

    @Override
    public ArrayList<Filtro> readAll() {
        ArrayList<Filtro> all = new ArrayList();
        Statement s;
        ResultSet rs;
        try{
            s = con.getCnx().prepareStatement(SQL__READALL);
            rs = s.executeQuery(SQL__READALL);
            while(rs.next()){
                all.add(new Filtro(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6), rs.getBoolean(7)));
            }
            rs.close();
        }catch(Exception ex){}
        return all;
    }
}
