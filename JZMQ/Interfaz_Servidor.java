
import java.rmi.Remote;


public interface Interfaz_Servidor extends Remote{

    
    public String getResultado ( int a , int b ) throws java.rmi.RemoteException ;


}
