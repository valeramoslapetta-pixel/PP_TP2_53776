package modelo;

import certificacion.Certificable;

public class Curso extends Actividad implements Certificable {
    private int nivel;
    public Curso(int id, String titulo, int cupoMax, int nivel){
        super(id,titulo,cupoMax);
        this.nivel=nivel;
    }
    public double calcularCostoMateriales(){
        return 4000+(2000*nivel);
    }
    public String getTipo(){
        return "Curso";
    }
    @Override
    public String generarCertificado(Estudiante estudiante){
        return "La entidad emisora "+ENTIDAD_EMISORA+" certifica a "+estudiante.getNombre()+" por su participacion en "+getTipo();
    }
}
