package EspejoProcedimientos;
/*import java.util.ArrayList;

public class CalcularFactorial {
    
    public static int factorial(int num) {
        int result = 1;
        if (num == 0) {
            return 1;
        }
        for (int i = 1; i <= num; i++) {
            result *= i;
        }
        return result;
    }

    public static void main(String[] args) {
        ArrayList<Integer> numerosAProbar = new ArrayList<>();
        numerosAProbar.add(0);
        numerosAProbar.add(1);
        numerosAProbar.add(2);
        numerosAProbar.add(3);
        numerosAProbar.add(4);
        numerosAProbar.add(5);

        for (int num : numerosAProbar) {
            int factorialResultado = factorial(num);
            System.out.println("El factorial de " + num + " es: " + factorialResultado);
        }
    }
}*/
import java.util.Scanner;


public class CalcularFactorial {
    
    public static int factorial(int num) {
        int result = 1;
        if (num == 0) {
            return 1;
        }
        for (int i = 1; i <= num; i++) {
            result *= i;
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num;
        while (true) {
            System.out.print("Ingrese un número para calcular su factorial (o ingrese -1 para salir): ");
            num = scanner.nextInt();
            if (num == -1) {
                break;
            }
            int factorialResultado = factorial(num);
            System.out.println("El factorial de " + num + " es: " + factorialResultado);
        }
        scanner.close();
    }
}
