package excepciones;

public class CupoExcedidoException extends Exception{
private String mensaje;
public CupoExcedidoException(String mensaje){
    super(mensaje);
}
}
