import java.io.*;
import java.net.*;

public class Client {

    public static void main(String[] args) throws IOException {


        String result = "";
        
        try {

            // Conectándose al servidor principal
            Socket socket = new Socket("localhost", 9092);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            // Enviar una solicitud para calcular
            out.println("4,2");

            // Recibir los resultados del servidor principal
            result = in.readLine();

            // Cerrar los sockets
            in.close();
            out.close();
            socket.close();

        } catch (Exception e) {

            System.out.println("Error: " + e.getMessage());
            
        
            System.out.println("Error: No se pudo conectar con el servidor principal");

            try {

                //conectando al servidor OpServer1
                Socket socket = new Socket("localhost", 9090);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                // Enviar una solicitud para calcular
                out.println("4,2");

                //recibir los resultados del OpServer1
                result = in.readLine();

                //imprimir el resultado parcial
                System.out.println("Resultado OpServer1: " + result);

                // Cerrar los sockets
                in.close();
                out.close();
                socket.close();

                //establecer conexión con el OpServer2
                Socket socket2 = new Socket("localhost", 9091);
                PrintWriter out2 = new PrintWriter(socket2.getOutputStream(), true);
                BufferedReader in2 = new BufferedReader(new InputStreamReader(socket2.getInputStream()));

                //enviar el resultado al OpServer2
                out2.println(result);

                //recibir los resultados del OpServer2
                result = in2.readLine();

                //imprimir el resultado final
                System.out.println("Resultado OpServer2: " + result);

                // Cerrar los sockets
                in2.close();
                out2.close();
                socket2.close();


            } catch (Exception e1) {

                //falló la conexión con alguno de los dos servidores OpServer
                System.out.println("Error: " + e1.getMessage());
                
            }

        }

        System.out.println("Resultado recibido del servidor: " + result);

    }
}
