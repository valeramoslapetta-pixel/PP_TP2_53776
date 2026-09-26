package modelo;
import java.io.*;
import java.util.List;
import java.util.ArrayList;
public class EventoUniversitario implements java.io.Serializable{
    private final String id;
    private String titulo;
    private double costoBase;
    private boolean gratuito;
    private Sala sala;
    private List<Actividad> actividades = new ArrayList<>();

    private static int cantidadEventos;

    public EventoUniversitario(String id, String titulo, Double costoBase, boolean gratuito){
        this.id = id;
        this.titulo = titulo;
        this.costoBase = costoBase;
        this.gratuito = gratuito;
        cantidadEventos++;


    }

    public EventoUniversitario(EventoUniversitario otro) {
        this.id = otro.id;
        this.titulo = otro.titulo;
        this.costoBase = otro.costoBase;
        this.gratuito = otro.gratuito;
        cantidadEventos++;
    }

    public double calcularCostoEstimado(){
        if (gratuito){
            return 0.0;
        }
        double costoActividades = 0;
        for(Actividad a : actividades){
            costoActividades+=a.calcularCostoMateriales();
        }
        return (costoBase+costoActividades)*1.21;

    }

    public void mostrarDatos() {
        System.out.println("ID: " + id);
        System.out.println("Titulo: " + titulo);
        System.out.println("Costo Base: $" + costoBase);
        System.out.println("gratuito: " + (gratuito ? "SI" : "NO"));
        System.out.println("Costo estimado: " + calcularCostoEstimado());
        System.out.println("Sala asignada: " + (sala != null ? sala.getNombre() : "sin asignar"));;
        for(Actividad a : actividades){
            a.mostrarInscripciones();
            a.mostrarIdentificacion();
        }
    }

    public static int getCantidadEventos(){
        return cantidadEventos;
    }
    public void asignarSala(Sala sala){
        this.sala = sala;
    }
    public void crearActividad(int id, String titulo, int cupo, String tipo, String disertante, boolean requiereNotebook,int nivel){
        if(tipo.equals("Charla")){
            Actividad nuevaActividad = new Charla(id, titulo, cupo, disertante);
            actividades.add(nuevaActividad);
        }
        if (tipo.equals("Taller")){
            Actividad nuevaActividad = new Taller(id,titulo,cupo,requiereNotebook);
            actividades.add(nuevaActividad);
        }
        if (tipo.equals("Curso")){
            Actividad nuevaActividad = new Curso(id,titulo,cupo,nivel);
            actividades.add(nuevaActividad);
        }
    }
    public List<Actividad> getActividades(){
        return actividades;
    }
    public boolean persistirEvento(){
        try {
            FileOutputStream fos = new FileOutputStream(this.id + ".dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            oos.close();
            fos.close();
            return true;
        }
        catch (FileNotFoundException e) {
            System.out.println("No se encontró el archivo para persistir el evento.");
            return false;
        }
        catch (IOException e) {
            System.out.println("Error de E/S al persistir el evento: " + e.getMessage());
            return false;
        }
    }

    public static EventoUniversitario recuperarEvento(String id){
        EventoUniversitario evento = null;
        try {
            FileInputStream fis = new FileInputStream(id + ".dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            evento = (EventoUniversitario) ois.readObject();
            ois.close();
            fis.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("No se encontró el archivo del evento con id: " + id);
        }
        catch (IOException e) {
            System.out.println("Error de E/S al recuperar el evento: " + e.getMessage());
        }
        catch (ClassNotFoundException e) {
            System.out.println("Error: clase no encontrada al deserializar.");
        }
        return evento;
    }
    public <T extends Actividad> List<T> filtrarActividadesPorTipo(Class<T> tipo){
        List<T> resultado = new ArrayList<>();
        for (Actividad a:actividades){
            if(tipo.isInstance(a)){
                resultado.add(tipo.cast(a));
            }
        }
        return resultado;
    }
    public double calcularCostoMateriales(List<? extends Actividad> actividades){
        double costoActividades = 0;
        for (Actividad a : actividades){
            costoActividades += a.calcularCostoMateriales();
        }
        return costoActividades;
    }
}
