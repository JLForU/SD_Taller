import java.rmi.Remote;


public interface IServidor extends Remote{
    
    public String getResultado ( int a , int b ) throws java.rmi.RemoteException ;

}
