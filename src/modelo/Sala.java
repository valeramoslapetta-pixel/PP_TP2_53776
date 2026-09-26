package modelo;

public class Sala implements java.io.Serializable{
    private final int id;
    private String nombre;

    public Sala(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
    public void mostrarDatos(){
        System.out.println("El id de la sala es " + id);
        System.out.println("el nombre de la Sala es " + nombre);
    }

    public int getId() {
        return id;
    }
    public String getNombre(){
        return nombre;
    }
}