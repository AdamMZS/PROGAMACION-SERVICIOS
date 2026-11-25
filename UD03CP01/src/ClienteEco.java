import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClienteEco {

    public ClienteEco(){
        try(Socket c=new Socket("localhost",5000);
            BufferedReader br=new BufferedReader(new InputStreamReader(c.getInputStream()));
            PrintWriter pw=new PrintWriter(c.getOutputStream(),true)){
            BufferedReader teclado=new BufferedReader(new InputStreamReader(System.in));
            String linea;
            System.out.println("Escribe mensajes (FIN para salir)");
            while((linea=teclado.readLine())!=null){
                pw.println(linea);
                if (linea.equals("FIN")){
                break;
                }
                System.out.println("Respuesta:"+br.readLine());

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args){
        new ClienteEco();
    }
}
