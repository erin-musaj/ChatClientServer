import java.io.*;
import java.net.*;
public class Server {
    public static void main(String[] args) {
        int port = 6969;
        try (ServerSocket ss = new ServerSocket(port)) {
            System.out.println("Server in ascolto sulla porta " + port);

            Socket cs = ss.accept();
            System.out.println("Connessione stabilita con il client");

            BufferedReader in = new BufferedReader(new InputStreamReader(cs.getInputStream()));
            PrintWriter out = new PrintWriter(cs.getOutputStream(), true);
            BufferedReader ui = new BufferedReader(new InputStreamReader(System.in));

            String response = "";
            while (!response.equals("FINE") && !response.equals(null)) {

                out.println(ui.readLine());

                response = in.readLine();
                System.out.println("Echo dal Client: " + response);
            }

            cs.close();
            in.close();
            out.close();
            System.out.println("Connessione con il Client chiusa.");

        } catch (IOException e) {
            System.err.println("Errore nella comunicazione: " + e.getMessage());
        }
    }
}