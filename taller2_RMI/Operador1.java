import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


public class Operador1 extends UnicastRemoteObject implements IOperador1 {
    
    // constructor
    protected Operador1() throws RemoteException {
        System.out.println("Iniciando Operador1");
    }
        
    //funcion de suma
    public int suma(int a, int b) throws java.rmi.RemoteException {
        return a + b;
    }

    public static void main(String[] args) {
     
        try {
            // Crear el objeto cuyos métodos el cliente podrá usar
            Operador1 op1 = new Operador1(); 
            
            // Incluir el objeto en el registro del RMI en el puerto 1091,
            // para que el cliente lo pueda encontrar.
            Registry registry = LocateRegistry.createRegistry(1091);
            registry.rebind("Operador1", op1); 
            System.out.println("Objeto -Operador1- Registrado en el RMI"); 
        } catch (RemoteException e) {
          System.out.println("Error: " + e); 
        }
    }
}
