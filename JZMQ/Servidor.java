
// PAQUETE


// IMPORTACIONES
//// JZMQ.
import org.zeromq.ZMQ ;
import java.nio.charset.Charset ;



/* IMPLEMENTACIÓN DE CLASE ´Servidor´. */

public class Servidor {
    

    //// IMPLEMENTACIÓN DE FUNCIÓN "MAIN". ////
    public static void main ( String[] args ) {

        
		// Elegir datos.
		int int_primerNuumero = 2 ;
		int int_segundoNuumero = 3 ;

		System.out.println ( "Enviar: " + int_primerNuumero + " y " + int_segundoNuumero ) ;
        String resultado = funcioon_Servidor ( int_primerNuumero , int_segundoNuumero ) ;
		System.out.println ( "Recibir: " + resultado ) ;
		

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
