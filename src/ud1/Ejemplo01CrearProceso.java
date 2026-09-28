package ud1;
import java.io.IOException;

public class Ejemplo01CrearProceso {

    public static void main(String[] args) {

        ProcessBuilder pb = new ProcessBuilder("java", "-version");

        try {
            Process proceso = pb.start();
            Process Arhamys = pb.start();
            Process Lugan = pb.start();
            System.out.println("El proceso se ha iniciado.");
            System.out.println("PID: " + proceso.pid());
            System.out.println("PID LuGan: " + Lugan.pid());
            System.out.println("PID: Arhamys: " + Arhamys.pid());
            if (proceso.isAlive()) {
                System.out.println("El proceso sigue vivo.");
                System.out.println("Matando el proceso...");
                Arhamys.destroy();
                Lugan.waitFor();
            }
            if (proceso.isAlive()) {
                System.out.println("¿CÓMO SIGUE VIVO?");
            } else {
                System.out.println("ESE COMPA YA ESTÁ MUERTO NOMAS NO LE HAN AVISADO!!!!");
            }
            if(Lugan.isAlive()){
                System.out.println("Lugan sigue viva");
            } else {
                System.out.println("Lugan ya murió");
            }
            if (Arhamys.isAlive()){
                System.out.println("Arhamys sigue viva");
            } else {
                System.out.println("Arhamys ya murió");
            }
        } catch (IOException e) {
            System.out.println("No se ha podido iniciar el proceso.");
            System.out.println(e.getMessage());

        } catch (InterruptedException e) {
              System.out.println("La espera del proceso ha sido interrumpida.");
          }
    }
}
