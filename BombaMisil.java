

/*Si hay un numero en una posicion adecuada es una bomba si hay un numero en una posicion inadecuada es un micil*/
package Espejo2;

import java.util.Scanner;

public class BombaMisil {
    public static void main(String[] args) {
        // Declarar variables
        int numeroAdivinar1;
        int numeroAdivinar2;
        int intentosMaximos = 5;
        int intentosRealizados = 0;
        boolean adivinado = false;

        // Leer los números ingresados por el usuario
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el primer número a esconder (entre 0 y 9): ");
        numeroAdivinar1 = scanner.nextInt();
        System.out.print("Ingrese el segundo número a esconder (entre 0 y 9): ");
        numeroAdivinar2 = scanner.nextInt();

        // Juego: adivinar los números
        while (intentosRealizados < intentosMaximos && !adivinado) {
            System.out.println("Intento " + (intentosRealizados + 1) + " de " + intentosMaximos);
            System.out.print("Ingrese el primer número: ");
            int numeroAdivinado1 = scanner.nextInt();
            System.out.print("Ingrese el segundo número: ");
            int numeroAdivinado2 = scanner.nextInt();

            // Verificar si los números adivinados son correctos
            if (numeroAdivinado1 == numeroAdivinar1 && numeroAdivinado2 == numeroAdivinar2) {
                System.out.println("¡Felicitaciones! Has adivinado los números correctamente.");
                adivinado = true;
            } else {
                // Verificar cuántas bombas y misiles se han encontrado
                int bombas = 0;
                int misiles = 0;
                if (numeroAdivinado1 == numeroAdivinar1) {
                    bombas++;
                } else if (numeroAdivinado1 == numeroAdivinar2) {
                    misiles++;
                }
                if (numeroAdivinado2 == numeroAdivinar2) {
                    bombas++;
                } else if (numeroAdivinado2 == numeroAdivinar1) {
                    misiles++;
                }
                System.out.println("Resultado del intento:");
                System.out.println("Bombas: " + bombas);
                System.out.println("Misiles: " + misiles);
                intentosRealizados++;
            }
        }

        // Mostrar mensaje si el usuario no adivinó los números
        if (!adivinado) {
            System.out.println("Lo siento, has agotado tus intentos. Los números a adivinar eran: " + numeroAdivinar1 + " y " + numeroAdivinar2);
        }

        // Cerrar el scanner
        scanner.close();
    }
}
