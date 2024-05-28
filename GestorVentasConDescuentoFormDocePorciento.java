package Espejo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GestorVentasConDescuentoFormDocePorciento extends JFrame {
    private JTextField codigoProductoField;
    private JTextField cantidadField;
    private JTextField codigoVendedorField;
    private JTextField codigoClienteField;
    private JButton gestionarVentaButton;

    public GestorVentasConDescuentoFormDocePorciento() {
        setTitle("Gestionar Venta con Descuento");
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

                conexion dbConexion = new conexion();
                GestorVentasConDescuento gestorVentas = new GestorVentasConDescuento(dbConexion.getConexion());
                gestorVentas.gestionarVentaConDescuento(codProducto, cantidad, codVendedor, codCliente);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GestorVentasConDescuentoFormDocePorciento().setVisible(true);
            }
        });
    }
}
