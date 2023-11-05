
import java.rmi.RemoteException ;
import java.rmi.registry.LocateRegistry ;
import java.rmi.registry.Registry ;
import java.rmi.server.UnicastRemoteObject ;


public class Operador_01 extends UnicastRemoteObject implements Interfaz_Operador_01 {
    
	
    // Constructor //
    protected Operador_01 ( ) throws RemoteException {
        System.out.println ( "Iniciando `Operador_01`..." ) ;
    }
        
    // Método de suma: suma los dos enteros recibidos.
    public int sumar ( int a , int b ) throws java.rmi.RemoteException {
        return a + b ;
    }

    public static void main ( String[] args ) {
     

        try {

            // Crear el objeto cuyos métodos podrá usar el cliente.
            Operador_01 op1 = new Operador_01() ;
            
            // Incluir el objeto en el registro del RMI en el puerto 1091,
            // para que el cliente lo pueda encontrar.
            Registry registry = LocateRegistry.createRegistry ( 1091 ) ;
            registry.rebind ( "Operador_01" , op1 ) ;
            System.out.println ( "Objeto 'Operador_01' registrado en RMI." ) ;

        } catch ( RemoteException e ) {

          	System.out.println ( "Error: " + e ) ;

        }


    }


}

