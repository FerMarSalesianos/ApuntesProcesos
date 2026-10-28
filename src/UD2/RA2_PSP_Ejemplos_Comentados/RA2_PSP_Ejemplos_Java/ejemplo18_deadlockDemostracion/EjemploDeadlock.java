import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.CountDownLatch;
/**
 * DIAPOSITIVA 33. Demostración CONTROLADA de deadlock.
 * Dos hilos obtienen recursos en orden contrario.
 * ATENCIÓN: los hilos daemon quedan bloqueados mientras dure la JVM.
 * Se marcan daemon para que termine la aplicación al finalizar main.
 */
public class EjemploDeadlock {
    private static final Object RECURSO_A = new Object();
    private static final Object RECURSO_B = new Object();
    // La barrera asegura que ambos hilos posean su primer recurso
    // antes de intentar obtener el segundo.
    private static final CountDownLatch PRIMEROS_OBTENIDOS = new CountDownLatch(2);
    public static void main(String[] args) throws InterruptedException {
        Thread uno = new Thread(() -> {
            synchronized (RECURSO_A) {
                System.out.println("Hilo 1 ha adquirido A.");
                PRIMEROS_OBTENIDOS.countDown();
                try { PRIMEROS_OBTENIDOS.await(); }
                catch (InterruptedException e) { Thread.currentThread().interrupt(); return; }
                // Necesita B, pero el hilo 2 lo está reteniendo.
                synchronized (RECURSO_B) {
                    System.out.println("Hilo 1 ha obtenido A y B.");
                }
            }
        }, "Hilo-1");
        Thread dos = new Thread(() -> {
            synchronized (RECURSO_B) {
                System.out.println("Hilo 2 ha adquirido B.");
                PRIMEROS_OBTENIDOS.countDown();
                try { PRIMEROS_OBTENIDOS.await(); }
                catch (InterruptedException e) { Thread.currentThread().interrupt(); return; }
                // Necesita A, pero el hilo 1 lo está reteniendo.
                synchronized (RECURSO_A) {
                    System.out.println("Hilo 2 ha obtenido B y A.");
                }
            }
        }, "Hilo-2");
        // Los daemon no mantienen viva la JVM. NO arreglan el deadlock.
        uno.setDaemon(true); dos.setDaemon(true);
        uno.start(); dos.start();
        PRIMEROS_OBTENIDOS.await();
        // El API de monitorización puede detectar el ciclo de bloqueos.
        ThreadMXBean monitor = ManagementFactory.getThreadMXBean();
        long[] ids = null;
        // Damos un pequeño margen para que los hilos intenten segundo monitor.
        for (int intento = 0; intento < 30 && ids == null; intento++) {
            Thread.sleep(30);
            ids = monitor.findDeadlockedThreads();
        }
        System.out.println("Estado hilo 1: " + uno.getState());
        System.out.println("Estado hilo 2: " + dos.getState());
        System.out.println("¿Deadlock detectado?: " + (ids != null));
        System.out.println("main termina; los hilos daemon siguen bloqueados.");
    }
}
