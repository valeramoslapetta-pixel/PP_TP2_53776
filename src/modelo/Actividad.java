package modelo;
import excepciones.CupoExcedidoException;

import java.util.List;
import java.util.ArrayList;
public abstract class Actividad implements java.io.Serializable{
    private int id;
    private String titulo;
    private int cupoMax;
    public static final int CUPO_MINIMO = 5;
    private List<Estudiante> estudiantesInscriptos = new ArrayList<>();
    private List<Inscripcion> inscripciones = new ArrayList<>();
     public Actividad(int id, String titulo, int cupoMax){
         this.id = id;
         this.titulo= titulo;
         this.cupoMax = cupoMax;
     }
     public Inscripcion inscribir(Estudiante estudiante)throws CupoExcedidoException{
         if (estudiantesInscriptos.size()>=cupoMax){
             throw new CupoExcedidoException("el cupo maximo de esta actividad fue excedido.");
         }
         estudiantesInscriptos.add(estudiante);
         Inscripcion nuevaInscripcion = new Inscripcion("pendiente");
         inscripciones.add(nuevaInscripcion);
         return nuevaInscripcion;
     }
    public void mostrarInscripciones(){
        for (Inscripcion i : inscripciones){
            System.out.println("Fecha: "+i.getFecha());
            System.out.println("Estado: "+ i.getEstado());
        }
    }
    public List<Inscripcion> getInscripciones() {
        return inscripciones;
    }
    public abstract double calcularCostoMateriales();
     public abstract String getTipo();
public final void mostrarIdentificacion(){
    System.out.println("Actividad #"+id+" - "+titulo+" ("+getTipo()+")");
}

}
