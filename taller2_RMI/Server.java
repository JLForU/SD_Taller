// cuando se ejecute la funcion getResultado, primero se
// llama a la funcion de suma del operador1, y luego ese resultado se le envia
// a la funcion de multiplicacion del operador2, y el resultado de esta ultima 
// lo retorna la funcion getResultado como String.

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


public class Server extends UnicastRemoteObject implements IServer{
    

    // constructor
    protected Server() throws RemoteException {
        System.out.println("Iniciando Server");
    }
        
    // funcion getResultado. recibe dos enteros y retorna un String.
    public String getResultado(int a, int b) throws RemoteException{

        int suma = 0;
        int multiplicacion = 0;
        String resultado = null;
            
        try {
            
            // conectarse al servidor y cargar registro de objetos RMI
            Registry registry = LocateRegistry.getRegistry("localhost", 1091);
            Registry registry2 = LocateRegistry.getRegistry("localhost", 1092);
            
            // buscar el objeto operador1 en el registro,
            // y si lo encuentra, crear el objeto local
            IOperador1 op1 = (IOperador1)registry.lookup("Operador1");
            
            // buscar el objeto operador2 en el registro,
            // y si lo encuentra, crear el objeto local
            IOperador2 op2 = (IOperador2)registry2.lookup("Operador2");
            
            suma = op1.suma(a, b);
            multiplicacion = op2.mult(suma);
            resultado = Integer.toString(multiplicacion);
            
        } catch (NotBoundException e) {
            System.out.println("no se encontro el objeto en el registro");
            System.exit(0);
        } catch (RemoteException e) {
            System.out.println("Error: " + e);
            System.exit(0);
        }

        return resultado;

    }

    // Arranque del Servidor
    public static void main(String[] args) {
        
        try {

            // Crear el objeto cuyos métodos el cliente podrá usar
            Server S = new Server(); 
            
            // Incluir el objeto en el registro del RMI en el puerto 1090,
            // para que el cliente lo pueda encontrar.
            Registry registry = LocateRegistry.createRegistry(1090);
            registry.rebind("server", S); 
            System.out.println("Objeto -server- Registrado en el RMI"); 
        
        } catch (RemoteException e) {
          System.out.println("Error: " + e); 
        } 
    }

}
