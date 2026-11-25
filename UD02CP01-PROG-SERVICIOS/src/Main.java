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
        //on pocas repeticiones (ej. 5 o 10): Es muy probable que no se mezclen (salgan todas las A, luego las B y luego las C), porque cada hilo termina su bucle entero antes de que agote su cuanto (quantum) de tiempo de CPU.
        //
        //Al aumentar las repeticiones (ej. 50, 200 o 1000): Sí se mezclan de forma impredecible y distinta en cada ejecución (por ejemplo: AAAAABBBBBCCCCAAAA...).
        //
        //Motivo técnico: Las 3 hebras compiten por el tiempo de procesador.
        // El planificador de hilos (Thread Scheduler) del sistema operativo y la JVM reparte turnos de ejecución
        // (cambios de contexto) de manera no determinista.Cuando una hebra agota su turno o queda bloqueada brevemente por la operación de entrada/salida
        // (System.out.print, que internamente es sincronizada),
        // otra hebra entra a ejecutar y escribe su carácter.
    }
}