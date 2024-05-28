package Espejo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

class TresPorcientoMas extends conexion {
    public void actualizarPrecio(int codProducto, double porcentajeAumento) {
        String sql = "UPDATE productos5 SET vr_unidad = vr_unidad + vr_unidad * ? WHERE cod_producto = ?";

        try (Connection conn = getConexion(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, porcentajeAumento);
            pstmt.setInt(2, codProducto);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Precio actualizado con éxito.");
            } else {
                System.out.println("No se encontró ningún producto con el código especificado.");
            }
        } catch (SQLException e) {
            System.err.println("Error al actualizar el precio: " + e.getMessage());
        }
    }
}