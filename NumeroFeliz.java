/*Ingresado un número por teclado determinar si es un número Feliz o Infeliz*/
package Espejo2;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
public class NumeroFeliz {
   
    public static void main(String[] args) {
        // Leer el número ingresado por el usuario
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese un número para verificar si es feliz o infeliz: ");
        int numero = scanner.nextInt();

        // Verificar si el número es feliz
        if (esFeliz(numero)) {
            System.out.println(numero + " es un número feliz.");
        } else {
            System.out.println(numero + " es un número infeliz.");
        }

        // Cerrar el scanner
        scanner.close();
    }

    // Función para verificar si un número es feliz
    public static boolean esFeliz(int numero) {
        Set<Integer> visitados = new HashSet<>();

        while (true) {
            int sumaDigitosCuadrados = 0;

            // Calcular la suma de los cuadrados de los dígitos
            while (numero != 0) {
                int digito = numero % 10;
                sumaDigitosCuadrados += digito * digito;
                numero /= 10;
            }

            // Verificar si la suma es 1 (número feliz)
            if (sumaDigitosCuadrados == 1) {
                return true;
            }

            // Verificar si el resultado ya ha sido visitado
            if (visitados.contains(sumaDigitosCuadrados)) {
                return false; // Se ha formado un ciclo, el número es infeliz
            }

            // Agregar la suma a los números visitados y actualizar el número para el siguiente cálculo
            visitados.add(sumaDigitosCuadrados);
            numero = sumaDigitosCuadrados;
        }
    }
}
