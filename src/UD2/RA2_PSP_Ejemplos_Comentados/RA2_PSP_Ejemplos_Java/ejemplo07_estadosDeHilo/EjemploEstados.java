import java.util.concurrent.CountDownLatch;
/** DIAPOSITIVA 15. Estados oficiales de Thread.State. */
public class EjemploEstados {
    public static void main(String[] args) throws InterruptedException {
        // Un hilo recién construido todavía está en NEW.
        Thread nuevo = new Thread(() -> {});
        System.out.println("Nuevo: " + nuevo.getState());
        // Este latch avisa de que el hilo ha llegado al sueño.
        CountDownLatch iniciados = new CountDownLatch(1);
        Thread dormilon = new Thread(() -> {
            iniciados.countDown();
            try {
                // Mientras se espera el tiempo de sleep, estado TIMED_WAITING.
                Thread.sleep(600);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        dormilon.start(); iniciados.await();
        // Esperamos un poco a que entre en sleep; getState es una fotografía instantánea.
        Thread.sleep(50);
        System.out.println("Durante sleep (habitual): " + dormilon.getState());
        // Otro hilo espera indefinidamente a una señal del latch: WAITING.
        CountDownLatch desbloquear = new CountDownLatch(1);
        CountDownLatch preparado = new CountDownLatch(1);
        Thread esperando = new Thread(() -> {
            preparado.countDown();
            try { desbloquear.await(); }
            catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        });
        esperando.start(); preparado.await(); Thread.sleep(50);
        System.out.println("Esperando señal (habitual): " + esperando.getState());
        // Crearemos un hilo que mantiene el monitor y otro que espera por él: BLOCKED.
        Object candado = new Object();
        CountDownLatch tieneCandado = new CountDownLatch(1);
        CountDownLatch liberarCandado = new CountDownLatch(1);
        Thread propietario = new Thread(() -> {
            synchronized (candado) {
                tieneCandado.countDown();
                try { liberarCandado.await(); }
                catch (InterruptedException e) { Thread.currentThread().interrupt(); }
            }
        });
        propietario.start(); tieneCandado.await();
        Thread bloqueado = new Thread(() -> {
            synchronized (candado) { System.out.println("Candado obtenido."); }
        });
        bloqueado.start(); Thread.sleep(50);
        System.out.println("Esperando monitor (habitual): " + bloqueado.getState());
        // Liberamos todos los hilos y esperamos su finalización.
        desbloquear.countDown(); liberarCandado.countDown();
        dormilon.join(); esperando.join(); propietario.join(); bloqueado.join();
        System.out.println("Tras join: " + dormilon.getState()); // TERMINATED
        // Nota: RUNNABLE incluye preparado y ejecutándose; observarlo exacto no es determinista.
    }
}
