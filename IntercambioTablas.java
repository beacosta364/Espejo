/*El intercambio de los valores de la tabla_X y de la Tabla_Y conforme alcancen los registros para la tabla de menor longitud*/
package EspejoCursores;

import Espejo.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IntercambioTablas extends conexion {

    public void intercambiarRegistros() {
        try (Connection conn = getConexion()) {
            int cantidadRegistrosX = obtenerCantidadRegistros(conn, "x");
            int cantidadRegistrosY = obtenerCantidadRegistros(conn, "y");

            if (cantidadRegistrosX < cantidadRegistrosY) {
                transferirRegistros(conn, "y", "x", cantidadRegistrosX);
                transferirRegistros(conn, "x", "y", cantidadRegistrosX);
            } else if (cantidadRegistrosX > cantidadRegistrosY) {
                transferirRegistros(conn, "x", "y", cantidadRegistrosY);
                transferirRegistros(conn, "y", "x", cantidadRegistrosY);
            } else {
                System.out.println("Ambas tablas tienen la misma cantidad de registros.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al realizar el intercambio de registros: " + e.getMessage(), e);
        }
    }

    private void transferirRegistros(Connection conn, String tablaOrigen, String tablaDestino, int cantidadRegistros) throws SQLException {
        String selectSql = "SELECT valor FROM " + tablaOrigen + " LIMIT ?";
        String insertSql = "INSERT INTO " + tablaDestino + " (valor) VALUES (?)";
        String deleteSql = "DELETE FROM " + tablaOrigen + " WHERE valor = ?";

        try (PreparedStatement selectStmt = conn.prepareStatement(selectSql);
             PreparedStatement insertStmt = conn.prepareStatement(insertSql);
             PreparedStatement deleteStmt = conn.prepareStatement(deleteSql)) {

            // Seleccionar registros de la tabla de origen
            selectStmt.setInt(1, cantidadRegistros);
            try (ResultSet rs = selectStmt.executeQuery()) {
                while (rs.next()) {
                    int valor = rs.getInt("valor");
                    // Insertar en la tabla de destino
                    insertStmt.setInt(1, valor);
                    insertStmt.executeUpdate();
                    // Eliminar de la tabla de origen
                    deleteStmt.setInt(1, valor);
                    deleteStmt.executeUpdate();
                }
            }
        }
    }

    private int obtenerCantidadRegistros(Connection conn, String tabla) throws SQLException {
        String sql = "SELECT COUNT(*) AS count FROM " + tabla;
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("count");
            }
            return 0;
        }
    }

    public static void main(String[] args) {
        IntercambioTablas intercambio = new IntercambioTablas();
        intercambio.intercambiarRegistros();
    }
}
