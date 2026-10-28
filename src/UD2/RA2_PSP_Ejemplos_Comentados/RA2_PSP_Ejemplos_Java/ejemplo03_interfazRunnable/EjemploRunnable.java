/** DIAPOSITIVAS 8-10. Dos objetos Thread, dos tareas Runnable. */
public class EjemploRunnable {
    public static void main(String[] args) throws InterruptedException {
        // Dos tareas distintas: la clase NO hereda de Thread.
        TareaDescarga imagenes = new TareaDescarga("imagenes.zip");
        TareaDescarga documentos = new TareaDescarga("documentos.zip");
        // Thread recibe un Runnable y opcionalmente un nombre.
        Thread hilo1 = new Thread(imagenes, "Descarga-Imagenes");
        Thread hilo2 = new Thread(documentos, "Descarga-Documentos");
        // Cada tarea se ejecutará en su propio hilo.
        hilo1.start();
        hilo2.start();
        // Esperamos ambos finales antes de imprimir el resumen.
        hilo1.join();
        hilo2.join();
        System.out.println("Descargas finalizadas.");
    }
}
