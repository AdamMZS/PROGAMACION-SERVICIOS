public class Main {
    public static void main(String[] args) throws InterruptedException {
        HebraCaracter h1 = new HebraCaracter('A', 50);
        HebraCaracter h2 = new HebraCaracter('B', 50);
        HebraCaracter h3 = new HebraCaracter('C', 50);

        h1.start();
        h2.start();
        h3.start();

        h1.join();
        h2.join();
        h3.join();
        System.out.println("\nFin de la ejecución.");
    }
}