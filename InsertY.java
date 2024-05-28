package EspejoCursores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class InsertY {
    
    private String url = "jdbc:mysql://127.0.0.1:3306/espejo";
    private String user = "root";
    private String password = "beacosta";

    public void insertValorEnY(int valor) {
        String sql = "INSERT INTO y (valor) VALUES (?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, valor);
            pstmt.executeUpdate();
            System.out.println("Valor insertado con éxito en la tabla y.");
        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar valor en la tabla y: " + e.getMessage(), e);
        }
    }

    public static void main(String[] args) {
        InsertY inserter = new InsertY();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Insertar valor en la tabla y");
            System.out.println("2. Salir");
            System.out.print("Opción: ");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.print("Ingrese el valor a insertar en la tabla y: ");
                    int valorY = scanner.nextInt();
                    inserter.insertValorEnY(valorY);
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
