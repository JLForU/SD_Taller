import java.io.*;
import java.net.*;

public class OpServer2 {

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(9091);
        Socket clientSocket = serverSocket.accept();
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            int num = Integer.parseInt(inputLine);
            int result = num * 10;
            out.println(result);
        }

        in.close();
        out.close();
        clientSocket.close();
        serverSocket.close();
        
    }
}
