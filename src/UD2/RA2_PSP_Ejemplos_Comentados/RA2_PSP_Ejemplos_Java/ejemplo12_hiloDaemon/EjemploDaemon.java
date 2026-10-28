/** DIAPOSITIVA 23. Hilos de usuario y daemon. */
public class EjemploDaemon {
    public static void main(String[] args) throws InterruptedException {
        Thread monitor = new Thread(() -> {
            int ciclos = 0;
            // Es una tarea auxiliar que hace comprobaciones periódicas.
            // El bucle podría seguir para siempre si no termina la JVM.
            while (true) {
                ciclos++;
                System.out.println("Monitorización auxiliar: " + ciclos);
                try { Thread.sleep(250); }
                catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        }, "Monitor-Daemon");
        // Importante: setDaemon(true) se llama ANTES de start().
        monitor.setDaemon(true);
        System.out.println("¿Es daemon? " + monitor.isDaemon());
        monitor.start();
        // main permanece vivo un instante: deja que el daemon trabaje.
        Thread.sleep(700);
        System.out.println("main termina; el daemon no mantiene viva la JVM.");
        // No llamamos join() sobre monitor, porque su bucle es infinito.
        // Cuando solo quedan hilos daemon, el proceso puede terminar.
    }
}
