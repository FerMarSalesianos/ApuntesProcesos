/** DIAPOSITIVA 16. Esperar la finalización con join(). */
public class EjemploJoin {
    public static void main(String[] args) throws InterruptedException {
        Thread trabajador = new Thread(() -> {
            System.out.println("El trabajador comienza.");
            try {
                // Simulamos trabajo que dura 900 milisegundos.
                Thread.sleep(900);
            } catch (InterruptedException e) {
                // Si se interrumpe, notificamos y terminamos.
                Thread.currentThread().interrupt();
                return;
            }
            System.out.println("El trabajador termina.");
        }, "Trabajador");
        // Inicia el nuevo hilo; main no espera automáticamente aquí.
        trabajador.start();
        System.out.println("main: esperando al trabajador...");
        // Bloquea al hilo que llama (main) hasta que termine trabajador.
        trabajador.join();
        // El mensaje final siempre aparece después del final del trabajador.
        System.out.println("main: el trabajador ya ha terminado.");
    }
}
