
package interfaces;

import java.util.ArrayList;

/**
 *
 * @author UCA
 */
public interface metodos <Generic>{
    public boolean create(Generic g);
    public boolean delete(int key);
    public boolean update (Generic c);
    
    public Generic read(int key);
    public ArrayList<Generic> readAll();
}
