package ud1;
public class Ejemplo06ProcessHandle {
    public static void main(String[] args) {

        ProcessHandle procesoActual = ProcessHandle.current();

        System.out.println(
                "PID: " + procesoActual.pid()
        );

        System.out.println(
                "¿Está ejecutándose?: "
                + procesoActual.isAlive()
        );

        System.out.println(
                "Información: "
                + procesoActual.info()
        );
    }
}
