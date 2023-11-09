
import java.rmi.NotBoundException ;
import java.rmi.RemoteException ;
import java.rmi.registry.LocateRegistry ;
import java.rmi.registry.Registry ;



public class Cliente {

    
    public static void main ( String[] args ) {

        
        int a = 5 ;
        int b = 2 ;
        String resultado = null ;

        try {
            
            // Conectarse al servidor y cargar registro de objetos RMI.
            Registry registry = LocateRegistry.getRegistry ( "localhost" , 1090 ) ;
            
            // Buscar el objeto 'server' en el registro,
            // y si lo encuentra, crear el objeto local.
            Interfaz_Servidor servidor = (Interfaz_Servidor) registry.lookup ( "server" ) ;
            
            // Usar el método `getResultado()` del objeto conectado.
            resultado = servidor.getResultado ( a , b ) ;
            
        } catch (NotBoundException e) {
            System.out.println ( "No se encontro el objeto en el registro." ) ;
            System.exit(0) ;
        } catch ( RemoteException e ) {
            System.out.println ( "Error: " + e ) ;
            System.exit(0) ;
        }

        if  ( resultado != null ) System.out.println ( "El resultado es: " + resultado ) ;


    }


}

