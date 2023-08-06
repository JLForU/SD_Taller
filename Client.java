import java.io.*;
import java.net.*;

public class Client {

    public static void main(String[] args) throws IOException {

        // Conectándose al servidor principal
        Socket socket = new Socket("localhost", 9092);
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        // Enviar una solicitud para calcular
        out.println("4,2");

        // Recibir los resultados del servidor principal
        String result = in.readLine();

        System.out.println("Resultado recibido del servidor: " + result);

        // Cerrar los sockets
        in.close();
        out.close();
        socket.close();

    }
}
