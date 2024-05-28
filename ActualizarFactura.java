package Espejo;

import java.sql.*;

public class ActualizarFactura {
    private static Connection conexion;

    public static void main(String[] args) {
        try {
            conectar();
            int codigoFactura = 1;  // Código de la factura a corregir y actualizar
            actualizarFactura(codigoFactura);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conexion != null) {
                try {
                    conexion.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void conectar() {
        try {
            // Registrar el driver JDBC de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/espejo", "root", "beacosta");
            System.out.println("Conexión establecida con éxito.");
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Error al establecer conexión: " + e.getMessage(), e);
        }
    }

    public static void actualizarFactura(int codigoFactura) {
        String selectDetalleVentaSQL = "SELECT * FROM detalle_venta5 WHERE COD_VENTA = ?";
        String selectProductoSQL = "SELECT PORCENTAJE_IMPUESTO, VR_UNIDAD FROM productos5 WHERE COD_PRODUCTO = ?";
        String updateDetalleVentaSQL = "UPDATE detalle_venta5 SET TOTAL_IMPUESTO = ?, TOTAL = ? WHERE COD_VENTA = ? AND COD_PRODUCTO = ?";
        String updateVentaSQL = "UPDATE venta5 SET TOTAL_NETO = ?, IMPUESTO = ?, TOTAL = ? WHERE COD_VENTA = ?";
        String sumDetalleVentaSQL = "SELECT SUM(TOTAL_NETO), SUM(TOTAL_IMPUESTO), SUM(TOTAL) FROM detalle_venta5 WHERE COD_VENTA = ?";

        try (PreparedStatement selectDetalleVentaStmt = conexion.prepareStatement(selectDetalleVentaSQL);
             PreparedStatement selectProductoStmt = conexion.prepareStatement(selectProductoSQL);
             PreparedStatement updateDetalleVentaStmt = conexion.prepareStatement(updateDetalleVentaSQL);
             PreparedStatement sumDetalleVentaStmt = conexion.prepareStatement(sumDetalleVentaSQL);
             PreparedStatement updateVentaStmt = conexion.prepareStatement(updateVentaSQL)) {

            // Comienza la transacción
            conexion.setAutoCommit(false);

            // Obtener los detalles de la factura
            selectDetalleVentaStmt.setInt(1, codigoFactura);
            try (ResultSet rsDetalleVenta = selectDetalleVentaStmt.executeQuery()) {
                while (rsDetalleVenta.next()) {
                    int codProducto = rsDetalleVenta.getInt("COD_PRODUCTO");
                    int cantidad = rsDetalleVenta.getInt("CANTIDAD");
                    double totalNeto = rsDetalleVenta.getDouble("TOTAL_NETO");

                    // Obtener el porcentaje de impuesto y el precio unitario del producto
                    selectProductoStmt.setInt(1, codProducto);
                    try (ResultSet rsProducto = selectProductoStmt.executeQuery()) {
                        if (rsProducto.next()) {
                            double porcentajeImpuesto = rsProducto.getDouble("PORCENTAJE_IMPUESTO");
                            double precioUnitario = rsProducto.getDouble("VR_UNIDAD");

                            // Calcular el total de impuesto y el total de la factura
                            double totalImpuesto = (precioUnitario * porcentajeImpuesto / 100) * cantidad;
                            double total = totalNeto + totalImpuesto;

                            // Asegurarse de que los valores no excedan los límites
                            if (totalImpuesto > Double.MAX_VALUE || totalImpuesto < -Double.MAX_VALUE) {
                                throw new SQLException("El valor calculado para total_impuesto está fuera de rango: " + totalImpuesto);
                            }

                            if (total > Double.MAX_VALUE || total < -Double.MAX_VALUE) {
                                throw new SQLException("El valor calculado para total está fuera de rango: " + total);
                            }

                            // Actualizar los valores en la tabla detalle_venta5
                            updateDetalleVentaStmt.setDouble(1, totalImpuesto);
                            updateDetalleVentaStmt.setDouble(2, total);
                            updateDetalleVentaStmt.setInt(3, codigoFactura);
                            updateDetalleVentaStmt.setInt(4, codProducto);
                            updateDetalleVentaStmt.executeUpdate();

                            System.out.println("Se ha corregido el impuesto y el total para el producto con código " + codProducto + " en la factura " + codigoFactura);
                        }
                    }
                }
            }

            // Confirmar la corrección de la factura
            conexion.commit();

            // Obtener los valores de TOTAL_NETO, TOTAL_IMPUESTO y TOTAL de la tabla detalle_venta5 para la misma factura
            sumDetalleVentaStmt.setInt(1, codigoFactura);
            try (ResultSet rsSum = sumDetalleVentaStmt.executeQuery()) {
                if (rsSum.next()) {
                    double totalNeto = rsSum.getDouble(1);
                    double totalImpuesto = rsSum.getDouble(2);
                    double total = rsSum.getDouble(3);

                    // Actualizar los valores en la tabla venta5
                    updateVentaStmt.setDouble(1, totalNeto);
                    updateVentaStmt.setDouble(2, totalImpuesto);
                    updateVentaStmt.setDouble(3, total);
                    updateVentaStmt.setInt(4, codigoFactura);
                    updateVentaStmt.executeUpdate();

                    System.out.println("Se han actualizado los campos TOTAL_NETO, IMPUESTO y TOTAL para la factura (venta) con código " + codigoFactura);
                }
            }

            // Confirmar la actualización
            conexion.commit();

        } catch (SQLException e) {
            try {
                conexion.rollback();
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            throw new RuntimeException("Error durante la actualización de la factura: " + e.getMessage(), e);
        } finally {
            try {
                // Restaurar el modo de autocommit
                conexion.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
