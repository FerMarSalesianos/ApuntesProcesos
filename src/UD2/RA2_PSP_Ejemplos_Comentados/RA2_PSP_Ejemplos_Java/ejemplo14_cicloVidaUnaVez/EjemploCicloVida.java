/** DIAPOSITIVAS 15 Y 26. NEW, TERMINATED e IllegalThreadStateException. */
public class EjemploCicloVida {
    public static void main(String[] args) throws InterruptedException {
        Thread hilo = new Thread(() -> {
            System.out.println("Tarea en ejecución.");
        }, "MiHilo");
        System.out.println("Antes de start(): " + hilo.getState()); // NEW
        hilo.start();
        // join() garantiza que ya terminó antes de comprobar TERMINATED.
        hilo.join();
        System.out.println("Después de join(): " + hilo.getState()); // TERMINATED
        try {
            // Un Thread solo puede iniciarse UNA vez, aunque su tarea haya acabado.
            hilo.start();
        } catch (IllegalThreadStateException e) {
            // Capturamos el error deliberadamente para que el ejemplo termine bien.
            System.out.println("No se puede reiniciar el mismo objeto Thread.");
        }
        // Para repetir la tarea debemos crear un objeto Thread NUEVO.
        Thread otro = new Thread(() -> System.out.println("Segunda ejecución."));
        otro.start(); otro.join();
    }
}
