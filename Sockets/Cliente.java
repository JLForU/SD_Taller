
import java.io.* ;
import java.net.* ;


public class Cliente {


    public static void main ( String[] args ) throws IOException {


        String result = "" ;
        
        try {

            // Conectándose al servidor principal.
            Socket socket = new Socket ( "localhost" , 9092 ) ;
            PrintWriter out = new PrintWriter ( socket.getOutputStream() , true ) ;
            BufferedReader in = new BufferedReader ( new InputStreamReader(socket.getInputStream()) ) ;
            
            // Enviar una solicitud para calcular.
            out.println ( "4,2" ) ;

            // Recibir los resultados del servidor principal.
            result = in.readLine() ;

            // Cerrar los sockets
            in.close() ;
            out.close() ;
            socket.close() ;

        } catch ( Exception e ) {

            System.out.println ( "Error: " + e.getMessage() ) ;
            
            System.out.println ( "Error: No se pudo conectar con el servidor principal." ) ;

            try {

				// OP_01 //

                // Conectando al servidor 'Operador_01'.
                Socket socket = new Socket ( "localhost" , 9090 ) ;
                PrintWriter out = new PrintWriter ( socket.getOutputStream() , true ) ;
                BufferedReader in = new BufferedReader ( new InputStreamReader(socket.getInputStream()) ) ;

                // Enviar una solicitud para calcular.
                out.println ( "4,2" ) ;

                // Recibir los resultados del 'Operador_01'.
                result = in.readLine() ;

                // Imprimir el resultado parcial.
                System.out.println ( "Resultado 'Operador_01': " + result ) ;

                // Cerrar los sockets.
                in.close() ;
                out.close() ;
                socket.close() ;

				// OP_02 //

                // Establecer conexión con el 'Operador_02'.
                Socket socket2 = new Socket ( "localhost" , 9091 ) ;
                PrintWriter out2 = new PrintWriter ( socket2.getOutputStream() , true ) ;
                BufferedReader in2 = new BufferedReader ( new InputStreamReader(socket2.getInputStream()) ) ;

                // Enviar el resultado al 'Operador_02'.
                out2.println ( result ) ;

                // Recibir los resultados del 'Operador_02'.
                result = in2.readLine() ;

                // Imprimir el resultado final.
                System.out.println ( "Resultado 'Operador_02': " + result ) ;

                // Cerrar los sockets.
                in2.close() ;
                out2.close() ;
                socket2.close() ;


            } catch ( Exception e1 ) {

                // Falló la conexión con alguno de los dos servidores "Operadores".
                System.out.println ( "Error: " + e1.getMessage() ) ;

            }

        }

        System.out.println ( "Resultado recibido del servidor: " + result ) ;


    }


}

