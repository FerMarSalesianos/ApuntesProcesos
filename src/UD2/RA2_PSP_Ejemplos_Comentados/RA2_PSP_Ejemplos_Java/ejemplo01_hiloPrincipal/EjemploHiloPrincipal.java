/**
 * DIAPOSITIVAS 2-5. El hilo principal de un programa Java.
 * Este ejemplo no crea hilos adicionales: inspecciona el hilo que ejecuta main().
 */
public class EjemploHiloPrincipal {
    public static void main(String[] args) {
        // currentThread() devuelve el objeto Thread correspondiente al hilo actual.
        Thread principal = Thread.currentThread();
        // getName(): nombre legible asignado al hilo; normalmente "main".
        System.out.println("Nombre: " + principal.getName());
        // threadId() (Java 19+) devuelve el identificador del hilo dentro de la JVM.
        // Utilizamos getId() para mantener compatibilidad con Java 17 (aunque deprecated en Java 21).
        System.out.println("ID: " + principal.getId());
        // La prioridad no garantiza un orden de ejecución.
        System.out.println("Prioridad: " + principal.getPriority());
        // Mientras está ejecutando instrucciones, su estado suele ser RUNNABLE.
        System.out.println("Estado: " + principal.getState());
        // isAlive() indica si el hilo ya se inició y todavía no terminó.
        System.out.println("Activo: " + principal.isAlive());
        // Una aplicación puede tener otros hilos internos de la JVM además del principal.
    }
}
