package EspejoCursores;

import Espejo.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InvertirTablaX extends conexion {

    public void invertirTablaX() {
        String deleteSql = "DELETE FROM x";
        String selectSql = "SELECT id, valor FROM x ORDER BY id DESC";
        String insertSql = "INSERT INTO x (valor) VALUES (?)";
        
        try (Connection conn = getConexion();
             PreparedStatement deleteStmt = conn.prepareStatement(deleteSql);
             PreparedStatement selectStmt = conn.prepareStatement(selectSql);
             ResultSet rs = selectStmt.executeQuery();
             PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {

            // Borra los datos existentes en la tabla
            deleteStmt.executeUpdate();

            // Itera sobre los resultados en orden inverso e inserta los datos invertidos
            while (rs.next()) {
                int valor = rs.getInt("valor");
                insertStmt.setInt(1, valor);
                insertStmt.executeUpdate();
            }
            
            System.out.println("Tabla x invertida y datos anteriores borrados con éxito.");

        } catch (SQLException e) {
            throw new RuntimeException("Error al invertir la tabla x: " + e.getMessage(), e);
        }
    }

    public static void main(String[] args) {
        InvertirTablaX inversor = new InvertirTablaX();
        inversor.invertirTablaX();
    }
}
