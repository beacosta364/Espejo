package EspejoProcedimientos;
import java.util.Scanner;

public class ContarVocalesConsonantes {
    
    public static void countVowelsConsonants(String inputString) {
        int vowelCount = 0;
        int consonantCount = 0;
        inputString = inputString.toUpperCase();
        for (int i = 0; i < inputString.length(); i++) {
            char character = inputString.charAt(i);
            if (character == 'A' || character == 'E' || character == 'I' || character == 'O' || character == 'U') {
                vowelCount++;
            } else if (character >= 'A' && character <= 'Z') {
                consonantCount++;
            }
        }
        System.out.println("Vocales: " + vowelCount + ", Consonantes: " + consonantCount);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese una cadena para contar vocales y consonantes: ");
        String inputString = scanner.nextLine();
        scanner.close();

        countVowelsConsonants(inputString);
    }
}
