
// PAQUETE


// IMPORTACIONES
//// JZMQ.
import org.zeromq.ZMQ ;
import java.nio.charset.Charset ;



/* IMPLEMENTACIÓN DE CLASE ´Broker´. */

public class Broker {
    

    //// IMPLEMENTACIÓN DE FUNCIÓN "MAIN". ////
    public static void main ( String[] args ) {

        
		// Elegir datos.
		int int_primerNuumero = 2 ;
		int int_segundoNuumero = 3 ;

		// OP1 //
		System.out.println ( "Enviar: " + int_primerNuumero + " y " + int_segundoNuumero ) ;
        String primerResultado = funcioon_Operador_01 ( int_primerNuumero , int_segundoNuumero ) ;
		System.out.println ( "Recibir: " + primerResultado ) ;
		
		// OP2 //
		System.out.println ( "Enviar: " + primerResultado ) ;
		String segundoResultado = funcioon_Operador_02 ( primerResultado ) ;
		System.out.println ( "Recibir: " + segundoResultado ) ;


    }

	public static String funcioon_Operador_01 ( int int_primerNuumero , int int_segundoNuumero ) {

		String primerResultado = "0" ;

		try (
			
			ZMQ.Context context = ZMQ.context(1) ;
			ZMQ.Socket socket = context.socket ( ZMQ.REQ ) ;
			
		) {

			// Conectar.
            socket.connect ( "tcp://localhost:5555" ) ;

			// Convertir.
			String string_primerNuumero = String.valueOf ( int_primerNuumero ) ;
			String string_segundoNuumero = String.valueOf ( int_segundoNuumero ) ;

			// Enviar.
			socket.send ( string_primerNuumero.getBytes() , ZMQ.SNDMORE ) ;
			socket.send ( string_segundoNuumero.getBytes() , ZMQ.NOBLOCK ) ;

			// Recibir.
			primerResultado = socket.recvStr ( Charset.defaultCharset() ) ;

			// Desconectar.
			socket.close() ;

		} catch ( Exception e ) {

			System.out.println ( "Error: " + e ) ;

		}

	return primerResultado ;
	}

	public static String funcioon_Operador_02 ( String primerResultado ) {

		String segundoResultado = "0" ;

		try (
			
			ZMQ.Context context = ZMQ.context(1) ;
			ZMQ.Socket socket = context.socket ( ZMQ.REQ ) ;
			
		) {

			// Conectar.
            socket.connect ( "tcp://localhost:5556" ) ;

			// Enviar.
			socket.send ( primerResultado.getBytes() , ZMQ.NOBLOCK ) ;

			// Recibir.
			segundoResultado = socket.recvStr ( Charset.defaultCharset() ) ;

			// Desconectar.
			socket.close() ;

		} catch ( Exception e ) {

			System.out.println ( "Error: " + e ) ;

		}

	return segundoResultado ;
	}


}




// FIN DE ARCHIVO.
