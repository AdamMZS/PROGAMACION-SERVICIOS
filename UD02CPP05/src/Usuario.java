public class Usuario extends Thread {
    private final int id;
    private final int pisoOrigen;
    private final int pisoDestino;
    private final Ascensor ascensor;

    public Usuario(int id, int pisoOrigen, int pisoDestino, Ascensor ascensor) {
        this.id = id;
        this.pisoOrigen = pisoOrigen;
        this.pisoDestino = pisoDestino;
        this.ascensor = ascensor;
    }

    public int getUsuarioId() {
        return id;
    }

    public int getPisoOrigen() {
        return pisoOrigen;
    }

    public int getPisoDestino() {
        return pisoDestino;
    }

    @Override
    public void run() {
        try {
            ascensor.solicitarAscensor(this);
            ascensor.bajarDelAscensor(this);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}