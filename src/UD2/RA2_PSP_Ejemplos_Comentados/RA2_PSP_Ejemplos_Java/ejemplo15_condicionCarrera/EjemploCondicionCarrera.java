/** DIAPOSITIVAS 28-30. Contador++ NO es una operación atómica. */
public class EjemploCondicionCarrera {
    // Campo estático común para los dos hilos de este mismo proceso.
    // volatile ayuda a la visibilidad pero NO convierte contador++ en operación atómica.
    private static volatile int contador = 0;
    public static void main(String[] args) throws InterruptedException {
        Runnable  incrementar = () -> {
            for (int i = 0; i < 200_000; i++) {
                // Operación compuesta: leer, sumar y escribir.
                // Dos hilos pueden leer el mismo valor y perder un incremento.
                incrementar();
            }
        };
        Thread a = new Thread(incrementar, "Sumador-A");
        Thread b = new Thread(incrementar, "Sumador-B");
        a.start(); b.start();
        // Sin join podríamos leer el resultado ANTES de terminar las tareas.
        a.join(); b.join();
        System.out.println("Esperado: 400000");
        System.out.println("Obtenido: " + valor);
        // El resultado puede ser correcto por casualidad; NO hay garantía.
        // Ejecútalo varias veces y compáralo con synchronized y AtomicInteger.
    }
    private static  int valor = 0;
    // Si dos hilos llaman al método SOBRE EL MISMO OBJETO,
    // solo uno podrá ejecutar el cuerpo a la vez.
    public static synchronized void incrementar() {
        valor++; // Ahora esta operación queda protegida por el monitor.
    }
    // Protegemos la lectura con el mismo monitor para ser coherentes.
    public synchronized int getValor() { return valor; }
}
