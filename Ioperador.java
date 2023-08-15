import java.rmi.Remote;

public interface Ioperador extends Remote {
    //operacion para sumar dos n umeros
    public String suma(String mensaje) throws java.rmi.RemoteException;
    public String multiplicacion(String mensaje) throws java.rmi.RemoteException;
}
