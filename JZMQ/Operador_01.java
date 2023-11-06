
// PAQUETE


// IMPORTACIONES
//// JZMQ.
import org.zeromq.ZMQ ;
import org.zeromq.ZMQException ;
import java.nio.charset.Charset ;



/* IMPLEMENTACIÓN DE CLASE ´Operador_01´. */

public class Operador_01 {
	

    // @param args the command line arguments

    public static void main ( String[] args ) {


		funcioon_OperadorZMQ ( ) ;


    }

	public static void funcioon_OperadorZMQ ( ) {

		try (
			
			ZMQ.Context context = ZMQ.context(1) ;
			ZMQ.Socket socket = context.socket ( ZMQ.REP ) ;
			
		) {

			// Conectar.
            socket.bind ( "tcp://localhost:5555" ) ;

			// Recibir.
			String string_primerNuumero = socket.recvStr ( Charset.defaultCharset() ) ;
			String string_segundoNuumero = socket.recvStr ( Charset.defaultCharset() ) ;

			// Convertir.
			int int_primerNuumero = Integer.parseInt ( string_primerNuumero ) ;
			int int_segundoNuumero = Integer.parseInt ( string_segundoNuumero ) ;

			// Operar.
			int int_respuesta = int_primerNuumero + int_segundoNuumero ;

			// Enviar.
			String string_respuesta = String.valueOf ( int_respuesta ) ;
			socket.send ( string_respuesta.getBytes() , ZMQ.NOBLOCK ) ;

			// Cerrar.
			socket.close() ;

		} catch ( ZMQException e ) {

   		     if ( e.getErrorCode() == ZMQ.Error.ETERM.getCode() ) {
   		         // Handle the case where the server has shut down (ETERM error).
   		         System.out.println ( "Server has shut down." ) ;
   		     } else {
   		         // Handle other ZeroMQ-related errors.
   		         System.out.println ( "Error: " + e ) ;
   		     }

   		 } catch ( Exception e ) {
   		     // Handle general exceptions (non-ZeroMQ related).
   		     System.out.println("Error: " + e) ;
   		 }

	}


}




// FIN DE ARCHIVO.    
