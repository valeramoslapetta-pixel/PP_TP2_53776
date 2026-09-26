import certificacion.Certificable;
import excepciones.CupoExcedidoException;
import modelo.Estudiante;
import modelo.EventoUniversitario;
import modelo.Sala;
import modelo.Actividad;
import modelo.Charla;
import modelo.Taller;
import modelo.Curso;
import java.util.List;
import java.util.ArrayList;
import modelo.Inscripcion;
import hilos.EnvioTicketsThread;
public class App {

    public static void main(String[] args) {
        Sala sala1 = new Sala(1234, "matematicas");
        Sala sala2 = new Sala(7896, "lengua");
        List<Estudiante> estudiantes = new ArrayList<>();
        Estudiante e1 = new Estudiante("Juan pablo", "53444");
        Estudiante e2 = new Estudiante("Santino", "67890");
        Estudiante e3 = new Estudiante("Martin", "53445");
        Estudiante e4 = new Estudiante("Pablo", "53447");
        Estudiante e5 = new Estudiante("Juan", "53446");
        estudiantes.add(e1);
        estudiantes.add(e2);
        estudiantes.add(e3);
        estudiantes.add(e4);
        estudiantes.add(e5);
        EventoUniversitario evento1 = new EventoUniversitario("EV001", "Clase de Matematica", 1500.0, false);
        EventoUniversitario evento2 = new EventoUniversitario("EV002", "Clase de fisica", 0.0, true);
        EventoUniversitario evento3 = new EventoUniversitario("EV003","Curso de marketing",5000.0,false);
        evento1.asignarSala(sala1);
        evento2.asignarSala(sala2);
        evento3.asignarSala(sala2);
        evento1.crearActividad(1245, "consulta", 2, "Charla", "Daniel", false,0);
        evento1.crearActividad(1246, "taller de programacion", 10, "Taller", null, true, 0);
        evento1.crearActividad(1247, "curso de ingles", 15, "Curso", null, false, 2);
        evento2.crearActividad(5463, "charla ted", 30, "Taller", null, true,0);
        evento3.crearActividad(4589,"curso de marketing",50,"Curso",null,false,3);
        EventoUniversitario copiaEvento1 = new EventoUniversitario(evento1);
        try {
            evento1.getActividades().get(0).inscribir(e1);
            Actividad actividadCharla = evento1.getActividades().get(0);
            if (actividadCharla instanceof Certificable) {
                Certificable certificable = (Certificable) actividadCharla;
                System.out.println(certificable.generarCertificado(e1));
            } else {
                System.out.println("La actividad '" + actividadCharla.getTipo() + "' no es certificable.");
            }
        } catch (CupoExcedidoException e) {
            System.out.println("No se pudo inscribir:" + e.getMessage());
        }
        try {
            evento1.getActividades().get(0).inscribir(e2);
        } catch (CupoExcedidoException e) {
            System.out.println("No se pudo inscribir:" + e.getMessage());
        }
        try {
            evento1.getActividades().get(0).inscribir(e3);
        } catch (CupoExcedidoException e) {
            System.out.println("No se pudo inscribir:" + e.getMessage());
        }
        try {
            evento2.getActividades().get(0).inscribir(e2);
            Actividad actividadTaller = evento2.getActividades().get(0);
            if (actividadTaller instanceof Certificable) {
                Certificable certificable = (Certificable) actividadTaller;
                String certificado = certificable.generarCertificado(e2);
                System.out.println(certificado);
            }
        } catch (CupoExcedidoException e) {
            System.out.println("No se pudo inscribir:" + e.getMessage());
        }
        try{
            evento3.getActividades().get(0).inscribir(e5);
            Actividad actividad = evento3.getActividades().get(0);
            if(actividad instanceof Certificable){
                Certificable certificable = (Certificable) actividad;
                String certificado = certificable.generarCertificado(e5);
                System.out.println(certificado);
            }else{
                System.out.println("Esta actividad no es certificable");
            }
        } catch (CupoExcedidoException e) {
            System.out.println("No se pudo inscribir: "+e.getMessage());
        }
        try{
            evento2.getActividades().get(0).inscribir(e5);
            System.out.println("Estudiante Inscripto correctamente");
            boolean persistido = evento2.persistirEvento();
            if (persistido){
                System.out.println("Evento persistido correctamente.");
            } else{
                System.out.println("No se pudo persistir el Evento.");
            }
            EventoUniversitario eventoRecuperado = EventoUniversitario.recuperarEvento("EV002");
            if (eventoRecuperado!=null){
                System.out.println("Evento recuperado desde archivo");
                eventoRecuperado.mostrarDatos();
            }else{
                System.out.println("no se pudo recuperar el evento.");
            }
        } catch (CupoExcedidoException e) {
            System.out.println("No se pudo inscribir al estudiante"+e.getMessage());
        } catch (Exception e) {
            System.out.println("Ocurrio un error inesperado"+e.getMessage());
        } finally {
            System.out.println("proceso finalizado");
        }
        try{
            evento1.getActividades().get(0).inscribir(e4);
            System.out.println("Estudiante Inscripto correctamente");
            boolean persistido = evento1.persistirEvento();
            if (persistido){
                System.out.println("Evento persistido correctamente.");
            } else{
                System.out.println("No se pudo persistir el Evento.");
            }
            EventoUniversitario eventoRecuperado = EventoUniversitario.recuperarEvento("EV001");
            if (eventoRecuperado!=null){
                System.out.println("Evento recuperado desde archivo");
                eventoRecuperado.mostrarDatos();
            }else{
                System.out.println("no se pudo recuperar el evento.");
            }
        } catch (CupoExcedidoException e) {
            System.out.println("No se pudo inscribir al estudiante"+e.getMessage());
        } catch (Exception e) {
            System.out.println("Ocurrio un error inesperado"+e.getMessage());
        } finally {
            System.out.println("proceso finalizado");
        }
        try {
            Inscripcion inscripcion1 = evento2.getActividades().get(0).inscribir(e3);
            inscripcion1.confirmar();
            inscripcion1.emitirTicket("T-" + e3.getLegajo());
        } catch (CupoExcedidoException e) {
            System.out.println("No se pudo inscribir: " + e.getMessage());
        }

        EnvioTicketsThread hiloEnvio = new EnvioTicketsThread(evento2);
        hiloEnvio.start();

        System.out.println("El hilo principal sigue mostrando datos del evento mientras se envían los tickets...");
        evento2.mostrarDatos();

        System.out.println(" Evento original ");
        evento1.mostrarDatos();
        System.out.println("\n=== Copia de evento1 ===");
        copiaEvento1.mostrarDatos();
        System.out.println("\n=== Evento 2 ===");
        evento2.mostrarDatos();
        System.out.println("\n=== Evento 3 ===");
        evento3.mostrarDatos();
        System.out.println("\nCantidad total de eventos creados: " + EventoUniversitario.getCantidadEventos());
        System.out.println("\n=== Filtrado de actividades de evento1 por tipo ===");

        List<Charla> charlas = evento1.filtrarActividadesPorTipo(Charla.class);
        List<Taller> talleres = evento1.filtrarActividadesPorTipo(Taller.class);
        List<Curso> cursos = evento1.filtrarActividadesPorTipo(Curso.class);

        System.out.println("Cantidad de Charlas: " + charlas.size());
        System.out.println("Cantidad de Talleres: " + talleres.size());
        System.out.println("Cantidad de Cursos: " + cursos.size());

        double costoCharlas = evento1.calcularCostoMateriales(charlas);
        double costoTalleres = evento1.calcularCostoMateriales(talleres);
        double costoCursos = evento1.calcularCostoMateriales(cursos);

        System.out.println("Costo de materiales de Charlas: $" + costoCharlas);
        System.out.println("Costo de materiales de Talleres: $" + costoTalleres);
        System.out.println("Costo de materiales de Cursos: $" + costoCursos);
    }
}