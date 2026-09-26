package hilos;

import modelo.EventoUniversitario;
import modelo.Actividad;
import modelo.Inscripcion;

public class EnvioTicketsThread extends Thread {
private EventoUniversitario evento;
public EnvioTicketsThread(EventoUniversitario evento){
    this.evento=evento;}
    @Override
    public void run() {
        for (Actividad actividad : evento.getActividades()) {
            for (Inscripcion inscripcion : actividad.getInscripciones()) {
                if (inscripcion.getEstado().equals("confirmada") && inscripcion.getTicket() != null) {
                    inscripcion.getTicket().enviarTicket();
                }
            }
        }
    }
}
