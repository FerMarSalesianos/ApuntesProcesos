/** DIAPOSITIVAS 24-25. Distintas implementaciones de Runnable. */
public class EjemploLambdas {
    public static void main(String[] args) throws InterruptedException {
        // 1) Clase anónima: escribimos explícitamente el método run().
        Runnable tareaAnonima = new Runnable() {
            @Override
            public void run() {
                System.out.println("Clase anónima desde "
                    + Thread.currentThread().getName());
            }
        };
        // 2) Lambda: Runnable tiene un único método abstracto, run().
        // El compilador interpreta el cuerpo de la lambda como ese método.
        Runnable tareaLambda = () -> {
            System.out.println("Lambda desde " + Thread.currentThread().getName());
        };
        // Una lambda NO lanza un hilo por sí misma.
        // El objeto Thread recibe cada tarea e inicia el hilo con start().
        Thread a = new Thread(tareaAnonima, "ClaseAnonima");
        Thread b = new Thread(tareaLambda, "Lambda");
        a.start(); b.start();
        a.join(); b.join();
        // Lambda corta cuando el cuerpo contiene solo una expresión.
        new Thread(() -> System.out.println("Lambda breve"), "Breve").start();
    }
}
