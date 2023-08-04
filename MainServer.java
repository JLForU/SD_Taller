import java.io.*;
import java.net.*;

public class MainServer {

    public static void main(String[] args) throws IOException {

        // Crear un socket para escuchar las solicitudes del cliente
        ServerSocket serverSocket = new ServerSocket(9092);
        Socket clientSocket = serverSocket.accept();
        BufferedReader clientIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter clientOut = new PrintWriter(clientSocket.getOutputStream(), true);

        // Comunicación con el primer servidor de operación
        Socket socket1 = new Socket("localhost", 9090);
        PrintWriter out1 = new PrintWriter(socket1.getOutputStream(), true);
        BufferedReader in1 = new BufferedReader(new InputStreamReader(socket1.getInputStream()));

        // Comunicación con el segundo servidor de operación
        Socket socket2 = new Socket("localhost", 9091);
        PrintWriter out2 = new PrintWriter(socket2.getOutputStream(), true);
        BufferedReader in2 = new BufferedReader(new InputStreamReader(socket2.getInputStream()));

        String inputLine;
        while ((inputLine = clientIn.readLine()) != null) {

            // Enviar números al primer y al segundo servidor de operación
            out1.println(inputLine);
            out2.println(inputLine);

            // Recibir los resultados de los servidores de operación
            String sumResult = in1.readLine();
            String diffResult = in2.readLine();

            // Enviar los resultados al cliente
            clientOut.println("Resultado de la suma: " + sumResult + ", Resultado de la resta: " + diffResult);
        }
    }
}
