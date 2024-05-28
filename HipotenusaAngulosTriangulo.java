
package Espejo2;
import java.util.Scanner;
/*ingresado los catetos de un triangulo rectangulo calcular su hipotenusa y calcular sus angulos adyacentes en grados, todos los angulos de un triangulo suma 180 grados*/

public class HipotenusaAngulosTriangulo {
    public static void main(String[] args) {
        // Declaración de variables
        double cateto1;
        double cateto2;
        double hipotenusa;
        double angulo1;
        double angulo2;

        // Leer los valores de los catetos ingresados por el usuario
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el valor del primer cateto: ");
        cateto1 = scanner.nextDouble();
        System.out.print("Ingrese el valor del segundo cateto: ");
        cateto2 = scanner.nextDouble();

        // Calcular la hipotenusa
        hipotenusa = Math.sqrt(cateto1 * cateto1 + cateto2 * cateto2);

        // Calcular los ángulos en grados
        angulo1 = Math.toDegrees(Math.asin(cateto1 / hipotenusa));
        angulo2 = Math.toDegrees(Math.asin(cateto2 / hipotenusa));

        // Imprimir los resultados
        System.out.println("La hipotenusa es: " + hipotenusa);
        System.out.println("El ángulo adyacente al cateto1 es: " + angulo1 + " grados");
        System.out.println("El ángulo adyacente al cateto2 es: " + angulo2 + " grados");

        // Cerrar el scanner
        scanner.close();
    }
}
