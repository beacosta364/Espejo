package Espejo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertCliente5 extends conexion {
    public void insertarCliente(String nombre, String tipo, String direccion, String telefono, String idNit) {
        String sql = "INSERT INTO cliente5 (nombre, tipo, direccion, telefono, id_nit) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = getConexion(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            pstmt.setString(2, tipo);
            pstmt.setString(3, direccion);
            pstmt.setString(4, telefono);
            pstmt.setString(5, idNit);
            pstmt.executeUpdate();
            System.out.println("Cliente insertado con éxito.");
        } catch (SQLException e) {
            System.err.println("Error al insertar el cliente: " + e.getMessage());
        }
    }
}
