package ud1.padrehijo;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProcesoPadre {

    public static void main(String[] args) {

        String java = System.getProperty("java.home")
                + File.separator
                + "bin"
                + File.separator
                + "java";

                //este es el classpath del proyecto, que se obtiene de la propiedad del sistema "java.class.path" no necesita ser modificado, ya que se obtiene automáticamente del entorno de ejecución
                //(traduccion pa tontos) no hacer falta poner ruta.
        String classpath = System.getProperty("java.class.path");
        String rutaClaseHijo = "ud1.padrehijo.ProcesoHijo";
        String nombre = "Fernando";
        String numero = "25";

        ProcessBuilder pb = new ProcessBuilder(
                java,
                "-cp",
                classpath,
                rutaClaseHijo,
                nombre,
                numero
        );

        pb.redirectErrorStream(true);

        try {
            Process proceso = pb.start();

            nombre = "Arhamys";
            numero = "30";
            rutaClaseHijo = "ud1.padrehijo.ProcesoRandom";
            Process proceso2 = pb.start();
            
            System.out.println(
                    "PID del proceso padre: "
                    + ProcessHandle.current().pid()
            );

            System.out.println(
                    "PID del proceso hijo: "
                    + proceso.pid()
            );

            try (BufferedReader lector = new BufferedReader(
                    new InputStreamReader(proceso.getInputStream()))) {

                String linea;

                while ((linea = lector.readLine()) != null) {
                    System.out.println("HIJO > " + linea);
                }
            }

            int codigo = proceso.waitFor();

            System.out.println(
                    "El proceso hijo terminó con código: "
                    + codigo
            );

        } catch (IOException e) {

            System.out.println(
                    "No se pudo ejecutar el proceso hijo."
            );

            System.out.println(e.getMessage());

        } catch (InterruptedException e) {

            System.out.println(
                    "La espera fue interrumpida."
            );

            Thread.currentThread().interrupt();
        }
    }
}
