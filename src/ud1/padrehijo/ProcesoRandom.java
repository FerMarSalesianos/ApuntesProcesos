package ud1.padrehijo;

public class ProcesoRandom {

    public static void main(String[] args) {

        System.out.println("Soy el proceso hijo.");

        System.out.println(
                "PID del proceso hijo: "
                + ProcessHandle.current().pid()
        );

        if (args.length >= 2) {

            String nombre = args[0];
            int numero = Integer.parseInt(args[1]);

            System.out.println("Nombre recibido: " + nombre);
            System.out.println("Número recibido: " + numero);
            System.out.println("Número x 10: " + (numero * 10));

        } else {

            System.out.println("No se han recibido suficientes argumentos.");
        }
    }
}
