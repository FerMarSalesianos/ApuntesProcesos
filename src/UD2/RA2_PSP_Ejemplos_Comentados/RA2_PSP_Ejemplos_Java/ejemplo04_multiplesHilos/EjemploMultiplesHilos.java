/** DIAPOSITIVAS 7 Y 11. Tres hilos concurrentes. */
public class EjemploMultiplesHilos {
    public static void main(String[] args) throws InterruptedException {
        // Un Runnable es una tarea compartida; cada hilo tiene su propio recorrido del for.
        Runnable tarea = () -> {
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
                // yield() es solo una sugerencia al planificador; no garantiza alternancia.
                Thread.yield();
            }
        };
        Thread a = new Thread(tarea, "Tarea-A");
        Thread b = new Thread(tarea, "Tarea-B");
        Thread c = new Thread(tarea, "Tarea-C");
        // Al iniciar los hilos no controlamos en qué orden imprimirán.
        a.start(); b.start(); c.start();
        // El hilo main espera a los tres antes del mensaje final.
        a.join(); b.join(); c.join();
        System.out.println("Las tres tareas terminaron.");
    }
}
