package ud1.variosprocesos;

import java.io.File;
import java.io.IOException;

public class Empresa {

    public static void main(String[] args) {

        String java = System.getProperty("java.home")
                + File.separator
                + "bin"
                + File.separator
                + "java";

        String classpath = System.getProperty(
                "java.class.path"
        );

        ProcessBuilder pb1 = new ProcessBuilder(
                java,
                "-cp",
                classpath,
                "ud1.variosprocesos.Trabajador",
                "Trabajador 1",
                "3"
        );

        ProcessBuilder pb2 = new ProcessBuilder(
                java,
                "-cp",
                classpath,
                "ud1.variosprocesos.Trabajador",
                "Trabajador 2",
                "5"
        );

        ProcessBuilder pb3 = new ProcessBuilder(
                java,
                "-cp",
                classpath,
                "ud1.variosprocesos.Trabajador",
                "Trabajador 3",
                "20"
        );

        pb1.inheritIO();
        pb2.inheritIO();
        pb3.inheritIO();

        try {

            Process proceso1 = pb1.start();
            Process proceso2 = pb2.start();
            Process proceso3 = pb3.start();

            System.out.println(
                    "Los tres procesos están ejecutándose."
            );

            proceso1.waitFor();
            proceso2.waitFor();
            proceso3.waitFor();

            System.out.println(
                    "Todos los procesos han terminado."
            );

        } catch (IOException e) {

            System.out.println(
                    "Error al crear los procesos."
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
