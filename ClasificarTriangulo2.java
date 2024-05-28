/*estructuras de seleccion: if < condicion then  end if;
ingresado los 3 angulos de un triangulo determinar si este es isiseles, escaleno, rectangulo, equilatero 
*/

package Espejo2;
import java.util.Scanner;

public class ClasificarTriangulo2 {
   

    public static void main(String[] args) {
        // Declaración de variables
        double angulo1;
        double angulo2;
        double angulo3;

        // Leer los valores de los ángulos ingresados por el usuario
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el valor del primer ángulo: ");
        angulo1 = scanner.nextDouble();
        System.out.print("Ingrese el valor del segundo ángulo: ");
        angulo2 = scanner.nextDouble();
        System.out.print("Ingrese el valor del tercer ángulo: ");
        angulo3 = scanner.nextDouble();

        // Verificar si los ángulos forman un triángulo válido
        if (angulo1 <= 0 || angulo2 <= 0 || angulo3 <= 0 || angulo1 + angulo2 + angulo3 != 180) {
            System.out.println("Los ángulos ingresados no forman un triángulo válido.");
        } else {
            // Verificar el tipo de triángulo
            if (angulo1 == angulo2 && angulo2 == angulo3) {
                System.out.println("El triángulo es equilátero.");
            } else if (angulo1 == 90 || angulo2 == 90 || angulo3 == 90) {
                if (angulo1 == angulo2 || angulo2 == angulo3 || angulo1 == angulo3) {
                    System.out.println("El triángulo es rectángulo isósceles.");
                } else {
                    System.out.println("El triángulo es rectángulo.");
                }
            } else if (angulo1 == angulo2 || angulo2 == angulo3 || angulo1 == angulo3) {
                System.out.println("El triángulo es isósceles.");
            } else {
                System.out.println("El triángulo es escaleno.");
            }
        }

        // Cerrar el scanner
        scanner.close();
    }
}
