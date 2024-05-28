package Espejo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Insertproductos5 extends conexion {
    public void insertarProducto(String nombre, int stockCantidad, int cantidad, double vrUnidad, double porcentajeImpuesto) {
        String sql = "INSERT INTO productos5 (nombre, stock_cantidad, cantidad, vr_unidad, porcentaje_impuesto) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = getConexion(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            pstmt.setInt(2, stockCantidad);
            pstmt.setInt(3, cantidad);
            pstmt.setDouble(4, vrUnidad);
            pstmt.setDouble(5, porcentajeImpuesto);
            pstmt.executeUpdate();
            System.out.println("Producto insertado con éxito.");
        } catch (SQLException e) {
            System.err.println("Error al insertar el producto: " + e.getMessage());
        }
    }
}
