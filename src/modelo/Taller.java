package modelo;

import certificacion.Certificable;

public class Taller extends Actividad implements Certificable {
private boolean requiereNotebook;
public Taller(int id,String titulo, int cupoMaximo, boolean requiereNotebook){
    super(id,titulo,cupoMaximo);
    this.requiereNotebook = requiereNotebook;
}

    @Override
    public double calcularCostoMateriales() {
        if (requiereNotebook){
            return 5000;
        }
        return 2000;
    }
    public String getTipo(){
    return "Taller";
    }

    @Override
    public String generarCertificado(Estudiante estudiante) {
        return "La entidad emisora "+ENTIDAD_EMISORA+" certifica a "+estudiante.getNombre()+" por su participacion en "+getTipo();
    }
}
