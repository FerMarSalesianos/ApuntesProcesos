package ud1;
import java.io.IOException;

public class Ejemplo01CrearProceso {

    public static void main(String[] args) {

        /*
        herramienta que se va a encargar de generar los procesos hijos,
        en este caso el proceso hijo es un proceso java que ejecuta la clase "java -version"

        De esta manera se puede ejecutar cualquier proceso externo al programa java, 
        ya que el proceso hijo es independiente del proceso padre.

        Si quisieramos ejecutar un proceso que no sea de java se puede hacer de la siguiente manera:
        ProcessBuilder pb = new ProcessBuilder("nombre_del_proceso", "argumento1", "argumento2", ...);
        */
        ProcessBuilder pb = new ProcessBuilder("java", "-version");

        try {
            /*
            creamos 3 procesos distintos, que ejecutan el mismo comando "java -version",
            pero los guardamos en 3 variables diferentes para poder controlarlos individualmente
            */
            Process proceso = pb.start();
            Process Arhamys = pb.start();
            Process Lugan = pb.start();

            /*
            Mostramos el PID del proceso padre y de los procesos hijos,
            para poder identificarlos en el sistema operativo
            
             */
            System.out.println("El proceso se ha iniciado.");
            //PID: Identificador unico de cada proceso,
            // aunque un proceso se ejecute a la vez 2 veces, cada una de estas tendrá un PID unico
            System.out.println("PID: " + proceso.pid());
            System.out.println("PID LuGan: " + Lugan.pid());
            System.out.println("PID: Arhamys: " + Arhamys.pid());
            //isAlive comprueba que el proceso siga vivo retornando True o False
            if (proceso.isAlive()) {
                System.out.println("El proceso sigue vivo.");
                System.out.println("Matando el proceso...");
                //destroy da la instrucción de terminar el proceso. NO ES INSTANTANEO
                Arhamys.destroy();
                //el proceso principal de este código se detiene hasta que el proceso Lugan termine,
                //una vez termina el proceso lugan, el proceso padre sigue con normalidad
                Lugan.waitFor();
            }
            //se pueden dar casos donde un proceso tarde en destruirse.
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
