package EspejoCursores;

import Espejo.conexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class InsertX {

    private String url = "jdbc:mysql://127.0.0.1:3306/espejo";
    private String user = "root";
    private String password = "beacosta";

    public void insertValor(int valor) {
        String sql = "INSERT INTO x (valor) VALUES (?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, valor);
            pstmt.executeUpdate();
            System.out.println("Valor insertado con éxito en la tabla x.");
        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar valor en la tabla x: " + e.getMessage(), e);
        }
    }

    public static void main(String[] args) {
        InsertX inserter = new InsertX();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Insertar valor en la tabla x");
            System.out.println("2. Salir");
            System.out.print("Opción: ");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.print("Ingrese el valor a insertar: ");
                    int valor = scanner.nextInt();
                    inserter.insertValor(valor);
                    break;
                case 2:
                    exit = true;
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
                    break;
            }
        }

        scanner.close();
    }
}
