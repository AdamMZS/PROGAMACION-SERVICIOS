public class HebraIncrementadora extends Thread {
    private final VariableCompartida variable;
    private final int incrementos;

    public HebraIncrementadora(VariableCompartida variable, int incrementos) {
        this.variable = variable;
        this.incrementos = incrementos;
    }

    @Override
    public void run() {
        for (int i = 0; i < incrementos; i++) {
            variable.incrementar();
        }
    }
}