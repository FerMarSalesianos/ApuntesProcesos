/** DIAPOSITIVA 20. Asignar y consultar nombres descriptivos. */
public class EjemploNombres {
    public static void main(String[] args) throws InterruptedException {
        // La misma tarea imprime el nombre del hilo que la está ejecutando.
        Runnable tarea = () -> System.out.println("Ejecutando: "
            + Thread.currentThread().getName());
        // Hay un constructor Thread(Runnable, String) que asigna nombre.
        Thread a = new Thread(tarea, "Descarga-Imagenes");
        Thread b = new Thread(tarea);
        // También podemos modificar el nombre antes de iniciar.
        b.setName("Descarga-Documentos");
        System.out.println("Se iniciarán: " + a.getName() + " y " + b.getName());
        a.start(); b.start();
        a.join(); b.join();
    }
}
