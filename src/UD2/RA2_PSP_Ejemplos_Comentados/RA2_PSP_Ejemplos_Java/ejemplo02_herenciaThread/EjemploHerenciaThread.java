/** DIAPOSITIVAS 6-7. Uso de extends Thread. */
public class EjemploHerenciaThread {
    public static void main(String[] args) throws InterruptedException {
        // Instanciamos dos objetos Thread de la clase que hemos definido.
        MiHilo a = new MiHilo("Hilo-A");
        MiHilo b = new MiHilo("Hilo-B");
        // start() crea cada hilo y programa la ejecución de su run().
        a.start();
        b.start();
        // main también continúa: los mensajes pueden intercalarse.
        System.out.println("main también se está ejecutando.");
        // join() evita finalizar el ejemplo antes de observar ambos finales.
        a.join();
        b.join();
        System.out.println("Todos han terminado.");
    }
}
