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

            // Enviar números al primer operador (suma los numeros)
            out1.println(inputLine);

            // Recibir los resultados del primer servidor de operación
            String sumResult = in1.readLine();
            String res1 = sumResult;
            
            // envia el resultado de la suma al operador 2 (multiplica el resultado por 10)
            out2.println(sumResult);

            // Recibir los resultados del segundo servidor de operación
            String multResult = in2.readLine();
            String res2 = multResult;

            // Enviar los resultados al cliente
            clientOut.println("Resultado de la suma: " + res1 + ", Resultado de la multiplicacion: " + res2);
        }

        // Cerrar los sockets
        clientIn.close();
        clientOut.close();
        clientSocket.close();

        in1.close();
        out1.close();
        socket1.close();

        in2.close();
        out2.close();
        socket2.close();

        serverSocket.close();
        socket1.close();
        socket2.close();
    }
}
