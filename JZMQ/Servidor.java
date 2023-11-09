
// PAQUETE


// IMPORTACIONES
//// JZMQ.
import org.zeromq.ZMQ ;
import java.nio.charset.Charset ;

import java.rmi.NotBoundException ;
import java.rmi.RemoteException ;
import java.rmi.registry.LocateRegistry ;
import java.rmi.registry.Registry ;
import java.rmi.server.UnicastRemoteObject ;



/* IMPLEMENTACIÓN DE CLASE ´Servidor´. */

public class Servidor extends UnicastRemoteObject implements Interfaz_Servidor{
    

    protected Servidor() throws RemoteException {
	}

	//// IMPLEMENTACIÓN DE FUNCIÓN "MAIN". ////
    public static void main ( String[] args ) {

		try {

            // Crear el objeto cuyos métodos podrá usar el cliente.
            Servidor S = new Servidor() ;
            
            // Incluir el objeto en el registro del RMI en el puerto 1090,
            // para que el cliente lo pueda encontrar.
            Registry registry = LocateRegistry.createRegistry ( 1090 ) ;
            registry.rebind ( "server" , S ) ;
            System.out.println ( "Objeto 'server' registrado en RMI" ) ;
        
        } catch ( RemoteException e ) {

        	System.out.println ( "Error: " + e ) ;

        }

    }

	public String getResultado(int a, int b){

		System.out.println ( "Enviar: " + a + " y " + b ) ;
        String resultado = funcioon_Servidor ( a , b ) ;
		System.out.println ( "Recibir: " + resultado ) ;
		return resultado;
		
	}

	public static String funcioon_Servidor ( int int_primerNuumero , int int_segundoNuumero ) {

		String resultado = "0" ;

		try (
			
			ZMQ.Context context = ZMQ.context(1) ;
			ZMQ.Socket socket = context.socket ( ZMQ.REQ ) ;
			
		) {

			// Conectar.
            socket.connect ( "tcp://localhost:5554" ) ;

			// Convertir.
			String string_primerNuumero = String.valueOf ( int_primerNuumero ) ;
			String string_segundoNuumero = String.valueOf ( int_segundoNuumero ) ;

			// Enviar.
			socket.send ( string_primerNuumero.getBytes() , ZMQ.SNDMORE ) ;
			socket.send ( string_segundoNuumero.getBytes() , ZMQ.NOBLOCK ) ;

			// Recibir.
			resultado = socket.recvStr ( Charset.defaultCharset() ) ;

			// Desconectar.
			socket.close() ;

		} catch ( Exception e ) {

			System.out.println ( "Error: " + e ) ;

		}

	return resultado ;
	}


}




// FIN DE ARCHIVO.
