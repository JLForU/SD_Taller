import java.rmi.Remote;

public interface IServer extends Remote{

    public String getResultado(int a, int b) throws java.rmi.RemoteException;
    
}
