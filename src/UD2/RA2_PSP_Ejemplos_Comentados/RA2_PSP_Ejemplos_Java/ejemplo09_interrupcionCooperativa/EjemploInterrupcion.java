/** DIAPOSITIVAS 17-19, 27. Cancelación cooperativa. */
public class EjemploInterrupcion {
    public static void main(String[] args) throws InterruptedException {
        Thread trabajador = new Thread(() -> {
            int vuelta = 0;
            // isInterrupted() consulta el indicador SIN borrarlo.
            while (!Thread.currentThread().isInterrupted()) {
                vuelta++;
                System.out.println("Trabajando... " + vuelta);
                try {
                    // sleep puede terminar anticipadamente si otro hilo llama interrupt().
                    Thread.sleep(350);
                } catch (InterruptedException e) {
                    // Esta excepción limpia el indicador de interrupción.
                    System.out.println("Se ha recibido una solicitud de interrupción.");
                    // Restablecemos el indicador para que el while finalice.
                    Thread.currentThread().interrupt();
                }
            }
            // El propio hilo decide salir de manera ordenada.
            System.out.println("El trabajador termina voluntariamente.");
        }, "Trabajador");
        trabajador.start();
        // El hilo principal deja transcurrir tiempo para observar varias vueltas.
        Thread.sleep(1100);
        System.out.println("main solicita detener al trabajador.");
        // interrupt NO mata el hilo: comunica la petición.
        trabajador.interrupt();
        // Evitamos continuar hasta que se produzca la salida del hilo.
        trabajador.join();
        System.out.println("Programa terminado.");
    }
}
