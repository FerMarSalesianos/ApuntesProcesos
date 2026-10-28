/** DIAPOSITIVA 12. start() y run() NO son equivalentes. */
public class EjemploStartVsRun {
    public static void main(String[] args) throws InterruptedException {
        Runnable tarea = () -> {
            // El nombre del hilo actual permite identificar quién ejecuta el código.
            System.out.println("Trabajo ejecutado por: "
                + Thread.currentThread().getName());
        };
        Thread primero = new Thread(tarea, "Hilo-Run");
        Thread segundo = new Thread(tarea, "Hilo-Start");
        System.out.println("Antes de invocar run(): " + Thread.currentThread().getName());
        // Ejecuta run() en el hilo que realiza esta llamada (aquí main).
        primero.run();
        System.out.println("Antes de invocar start(): " + Thread.currentThread().getName());
        // Ahora sí se inicia un nuevo hilo, distinto del main.
        segundo.start();
        // join garantiza que el mensaje de cierre aparezca al final.
        segundo.join();
        System.out.println("Demostración terminada.");
    }
}
