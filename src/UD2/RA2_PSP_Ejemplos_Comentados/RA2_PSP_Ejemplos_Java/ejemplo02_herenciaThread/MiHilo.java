/** Representa un hilo concreto porque hereda de Thread. */
public class MiHilo extends Thread {
    // Constructor: permite ponerle nombre al hilo mediante la superclase.
    public MiHilo(String nombre) { super(nombre); }
    @Override
    public void run() {
        // run() contiene las instrucciones que ejecutará el nuevo hilo.
        // No llamamos a run() directamente desde main: usamos start().
        for (int i = 1; i <= 4; i++) {
            System.out.println(getName() + " -> paso " + i);
        }
        System.out.println(getName() + " ha terminado su tarea.");
    }
}
