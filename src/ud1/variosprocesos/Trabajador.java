package ud1.variosprocesos;

//proceso hijo
public class Trabajador {

    public static void main(String[] args) {

        String nombre = args[0];

        int segundos = Integer.parseInt(args[1]);

        System.out.println(
                nombre + " comienza su trabajo."
        );

        try {

            Thread.sleep(segundos * 1000);

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();
        }

        System.out.println(
                nombre + " ha terminado."
        );
    }
}
