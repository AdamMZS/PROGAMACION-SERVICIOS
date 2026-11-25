import java.io.*;
import java.net.*;
import java.util.Locale;

public class ServidorEco {
    final int PUERTO=5000;
    public ServidorEco() {
        try (ServerSocket ss = new ServerSocket(PUERTO)) {
            System.out.println("Servidor esperando en puerto:" + PUERTO);
            try (Socket s = ss.accept();
                 BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
                 PrintWriter pw = new PrintWriter(s.getOutputStream(), true)) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    System.out.println("Cliente dice:" + linea);
                    pw.println("ECO:" + linea.toUpperCase());
                }
                System.out.println("Cliente desconectado");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        new ServidorEco();
    }

}
