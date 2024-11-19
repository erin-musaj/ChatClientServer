import java.io.*;
import java.net.*;
public class Client {
    public static void main(String[] args) {
        String serverAddress = "localhost";
        int port = 6969;
        try (Socket s = new Socket(serverAddress, port)) {
            System.out.println("Connesso al server");

            PrintWriter out = new PrintWriter(s.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            BufferedReader ui = new BufferedReader(new InputStreamReader(System.in));

            String line = "";
            String response = "";
            while (!line.equals("FINE") && !response.equals(null)) {
                response = in.readLine();
                System.out.println("Echo dal Server: " + response);

                line = ui.readLine();
                out.println(line);
            }

            in.close();
            out.close();
            s.close();

            System.out.println("Connessione con il Server chiusa.");

        } catch (IOException e) {
            System.err.println("Errore nella comunicazione: " + e.getMessage());
        }
    }
}