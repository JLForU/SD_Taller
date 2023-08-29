import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Operador2 extends UnicastRemoteObject implements IOperador2 {
    
    // constructor
    protected Operador2() throws RemoteException {
        System.out.println("Iniciando Operador2");
    }
        
    //funcion de multiplicacion. multiplica el numero que recibe por 10.
    public int mult(int a) throws RemoteException {
        return a * 10;
    }

    public static void main(String[] args) {
     
        try {
            // Crear el objeto cuyos métodos el cliente podrá usar
            Operador2 op2 = new Operador2(); 
            
            // Incluir el objeto en el registro del RMI en el puerto 1092,
            // para que el cliente lo pueda encontrar.
            Registry registry = LocateRegistry.createRegistry(1092);
            registry.rebind("Operador2", op2); 
            System.out.println("Objeto -Operador2- Registrado en el RMI"); 
        } catch (RemoteException e) {
          System.out.println("Error: " + e); 
        }
    }
    
}
