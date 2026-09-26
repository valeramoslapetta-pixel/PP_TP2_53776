package modelo;

import java.time.LocalDate;
public class Inscripcion implements java.io.Serializable{
    private final LocalDate fecha;
    private String estado;
    private TicketDeAcceso ticket;

    public Inscripcion(String estado){
        this.estado = estado;
        this.fecha = LocalDate.now();
    }

    public LocalDate getFecha() {
        return fecha;
    }
    public void confirmar(){
        this.estado = "confirmada";
    }
    public TicketDeAcceso emitirTicket(String idTicket){
        if (this.estado.equals("confirmada")){
            this.ticket = new TicketDeAcceso(idTicket);
            return this.ticket;
        }
        return null;
    }
    public TicketDeAcceso getTicket(){
        return ticket;
    }

    public String getEstado() {
        return estado;
    }
    public class TicketDeAcceso implements java.io.Serializable {
        private String idTicket;
        private LocalDate fechaEmision;

        public TicketDeAcceso(String idTicket) {
            this.idTicket = idTicket;
            this.fechaEmision = LocalDate.now();
        }

        public void enviarTicket() {
            System.out.println("Enviando ticket " + idTicket + " (emitido " + fechaEmision + ") - Estado inscripción: " + estado);
        }

        public String getIdTicket() {
            return idTicket;
        }
    }
}
