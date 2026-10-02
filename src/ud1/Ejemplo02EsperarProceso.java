package ud1;
import java.io.IOException;

public class Ejemplo02EsperarProceso {

    public static void main(String[] args) {

        ProcessBuilder pb = new ProcessBuilder("java", "-version");

        try {
            Process proceso = pb.start();

            System.out.println("Proceso iniciado.");
            System.out.println("PID: " + proceso.pid());
            //waitfor retorna un entero indicando como ha terminado el proceso
            //recordemos que con 0 el proceso termina de forma existosa, y con !=0 indica fallo
            int codigoSalida = proceso.waitFor();

            System.out.println("Proceso terminado.");
            System.out.println("Código de salida: " + codigoSalida);

        } catch (IOException e) {
            System.out.println("Error al crear el proceso.");
            System.out.println(e.getMessage());

        } catch (InterruptedException e) {
            System.out.println("La espera del proceso fue interrumpida.");
            Thread.currentThread().interrupt();
        }
    }
}