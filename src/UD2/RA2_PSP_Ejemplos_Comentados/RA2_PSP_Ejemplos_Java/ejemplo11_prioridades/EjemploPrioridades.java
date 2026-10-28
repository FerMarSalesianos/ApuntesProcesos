/** DIAPOSITIVAS 21-22. Prioridad no significa orden garantizado. */
public class EjemploPrioridades {
    public static void main(String[] args) throws InterruptedException {
        Runnable trabajo = () -> {
            // La salida permite identificar al hilo que se ejecutó.
            for (int i = 1; i <= 3; i++) {
                System.out.println(Thread.currentThread().getName() + " paso " + i);
            }
        };
        Thread baja = new Thread(trabajo, "Prioridad-baja");
        Thread normal = new Thread(trabajo, "Prioridad-normal");
        Thread alta = new Thread(trabajo, "Prioridad-alta");
        // Valores convencionales de 1, 5 y 10 para hilos de plataforma.
        baja.setPriority(Thread.MIN_PRIORITY);
        normal.setPriority(Thread.NORM_PRIORITY);
        alta.setPriority(Thread.MAX_PRIORITY);
        System.out.println(baja.getName() + ": " + baja.getPriority());
        System.out.println(normal.getName() + ": " + normal.getPriority());
        System.out.println(alta.getName() + ": " + alta.getPriority());
        // El planificador puede alternar hilos en cualquier orden.
        baja.start(); normal.start(); alta.start();
        baja.join(); normal.join(); alta.join();
        System.out.println("No se puede deducir el orden de la prioridad.");
    }
}
