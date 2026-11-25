import java.util.LinkedList;

public class Ascensor extends Thread {
    private int pisoActual;
    private final LinkedList<Usuario> colaUsuarios = new LinkedList<>();
    private Usuario usuarioActual = null;
    private boolean esperandoSubida = false;
    private boolean esperandoBajada = false;
    private int usuariosPendientes;

    public Ascensor(int pisoInicial, int totalUsuarios) {
        this.pisoActual = pisoInicial;
        this.usuariosPendientes = totalUsuarios;
    }

    public synchronized void solicitarAscensor(Usuario u) throws InterruptedException {
        colaUsuarios.addLast(u);
        notifyAll();

        while (usuarioActual != u || pisoActual != u.getPisoOrigen() || !esperandoSubida) {
            wait();
        }

        System.out.println("Usuario " + u.getUsuarioId() + " sube al ascensor.");
        esperandoSubida = false;
        notifyAll();
    }

    public synchronized void bajarDelAscensor(Usuario u) throws InterruptedException {
        // Espera a que el ascensor llegue a su destino y abra puertas para bajar
        while (usuarioActual != u || pisoActual != u.getPisoDestino() || !esperandoBajada) {
            wait();
        }

        // El usuario baja y confirma al ascensor
        System.out.println("Usuario " + u.getUsuarioId() + " baja en piso " + u.getPisoDestino());
        esperandoBajada = false;
        usuarioActual = null;
        usuariosPendientes--;
        notifyAll();
    }

    @Override
    public void run() {
        try {
            while (true) {
                Usuario siguiente;

                // 1. Obtener al siguiente usuario de la cola de forma segura
                synchronized (this) {
                    while (colaUsuarios.isEmpty() && usuariosPendientes > 0) {
                        wait();
                    }
                    if (usuariosPendientes == 0) {
                        break; // Ya no quedan usuarios por atender
                    }
                    siguiente = colaUsuarios.pollFirst();
                    usuarioActual = siguiente;
                    System.out.println("Usuario " + siguiente.getUsuarioId() + " espera en piso " + siguiente.getPisoOrigen());
                }

                // 2. Moverse al piso de origen del usuario
                moverA(siguiente.getPisoOrigen());

                // 3. Permitir que el usuario suba y esperar su confirmación
                synchronized (this) {
                    esperandoSubida = true;
                    notifyAll();
                    while (esperandoSubida) {
                        wait();
                    }
                }

                // 4. Moverse al piso destino del usuario
                moverA(siguiente.getPisoDestino());

                // 5. Permitir que el usuario baje y esperar su confirmación
                synchronized (this) {
                    esperandoBajada = true;
                    notifyAll();
                    while (esperandoBajada) {
                        wait();
                    }
                }
            }

            // 6. Cuando no hay usuarios pendientes, bajar a -1 y finalizar
            moverA(-1);
            System.out.println("Ascensor llegó a piso -1. Fin del programa.");

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void moverA(int destino) throws InterruptedException {
        int paso = (destino > pisoActual) ? 1 : -1;
        while (pisoActual != destino) {
            Thread.sleep(300); // Pausa realista entre pisos
            synchronized (this) {
                pisoActual += paso;
                System.out.println("Ascensor en piso " + pisoActual);
            }
        }
    }
}