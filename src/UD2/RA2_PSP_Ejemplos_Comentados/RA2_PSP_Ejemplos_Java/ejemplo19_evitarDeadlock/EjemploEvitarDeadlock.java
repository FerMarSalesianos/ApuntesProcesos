/** DIAPOSITIVA 33. Prevención de un interbloqueo. */
public class EjemploEvitarDeadlock {
    private static final Object RECURSO_A = new Object();
    private static final Object RECURSO_B = new Object();
    public static void main(String[] args) throws InterruptedException {
        // Los dos hilos usan la MISMA secuencia de adquisición A -> B.
        Runnable tarea = () -> {
            // Nadie puede obtener B mientras retiene A en orden inverso.
            synchronized (RECURSO_A) {
                System.out.println(Thread.currentThread().getName() + " tiene A");
                synchronized (RECURSO_B) {
                    System.out.println(Thread.currentThread().getName() + " tiene A y B");
                } // B se libera aquí.
            } // A se libera aquí.
            System.out.println(Thread.currentThread().getName() + " terminó.");
        };
        Thread a = new Thread(tarea, "Trabajador-A");
        Thread b = new Thread(tarea, "Trabajador-B");
        a.start(); b.start();
        a.join(); b.join();
        System.out.println("No hubo espera circular; todos terminaron.");
    }
}
