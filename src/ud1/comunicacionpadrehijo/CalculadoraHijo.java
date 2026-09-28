package ud1.comunicacionpadrehijo;
import java.util.Scanner;

public class CalculadoraHijo {

    public static void main(String[] args) {

        try (Scanner teclado = new Scanner(System.in)) {

            int numero = teclado.nextInt();

            int resultado = numero * numero;

            System.out.println(resultado);
        }
    }
}
