/**
 * Clase que encapsula el estado compartido.
 * Los métodos synchronized de instancia usan el monitor de ESTE objeto.
 */
public class ContadorSeguro {
    // private impide modificar directamente el valor desde fuera.
    private int valor = 0;
    // Si dos hilos llaman al método SOBRE EL MISMO OBJETO,
    // solo uno podrá ejecutar el cuerpo a la vez.
    public synchronized void incrementar() {
        valor++; // Ahora esta operación queda protegida por el monitor.
    }
    // Protegemos la lectura con el mismo monitor para ser coherentes.
    public synchronized int getValor() { return valor; }
}
