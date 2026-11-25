public class Main {
    public static void main(String[] args) throws InterruptedException {
        Ascensor ascensor = new Ascensor(0, 3);

        Usuario u1 = new Usuario(1, 0, 3, ascensor);
        Usuario u2 = new Usuario(2, 2, -1, ascensor);
        Usuario u3 = new Usuario(3, 1, 5, ascensor);

        ascensor.start();
        u1.start();
        Thread.sleep(50); // Asegura que u1 entre primero a la cola
        u2.start();
        Thread.sleep(50); // Asegura que u2 entre segundo a la cola
        u3.start();

        u1.join();
        u2.join();
        u3.join();
        ascensor.join();
    }
}