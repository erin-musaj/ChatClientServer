import java.io.*;
import java.net.*;
public class Server {
    public static void main(String[] args) {
        int port = 1234;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server in ascolto sulla porta " + port);
            Socket clientSocket = serverSocket.accept();
            System.out.println("Connessione stabilita con il client");
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader ui = new BufferedReader(new InputStreamReader(System.in));


            String line = "";
            String response = "";
            while (!response.equals("FINE") && !response.equals(null)) {
                line = ui.readLine();
                out.println(line);

                response = in.readLine();
                System.out.println("Echo dal Client: " + response);
            }

            clientSocket.close();
            in.close();
            out.close();
            System.out.println("Connessione con il client chiusa.");

        } catch (IOException e) {
            System.err.println("Errore nella comunicazione: " + e.getMessage());
        }
    }
}