
public class Main {
void main() {
    Thread hilo1 = new Thread(()->{
            for (int i=0; i < 5; i++) {
                System.out.print("H1:");
            }
    });
    Thread hilo2 = new Thread(()->{
            for (int i=0; i < 5; i++) {
                System.out.print("H2");
            }
    });
    Thread hilo3 = new Thread(()->{
            for (int i=0; i < 5; i++) {
                System.out.print("H3:");
            }
    });
    hilo1.start();
    hilo2.start();
    hilo3.start();

}
}
