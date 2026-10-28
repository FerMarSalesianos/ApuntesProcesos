/** DIAPOSITIVA 32. Dos hilos comparten un contador sincronizado. */
public class EjemploSynchronized {
    public static void main(String[] args) throws InterruptedException {
        // MUY IMPORTANTE: creamos UN solo objeto compartido.
        ContadorSeguro contador = new ContadorSeguro();
        Runnable tarea = () -> {
            for (int i = 0; i < 200_000; i++) {
                // Ambos invocan el método sincronizado sobre el mismo monitor.
                contador.incrementar();
            }
        };
        Thread a = new Thread(tarea, "A");
        Thread b = new Thread(tarea, "B");
        a.start(); b.start();
        // join garantiza que todas las sumas finalizaron antes de leer.
        a.join(); b.join();
        System.out.println("Esperado: 400000");
        System.out.println("Obtenido: " + contador.getValor());
        // En condiciones normales, los dos valores siempre coinciden.
    }
}
