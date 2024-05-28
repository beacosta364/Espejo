
package EspejoProcedimientos;
/*public class NumerosPrimos {
    
    public static boolean esPrimo(int numero) {
        if (numero <= 1) {
            return false;
        }
        for (int divisor = 2; divisor < numero; divisor++) {
            if (numero % divisor == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] numerosAProbar = {2, 3, 4, 5, 6, 7, 8, 9, 10};
        
        for (int numero : numerosAProbar) {
            if (esPrimo(numero)) {
                System.out.println(numero + " es primo.");
            } else {
                System.out.println(numero + " no es primo.");
            }
        }
    }
}*/

import java.util.Scanner;

public class NumerosPrimos {
    
    public static boolean esPrimo(int numero) {
        if (numero <= 1) {
            return false;
        }
        for (int divisor = 2; divisor < numero; divisor++) {
            if (numero % divisor == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese los números a verificar (ingrese -1 para terminar):");
        
        int numero;
        while (true) {
            System.out.print("Número: ");
            numero = scanner.nextInt();
            if (numero == -1) {
                break;
            }
            if (esPrimo(numero)) {
                System.out.println(numero + " es primo.");
            } else {
                System.out.println(numero + " no es primo.");
            }
        }
        
        scanner.close();
    }
}

