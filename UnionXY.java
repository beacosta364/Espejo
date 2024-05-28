/*La unión de los elementos de la tabla X y la Tabla y sin repetir valores. */
package EspejoCursores;

import Espejo.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class UnionXY extends conexion {

    public void mostrarUnion() {
        String selectXSql = "SELECT DISTINCT valor FROM x";
        String selectYSql = "SELECT DISTINCT valor FROM y";

        try (Connection conn = getConexion();
             PreparedStatement selectXStmt = conn.prepareStatement(selectXSql);
             PreparedStatement selectYStmt = conn.prepareStatement(selectYSql);
             ResultSet rsX = selectXStmt.executeQuery();
             ResultSet rsY = selectYStmt.executeQuery()) {

            Set<Integer> valoresUnicos = new HashSet<>();

            while (rsX.next()) {
                valoresUnicos.add(rsX.getInt("valor"));
            }

            while (rsY.next()) {
                valoresUnicos.add(rsY.getInt("valor"));
            }

            System.out.println("Unión de elementos de tabla x y tabla y sin repetir valores:");

            for (int valor : valoresUnicos) {
                System.out.println("Valor: " + valor);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al mostrar la unión de elementos de las tablas x y y: " + e.getMessage(), e);
        }
    }

    public static void main(String[] args) {
        UnionXY union = new UnionXY();
        union.mostrarUnion();
    }
}
