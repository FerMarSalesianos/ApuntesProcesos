/**
 * Una tarea Runnable NO es por sí misma un hilo.
 * Puede entregarse a uno o a varios objetos Thread.
 */
public class TareaDescarga implements Runnable {
    // Atributo que personaliza la tarea independientemente del hilo.
    private final String archivo;
    public TareaDescarga(String archivo) { this.archivo = archivo; }
    @Override
    public void run() {
        // Aquí se ejecuta el trabajo definido por la interfaz Runnable.
        for (int paso = 1; paso <= 3; paso++) {
            System.out.println(Thread.currentThread().getName()
                + " descarga " + archivo + ": paso " + paso);
        }
    }
}
