package Espejo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConsultarDisponibilidad extends conexion {
    
    public void consultarDisponibilidad(int codProducto, int cantidad) {
        try {
            // Preparar la consulta SQL para obtener los datos del producto
            String sql = "SELECT stock_cantidad FROM productos5 WHERE COD_PRODUCTO = ?";
            PreparedStatement statement = getConexion().prepareStatement(sql);
            statement.setInt(1, codProducto);
            
            // Ejecutar la consulta
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                // Si hay resultados, obtener la cantidad en stock del producto
                int stockCantidad = resultSet.getInt("stock_cantidad");
                
                // Verificar si hay suficiente stock para la cantidad deseada
                if (stockCantidad >= cantidad) {
                    System.out.println("Producto disponible. Puede vender " + cantidad + " unidades.");
                } else {
                    System.out.println("No hay suficientes existencias para vender " + cantidad + " unidades.");
                }
            } else {
                // Si no hay resultados, el producto no existe
                System.out.println("El producto con código " + codProducto + " no existe.");
            }
            
            // Cerrar los recursos
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Error al ejecutar la consulta: " + e.getMessage(), e);
        }
    }
}
