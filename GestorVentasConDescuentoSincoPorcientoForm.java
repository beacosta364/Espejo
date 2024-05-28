package Espejo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GestorVentasConDescuentoSincoPorcientoForm extends JFrame {
    private JTextField codigoProductoField;
    private JTextField cantidadField;
    private JTextField codigoVendedorField;
    private JTextField codigoClienteField;
    private JButton gestionarVentaButton;

    public GestorVentasConDescuentoSincoPorcientoForm() {
        setTitle("Gestionar Venta con Descuento del 5%");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        codigoProductoField = new JTextField();
        cantidadField = new JTextField();
        codigoVendedorField = new JTextField();
        codigoClienteField = new JTextField();
        gestionarVentaButton = new JButton("Gestionar Venta");

        add(new JLabel("Código del Producto:"));
        add(codigoProductoField);
        add(new JLabel("Cantidad:"));
        add(cantidadField);
        add(new JLabel("Código del Vendedor:"));
        add(codigoVendedorField);
        add(new JLabel("Código del Cliente:"));
        add(codigoClienteField);
        add(gestionarVentaButton);

        gestionarVentaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int codProducto = Integer.parseInt(codigoProductoField.getText());
                int cantidad = Integer.parseInt(cantidadField.getText());
                int codVendedor = Integer.parseInt(codigoVendedorField.getText());
                int codCliente = Integer.parseInt(codigoClienteField.getText());

                try {
                    Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/espejo", "nuevo_usuario", "nueva_contraseña");
                    GestorVentasConDescuentoSincoPorciento gestor = new GestorVentasConDescuentoSincoPorciento(conexion);
                    gestor.gestionarVentaConDescuento(codProducto, cantidad, codVendedor, codCliente);
                    conexion.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GestorVentasConDescuentoSincoPorcientoForm().setVisible(true);
            }
        });
    }
}
