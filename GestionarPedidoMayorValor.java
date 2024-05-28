package Espejo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GestionarPedidoMayorValor extends conexion {
    
    public void gestionarPedidoMayorValor() {
        try {
            Connection con = getConexion();
            
            // Obtener el producto con el mayor valor unitario
            int codigoProducto = 0;
            String nombreProducto = "";
            double valorUnitario = 0.0;
            int stockActual = 0;
            try (PreparedStatement ps = con.prepareStatement(
                    "SELECT p.cod_producto, p.nombre, p.vr_unidad, p.stock_cantidad " +
                    "FROM productos5 p " +
                    "INNER JOIN detalle_venta5 dv ON p.cod_producto = dv.cod_producto " +
                    "ORDER BY p.vr_unidad DESC " +
                    "LIMIT 1")) {
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        codigoProducto = rs.getInt("cod_producto");
                        nombreProducto = rs.getString("nombre");
                        valorUnitario = rs.getDouble("vr_unidad");
                        stockActual = rs.getInt("stock_cantidad");
                    }
                }
            }
            
            // Obtener la cantidad total vendida del producto
            int cantidadTotal = 0;
            try (PreparedStatement ps = con.prepareStatement(
                    "SELECT COALESCE(SUM(cantidad), 0) AS cantidad_total " +
                    "FROM detalle_venta5 " +
                    "WHERE cod_producto = ?")) {
                ps.setInt(1, codigoProducto);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        cantidadTotal = rs.getInt("cantidad_total");
                    }
                }
            }
            
            // Actualizar el stock según la cantidad vendida
            int nuevoStock = 0;
            if (cantidadTotal >= 0 && cantidadTotal <= 15) {
                nuevoStock = stockActual + 20;
            } else if (cantidadTotal >= 16 && cantidadTotal <= 25) {
                nuevoStock = (int) (stockActual + (stockActual * 0.5));
            } else {
                nuevoStock = stockActual * 2;
            }
            
            actualizarStock(con, codigoProducto, nuevoStock);
            
            // Mostrar resultados
            System.out.println("Se ha generado un pedido para el producto: " + nombreProducto + " (Código: " + codigoProducto + ")");
            System.out.println("Cantidad actual de stock: " + stockActual);
            System.out.println("Cantidad total vendida: " + cantidadTotal);
            System.out.println("Nuevo stock actualizado: " + nuevoStock);
            
        } catch (SQLException e) {
            throw new RuntimeException("Error al gestionar el pedido del producto de mayor valor: " + e.getMessage(), e);
        }
    }
    
    private void actualizarStock(Connection con, int codigoProducto, int nuevoStock) throws SQLException {
        String query = "UPDATE productos5 SET stock_cantidad = ? WHERE cod_producto = ?";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, nuevoStock);
            ps.setInt(2, codigoProducto);
            ps.executeUpdate();
        }
    }
}
