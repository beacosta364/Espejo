package Espejo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GestionarPedidos extends conexion {
    
    public void gestionarPedidos(int codigoProducto) {
        try {
            Connection con = getConexion();
            
            // Obtener la cantidad vendida del producto
            int cantidadVendida = getCantidadVendida(con, codigoProducto);
            
            // Obtener el stock actual del producto
            int stockActual = getStockActual(con, codigoProducto);
            
            // Aplicar el pedido
            int stockNuevo = 0;
            if (cantidadVendida >= 0 && cantidadVendida <= 15) {
                stockNuevo = stockActual + 20;
            } else if (cantidadVendida >= 16 && cantidadVendida <= 25) {
                stockNuevo = (int) (stockActual + (stockActual * 0.5));
            } else {
                stockNuevo = stockActual * 2;
            }
            
            // Actualizar el stock del producto en la base de datos
            actualizarStock(con, codigoProducto, stockNuevo);
            
            System.out.println("Pedido gestionado con éxito.");
            
        } catch (SQLException e) {
            throw new RuntimeException("Error al gestionar los pedidos: " + e.getMessage(), e);
        }
    }
    
    private int getCantidadVendida(Connection con, int codigoProducto) throws SQLException {
        String query = "SELECT COALESCE(SUM(cantidad), 0) AS cantidad_vendida FROM detalle_venta5 WHERE cod_producto = ?";
        
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, codigoProducto);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("cantidad_vendida");
                }
            }
        }
        
        return 0;
    }
    
    private int getStockActual(Connection con, int codigoProducto) throws SQLException {
        String query = "SELECT stock_cantidad FROM productos5 WHERE cod_producto = ?";
        
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, codigoProducto);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("stock_cantidad");
                }
            }
        }
        
        return 0;
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

