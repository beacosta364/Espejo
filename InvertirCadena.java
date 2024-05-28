package EspejoProcedimientos;

import java.util.Scanner;

public class InvertirCadena {
    
    public static String invertirCadena(String inputString) {
        StringBuilder reversedString = new StringBuilder();
        for (int i = inputString.length() - 1; i >= 0; i--) {
            reversedString.append(inputString.charAt(i));
        }
        return reversedString.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese una cadena para invertir: ");
        System.out.println("recuerda que la cadena son varios numeros, este programa simplemente los invertira");
        String inputString = scanner.nextLine();
        scanner.close();

        String invertedString = invertirCadena(inputString);
        System.out.println("Cadena invertida: " + invertedString);
    }
}
