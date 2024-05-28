package Espejo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertVendedores5 extends conexion {
    public void insertarVendedor(String nombre, String dir, String telefono, java.sql.Date fechaNac, String cc) {
        String sql = "INSERT INTO vendedores5 (nombre, dir, telefono, fecha_nac, cc) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = getConexion(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            pstmt.setString(2, dir);
            pstmt.setString(3, telefono);
            pstmt.setDate(4, fechaNac);
            pstmt.setString(5, cc);
            pstmt.executeUpdate();
            System.out.println("Vendedor insertado con éxito.");
        } catch (SQLException e) {
            System.err.println("Error al insertar el vendedor: " + e.getMessage());
        }
    }
}
