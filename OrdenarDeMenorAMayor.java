/*ordenar de menor a mayor por el camppo valor*/
package EspejoCursores;

import Espejo.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrdenarDeMenorAMayor extends conexion {

    public void ordenarDeMenorAMayor() {
        String sql = "SELECT * FROM x ORDER BY valor ASC";
        
        try (Connection conn = getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("Tabla x ordenada de menor a mayor por el campo valor:");
            System.out.println("ID\tValor");
            while (rs.next()) {
                int id = rs.getInt("id");
                int valor = rs.getInt("valor");
                System.out.println(id + "\t" + valor);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al ordenar la tabla x de menor a mayor: " + e.getMessage(), e);
        }
    }

    public static void main(String[] args) {
        OrdenarDeMenorAMayor ordenador = new OrdenarDeMenorAMayor();
        ordenador.ordenarDeMenorAMayor();
    }
}
