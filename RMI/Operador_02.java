
import java.rmi.RemoteException ;
import java.rmi.registry.LocateRegistry ;
import java.rmi.registry.Registry ;
import java.rmi.server.UnicastRemoteObject ;


public class Operador_02 extends UnicastRemoteObject implements Interfaz_Operador_02 {
    
	
    // Constructor //
    protected Operador_02 ( ) throws RemoteException {
        System.out.println ( "Iniciando 'Operador_02'..." ) ;
    }
        
	// Método de multiplicar: multiplica por 10 el número que recibe.
    public int multiplicar ( int a ) throws RemoteException {
        return a * 10 ;
    }

    public static void main ( String[] args ) {
     

        try {

            // Crear el objeto cuyos métodos podrá usar el cliente.
            Operador_02 op2 = new Operador_02() ;
            
            // Incluir el objeto en el registro del RMI en el puerto 1092,
            // para que el cliente lo pueda encontrar.
            Registry registry = LocateRegistry.createRegistry ( 1092 ) ;
            registry.rebind ( "Operador_02" , op2 ) ;
            System.out.println ( "Objeto 'Operador_02' registrado en RMI." ) ;

        } catch ( RemoteException e ) {

          System.out.println ( "Error: " + e ) ; 

        }


    }
    

}

