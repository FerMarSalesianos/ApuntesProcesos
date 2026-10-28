/** DIAPOSITIVAS 13-14. Dos descargas simuladas con pausas. */
public class EjemploSleep {
    public static void main(String[] args) throws InterruptedException {
        Runnable descargar = () -> {
            for (int parte = 1; parte <= 4; parte++) {
                System.out.println(Thread.currentThread().getName() + " - parte " + parte);
                try {
                    // sleep(400) suspende solo ESTE hilo unos 400 ms.
                    // Tras ese plazo NO se garantiza una reanudación inmediata.
                    Thread.sleep(400);
                } catch (InterruptedException e) {
                    // sleep lanza la excepción si se solicita la interrupción al esperar.
                    // La excepción limpia el indicador; restaurarlo permite a otro código verlo.
                    Thread.currentThread().interrupt();
                    System.out.println("Descarga interrumpida: "
                        + Thread.currentThread().getName());
                    // Terminamos cooperativamente la tarea.
                    return;
                }
            }
            System.out.println(Thread.currentThread().getName() + " completada.");
        };
        Thread a = new Thread(descargar, "Descarga-1");
        Thread b = new Thread(descargar, "Descarga-2");
        a.start(); b.start();
        // main puede esperar; dormir un hilo no implica dormir otro.
        a.join(); b.join();
        System.out.println("Todas las descargas terminaron.");
    }
}
