package Espejo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GestorVentasConDescuentoSincoPorciento {
    private Connection conexion;

    public GestorVentasConDescuentoSincoPorciento(Connection conexion) {
        this.conexion = conexion;
    }

    public void gestionarVentaConDescuento(int codProducto, int cantidad, int codVendedor, int codCliente) {
        try {
            if (!existeEnTabla("productos5", "cod_producto", codProducto)) {
                System.out.println("El producto con código " + codProducto + " no existe.");
                return;
            }
            if (!existeEnTabla("vendedores5", "cod_vendedor", codVendedor)) {
                System.out.println("El vendedor con código " + codVendedor + " no existe.");
                return;
            }
            if (!existeEnTabla("cliente5", "cod_cliente", codCliente)) {
                System.out.println("El cliente con código " + codCliente + " no existe.");
                return;
            }

            PreparedStatement stmt = conexion.prepareStatement("SELECT * FROM productos5 WHERE COD_PRODUCTO = ?");
            stmt.setInt(1, codProducto);
            ResultSet rs = stmt.executeQuery();

            if (!rs.next()) {
                System.out.println("El producto con código " + codProducto + " no existe.");
                return;
            }

            int stockCantidad = rs.getInt("stock_cantidad");
            double vrUnidad = rs.getDouble("vr_unidad");

            PreparedStatement totalVentasStmt = conexion.prepareStatement("SELECT COUNT(*) FROM venta5 WHERE cod_cliente = ?");
            totalVentasStmt.setInt(1, codCliente);
            ResultSet totalVentasRs = totalVentasStmt.executeQuery();
            totalVentasRs.next();
            int totalVentas = totalVentasRs.getInt(1);

            double descuento = 0.0;
            if (totalVentas > 5) {
                descuento = 0.05;
            }

            vrUnidad *= (1 - descuento);
            double total = vrUnidad * cantidad;

            if (stockCantidad >= cantidad) {
                PreparedStatement ventaStmt = conexion.prepareStatement("INSERT INTO venta5 (cod_vendedor, cod_cliente, total_neto, impuesto, total) VALUES (?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
                ventaStmt.setInt(1, codVendedor);
                ventaStmt.setInt(2, codCliente);
                ventaStmt.setDouble(3, 0);
                ventaStmt.setDouble(4, 0);
                ventaStmt.setDouble(5, total);
                ventaStmt.executeUpdate();

                ResultSet generatedKeys = ventaStmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int codVenta = generatedKeys.getInt(1);

                    PreparedStatement detalleStmt = conexion.prepareStatement("INSERT INTO detalle_venta5 (cod_venta, cod_producto, cantidad, total_neto, total_impuesto, total, descuento) VALUES (?, ?, ?, ?, ?, ?, ?)");
                    detalleStmt.setInt(1, codVenta);
                    detalleStmt.setInt(2, codProducto);
                    detalleStmt.setInt(3, cantidad);
                    detalleStmt.setDouble(4, vrUnidad * cantidad);
                    detalleStmt.setDouble(5, 0);
                    detalleStmt.setDouble(6, total);
                    detalleStmt.setDouble(7, descuento);
                    detalleStmt.executeUpdate();

                    PreparedStatement actualizarProductoStmt = conexion.prepareStatement("UPDATE productos5 SET stock_cantidad = stock_cantidad - ? WHERE cod_producto = ?");
                    actualizarProductoStmt.setInt(1, cantidad);
                    actualizarProductoStmt.setInt(2, codProducto);
                    actualizarProductoStmt.executeUpdate();

                    System.out.println("Venta gestionada con éxito. Descuento aplicado: " + (descuento * 100) + "%.");
                }
            } else {
                System.out.println("No hay suficientes existencias para vender " + cantidad + " unidades.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean existeEnTabla(String tabla, String columna, int valor) {
        try {
            PreparedStatement stmt = conexion.prepareStatement("SELECT 1 FROM " + tabla + " WHERE " + columna + " = ?");
            stmt.setInt(1, valor);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
