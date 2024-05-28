/*Realizar un pl/sql que determine la luz a seguir de un semaforo dependiendo de una luz inicial ingresada*/
package Espejo2;
import java.util.Scanner;

public class Semaforo {
    public static void main(String[] args) {
        // Declarar variables
        String luz_siguiente = "";
        String luz_anterior;
        String luz_actual;

        // Leer la luz anterior y la luz actual ingresadas por el usuario
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese la luz anterior: ");
        luz_anterior = scanner.nextLine();
        System.out.print("Ingrese la luz actual: ");
        luz_actual = scanner.nextLine();

        // Determinar la luz siguiente
        if (luz_actual.equals("VERDE")) {
            luz_siguiente = "AMARILLO";
        } else if (luz_actual.equals("AMARILLO")) {
            if (luz_anterior.equals("ROJO")) {
                luz_siguiente = "VERDE";
            } else {
                luz_siguiente = "ROJO";
            }
        } else if (luz_actual.equals("ROJO")) {
            luz_siguiente = "VERDE";
        } else {
            System.out.println("Luz ingresada no válida.");
            return;
        }

        // Imprimir la luz siguiente
        System.out.println("La luz siguiente es: " + luz_siguiente);

        // Cerrar el scanner
        scanner.close();
    }
}
