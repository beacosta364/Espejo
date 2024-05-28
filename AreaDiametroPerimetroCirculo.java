
package Espejo2;
import java.util.Scanner;
/*calcular el area, el diametro y el perimetro de un circulo dando el radio*/
public class AreaDiametroPerimetroCirculo {
    public static void main(String[] args) {
        // Declaración de constantes y variables
        final double PI = 3.14159;
        double radio;
        double area;
        double diametro;
        double perimetro;

        // Leer el valor del radio ingresado por el usuario
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el radio del círculo: ");
        radio = scanner.nextDouble();

        // Calcular el área
        area = PI * Math.pow(radio, 2);

        // Calcular el diámetro
        diametro = 2 * radio;

        // Calcular el perímetro
        perimetro = 2 * PI * radio;

        // Imprimir los resultados
        System.out.println("El área del círculo con radio " + radio + " es: " + area);
        System.out.println("El diámetro del círculo con radio " + radio + " es: " + diametro);
        System.out.println("El perímetro del círculo con radio " + radio + " es: " + perimetro);

        // Cerrar el scanner
        scanner.close();
    }
}
