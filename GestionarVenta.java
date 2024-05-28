package Espejo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GestionarVenta extends conexion {
    
    public void gestionarVenta(int codProducto, int cantidad, int codVendedor, int codCliente) {
        try {
            // Preparar la consulta SQL para obtener los datos del producto
            String sql = "SELECT stock_cantidad, vr_unidad FROM productos5 WHERE COD_PRODUCTO = ?";
            PreparedStatement statement = getConexion().prepareStatement(sql);
            statement.setInt(1, codProducto);
            
            // Ejecutar la consulta
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                // Si hay resultados, obtener la cantidad en stock y el valor por unidad del producto
                int stockCantidad = resultSet.getInt("stock_cantidad");
                double vrUnidad = resultSet.getDouble("vr_unidad");
                
                // Verificar si hay suficiente stock para la cantidad deseada
                if (stockCantidad >= cantidad) {
                    System.out.println("Producto disponible. Puede vender " + cantidad + " unidades.");

                    // Iniciar transacción
                    getConexion().setAutoCommit(false);

                    // Insertar venta y recuperar el código de venta generado
                    PreparedStatement insertVentaStatement = getConexion().prepareStatement(
                            "INSERT INTO venta5 (cod_vendedor, cod_cliente, total_neto, impuesto, total) VALUES (?, ?, 0, 0, 0)",
                            PreparedStatement.RETURN_GENERATED_KEYS);
                    insertVentaStatement.setInt(1, codVendedor);
                    insertVentaStatement.setInt(2, codCliente);
                    insertVentaStatement.executeUpdate();
                    
                    ResultSet generatedKeys = insertVentaStatement.getGeneratedKeys();
                    int codVenta = 0;
                    if (generatedKeys.next()) {
                        codVenta = generatedKeys.getInt(1);
                    }
                    
                    // Insertar detalle de venta
                    PreparedStatement insertDetalleStatement = getConexion().prepareStatement(
                            "INSERT INTO detalle_venta5 (cod_venta, cod_producto, cantidad, total_neto, total_impuesto, total, descuento) VALUES (?, ?, ?, ?, 0, ?, 0)");
                    insertDetalleStatement.setInt(1, codVenta);
                    insertDetalleStatement.setInt(2, codProducto);
                    insertDetalleStatement.setInt(3, cantidad);
                    double totalNeto = vrUnidad * cantidad;
                    insertDetalleStatement.setDouble(4, totalNeto);
                    insertDetalleStatement.setDouble(5, totalNeto);
                    insertDetalleStatement.executeUpdate();
                    
                    // Insertar Total en la tabla venta5
                    PreparedStatement updateVentaStatement = getConexion().prepareStatement(
                            "UPDATE venta5 SET total_neto = ?, total = ? WHERE cod_venta = ?");
                    updateVentaStatement.setDouble(1, totalNeto);
                    updateVentaStatement.setDouble(2, totalNeto);
                    updateVentaStatement.setInt(3, codVenta);
                    updateVentaStatement.executeUpdate();
                    
                    // Actualizar la tabla productos5 cuando se realiza la venta
                    PreparedStatement updateProductoStatement = getConexion().prepareStatement(
                            "UPDATE productos5 SET stock_cantidad = stock_cantidad - ? WHERE COD_PRODUCTO = ? AND STOCK_CANTIDAD >= ?");
                    updateProductoStatement.setInt(1, cantidad);
                    updateProductoStatement.setInt(2, codProducto);
                    updateProductoStatement.setInt(3, cantidad);
                    updateProductoStatement.executeUpdate();
                    
                    // Commit de la transacción
                    getConexion().commit();
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
            try {
                // Rollback en caso de error
                getConexion().rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            throw new RuntimeException("Error al ejecutar la consulta: " + e.getMessage(), e);
        }
    }
}
