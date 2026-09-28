package ud1.comunicacionpadrehijo;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class CalculadoraPadre {

    public static void main(String[] args) {

        String java = System.getProperty("java.home")
                + File.separator
                + "bin"
                + File.separator
                + "java";

        String classpath = System.getProperty("java.class.path");

        ProcessBuilder pb = new ProcessBuilder(
                java,
                "-cp",
                classpath,
                "comunicacionpadrehijo.CalculadoraHijo"
        );

        try {
            Process proceso = pb.start();

            try (
                PrintWriter escritor = new PrintWriter(
                        proceso.getOutputStream(),
                        true
                );

                BufferedReader lector = new BufferedReader(
                        new InputStreamReader(
                                proceso.getInputStream()
                        )
                )
            ) {

                int numero = 8;

                System.out.println(
                        "PADRE > Enviando número: " + numero
                );

                escritor.println(numero);

                String respuesta = lector.readLine();

                System.out.println(
                        "PADRE > Resultado recibido: "
                        + respuesta
                );
            }

            int codigoSalida = proceso.waitFor();

            System.out.println(
                    "Proceso hijo finalizado con código: "
                    + codigoSalida
            );

        } catch (IOException e) {

            System.out.println(
                    "Error de comunicación con el proceso hijo."
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