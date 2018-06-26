
package interfaces;

import java.util.ArrayList;

/**
 *
 * @author UCA
 */
public interface metodos <Generic>{
    public boolean create(Generic g);
    public boolean delete(String key);
    public boolean update (Generic c);
    
    public Generic read(String key);
    public ArrayList<Generic> readAll();
}
