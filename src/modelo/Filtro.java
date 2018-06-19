
package modelo;

/**
 *
 * @author UCA
 */
public class Filtro {
     private int id;
    private String  nombres;
    private String apellidos;
    private int carnet;
    private int edad;
    private String universidad;
    private boolean estado;    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getCarnet() {
        return carnet;
    }

    public void setCarnet(int carnet) {
        this.carnet = carnet;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getUniversidad() {
        return universidad;
    }

    public void setUniversidad(String universidad) {
        this.universidad = universidad;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
        
    
    public Filtro(){}
    public Filtro(int id, int carnet, String nombre, String apellido, int edad, String universidad, boolean estado){
        this.id = id;
        this.carnet = carnet;
        this.nombres = nombre;
        this.edad = edad;
        this.estado = estado;
        this.apellidos = apellido;
        this.universidad = universidad;
    }
    
    public Filtro(String nombre, String apellido, String universidad, int edad, boolean estado, int carnet){
        this.carnet = carnet;
        this.nombres = nombre;
        this.edad = edad;
        this.estado = estado;
        this.apellidos = apellido;
        this.universidad = universidad;
    }
    
    /*public Filtro(String marca, int stock, boolean existencia){
        this.marca = marca;
        this.stock = stock;
        this.existencia = existencia;
    }*/
}
