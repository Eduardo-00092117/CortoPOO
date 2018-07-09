 
package conexion;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author UCA
 */
public class Conexion {
    private String user;
    private String pass;
    private String driver;
    private String url;
    
    private Connection cnx;
    
    public static Conexion instance;
    
    public synchronized static Conexion getInstanceConexion(){
        if(instance == null){
            return new Conexion();
        }
        return instance;
    }
    
    private Conexion(){
        cargarCredenciales();
        
        try{
            Class.forName(this.driver);
            cnx = (Connection) DriverManager.getConnection(this.url, this.user, this.pass);
        }catch(Exception ex){
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void cargarCredenciales(){
        user = "root";
        pass = "";
        driver = "com.mysql.jdbc.Driver";
        url = "jdbc:mysql://localhost/inscripciones";
    }
    
    public Connection getCnx(){
        try {
            return (Connection) DriverManager.getConnection(this.url, this.user, this.pass);
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void cerrarConexion(){
        instance = null;
    }
}
