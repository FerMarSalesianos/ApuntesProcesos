/**
 * DIAPOSITIVA 38. volatile aporta visibilidad, NO atomicidad.
 * La bandera compartida indica a un hilo que debe terminar.
 */
public class EjemploVolatile {
    // volatile garantiza que las escrituras serán visibles al otro hilo.
    private static volatile boolean seguir = true;
    public static void main(String[] args) throws InterruptedException {
        Thread trabajador = new Thread(() -> {
            long vueltas = 0;
            // Cada consulta de seguir observa el valor con semántica volatile.
            while (seguir) {
                vueltas++;
                // No utilizamos sleep porque queremos demostrar una lectura repetida.
            }
            System.out.println("El trabajador recibió la señal tras " + vueltas + " vueltas.");
        }, "Trabajador");
        trabajador.start();
        // Simulamos que main deja trabajar al otro hilo brevemente.
        Thread.sleep(300);
        // La escritura volatile será visible para las lecturas posteriores.
        seguir = false;
        trabajador.join();
        System.out.println("Trabajador detenido.");
        // IMPORTANTE: volatile NO vuelve atómico un contador++.
        // Para eso usa synchronized o AtomicInteger (otros ejemplos).
    }
}
