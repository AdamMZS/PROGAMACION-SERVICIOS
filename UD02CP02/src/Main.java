void main(String[] args) throws InterruptedException {
    // Prueba 1: 10 incrementos por hebra (esperado: 20)
    ejecutarPrueba(10);

    // Prueba 2: Aumentando progresivamente (esperado: 200.000)
    ejecutarPrueba(100_000);
}

private static void ejecutarPrueba(int numIncrementos) throws InterruptedException {
    VariableCompartida vc = new VariableCompartida();
    HebraIncrementadora h1 = new HebraIncrementadora(vc, numIncrementos);
    HebraIncrementadora h2 = new HebraIncrementadora(vc, numIncrementos);

    h1.start();
    h2.start();

    // Esperamos a que ambas terminen antes de leer el valor desde main
    h1.join();
    h2.join();

    System.out.println("Incrementos por hebra: " + numIncrementos
            + " | Esperado: " + (numIncrementos * 2)
            + " | Obtenido: " + vc.getValor());
}
//Con 10 incrementos: Casi siempre se obtiene el resultado esperado (20).
// Al ser tan pocas operaciones, la primera hebra suele terminar sus 10 iteraciones antes de que la segunda hebra llegue siquiera a arrancar.
// Al aumentar el número de incrementos (ej. 10.000 o 100.000): Aparece el comportamiento "extraño": el valor final es menor al esperado generalmente
//Con muchos incrementos (ej. 100.000): No, sale un valor menor al esperado y distinto en cada ejecución.
// Se produce una condición de carrera (Race Condition) porque v++ no es atómico (se divide en leer, sumar y escribir).
// Al no usar synchronized, una hebra puede leer un valor antiguo antes de que la otra guarde su suma, sobrescribiendo y perdiendo incrementos.