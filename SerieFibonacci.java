/*Ingresado un número de elementos a generar determinar la serie de Fibonassi desde 1.*/
package Espejo2;
import java.util.Scanner;

public class SerieFibonacci {
    public static void main(String[] args) {
        // Declarar variables
        int numeroElementos;
        int termino1 = 0;
        int termino2 = 1;

        // Leer el número de elementos ingresado por el usuario
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el número de elementos de la serie de Fibonacci a generar: ");
        numeroElementos = scanner.nextInt();

        // Imprimir los primeros n términos de la serie de Fibonacci
        System.out.println("Serie de Fibonacci:");

        // Imprimir el primer término (si n es mayor que 0)
        if (numeroElementos >= 1) {
            System.out.print(termino1 + " ");
        }

        // Imprimir el segundo término (si n es mayor que 1)
        if (numeroElementos >= 2) {
            System.out.print(termino2 + " ");
        }

        // Calcular y imprimir los términos restantes
        for (int i = 3; i <= numeroElementos; i++) {
            int siguienteTermino = termino1 + termino2;
            System.out.print(siguienteTermino + " ");

            // Actualizar los términos para el siguiente cálculo
            termino1 = termino2;
            termino2 = siguienteTermino;
        }

        // Cerrar el scanner
        scanner.close();
    }
}
