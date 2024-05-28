package EspejoProcedimientos;

/*public class IntercambiarValores {
    
    public static void exchangeValues(int[] numbers) {
        int temp = numbers[0];
        numbers[0] = numbers[1];
        numbers[1] = temp;
    }

    public static void main(String[] args) {
        int a = 10;
        int b = 20;
        
        System.out.println("Antes del intercambio - a: " + a + ", b: " + b);
        
        int[] values = {a, b};
        exchangeValues(values);
        
        a = values[0];
        b = values[1];
        
        System.out.println("Después del intercambio - a: " + a + ", b: " + b);
    }
}
*/
import java.util.Scanner;

public class IntercambiarValores {
    
    public static void exchangeValues(int[] numbers) {
        int temp = numbers[0];
        numbers[0] = numbers[1];
        numbers[1] = temp;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Ingrese el primer número: ");
        int a = scanner.nextInt();
        
        System.out.print("Ingrese el segundo número: ");
        int b = scanner.nextInt();
        
        System.out.println("Antes del intercambio - a: " + a + ", b: " + b);
        
        int[] values = {a, b};
        exchangeValues(values);
        
        a = values[0];
        b = values[1];
        
        System.out.println("Después del intercambio - a: " + a + ", b: " + b);
        
        scanner.close();
    }
}
