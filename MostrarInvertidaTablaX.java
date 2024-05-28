
/*3. mostrar en pantalla invertida la tabla */
package EspejoCursores;

import Espejo.conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MostrarInvertidaTablaX extends conexion {

    public void mostrarInvertidaTablaX() {
        String sql = "SELECT * FROM x ORDER BY id DESC";
        
        try (Connection conn = getConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                int valor = rs.getInt("valor");
                System.out.println("ID: " + id + ", Valor: " + valor);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al mostrar la tabla x en orden inverso: " + e.getMessage(), e);
        }
    }

    public static void main(String[] args) {
        MostrarInvertidaTablaX mostrador = new MostrarInvertidaTablaX();
        mostrador.mostrarInvertidaTablaX();
    }
}
