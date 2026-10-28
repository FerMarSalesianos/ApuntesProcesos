import java.util.concurrent.atomic.AtomicInteger;
/** DIAPOSITIVAS 30-31. Alternativa atómica para un contador sencillo. */
public class EjemploAtomicInteger {
    // Importante: ambos hilos deben compartir ESTE MISMO AtomicInteger.
    private static final AtomicInteger contador = new AtomicInteger(0);
    public static void main(String[] args) throws InterruptedException {
        Runnable tarea = () -> {
            for (int i = 0; i < 200_000; i++) {
                // incrementAndGet incrementa de manera atómica.
                // Devuelve el valor nuevo, aunque en este ejemplo lo ignoramos.
                contador.incrementAndGet();
            }
        };
        Thread a = new Thread(tarea);
        Thread b = new Thread(tarea);
        a.start(); b.start();
        a.join(); b.join();
        // get() obtiene el valor compartido actual.
        System.out.println("Valor esperado: 400000");
        System.out.println("Valor obtenido: " + contador.get());
        // set() permite asignar un valor nuevo de forma atómica.
        contador.set(0);
        System.out.println("Reiniciado con set(0): " + contador.get());
        // AtomicInteger es útil para contadores, pero no sustituye en todos
        // los casos a sincronizar varias operaciones relacionadas entre sí.
    }
}
