package EspejoProcedimientos;

/*import java.util.ArrayList;

public class CalcularPromedio {
    
    public static double calcularPromedio(ArrayList<Integer> numbers) {
        double total = 0;
        for (int number : numbers) {
            total += number;
        }
        return total / numbers.size();
    }

    public static void main(String[] args) {
        // Definir una lista de números para probar la función
        ArrayList<Integer> listaNumeros = new ArrayList<>();
        listaNumeros.add(10);
        listaNumeros.add(20);
        listaNumeros.add(30);
        listaNumeros.add(40);
        listaNumeros.add(50);

        // Llamar a la función y almacenar el resultado en la variable promedio
        double promedio = calcularPromedio(listaNumeros);

        // Imprimir el resultado
        System.out.println("El promedio de la lista de números es: " + promedio);
    }
}*/

import java.util.ArrayList;
import java.util.Scanner;

public class CalcularPromedio {
    
    public static double calcularPromedio(ArrayList<Integer> numbers) {
        double total = 0;
        for (int number : numbers) {
            total += number;
        }
        return total / numbers.size();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese los números de los cuales desea calcular el promedio (Ingrese -1 para terminar):");

        ArrayList<Integer> listaNumeros = new ArrayList<>();
        int numero;
        while (true) {
            System.out.print("Número: ");
            numero = scanner.nextInt();
            if (numero == -1) {
                break;
            }
            listaNumeros.add(numero);
        }
        
        scanner.close();

        if (listaNumeros.isEmpty()) {
            System.out.println("No se han ingresado números.");
        } else {
            double promedio = calcularPromedio(listaNumeros);
            System.out.println("El promedio de los números ingresados es: " + promedio);
        }
    }
}
