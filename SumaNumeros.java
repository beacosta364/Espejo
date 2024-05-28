
package Espejo2;
/*ingresado un numero realizar la sumatoria desde 1 hasta el numero ingresado con While*/

import java.util.Scanner;

public class SumaNumeros {
    public static void main(String[] args) {
        // Declarar variables
        int numero;
        int suma = 0;
        int contador = 1;

        // Leer el número ingresado por el usuario
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese un número: ");
        numero = scanner.nextInt();

        // Calcular la suma de los números naturales hasta el número ingresado
        while (contador <= numero) {
            suma += contador;
            contador++;
        }

        // Imprimir la suma
        System.out.println("La suma de los números naturales hasta " + numero + " es: " + suma);

        // Cerrar el scanner
        scanner.close();
    }
}
