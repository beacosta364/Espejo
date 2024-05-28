package Espejo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductoForm extends JFrame {
    private JTextField nombreField;
    private JTextField stockCantidadField;
    private JTextField cantidadField;
    private JTextField vrUnidadField;
    private JTextField porcentajeImpuestoField;
    private JButton insertarButton;

    public ProductoForm() {
        setTitle("Insertar Producto");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        nombreField = new JTextField();
        stockCantidadField = new JTextField();
        cantidadField = new JTextField();
        vrUnidadField = new JTextField();
        porcentajeImpuestoField = new JTextField();
        insertarButton = new JButton("Insertar Producto");

        add(new JLabel("Nombre:"));
        add(nombreField);
        add(new JLabel("Stock Cantidad:"));
        add(stockCantidadField);
        add(new JLabel("Cantidad:"));
        add(cantidadField);
        add(new JLabel("Valor Unidad:"));
        add(vrUnidadField);
        add(new JLabel("Porcentaje Impuesto:"));
        add(porcentajeImpuestoField);
        add(insertarButton);

        insertarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = nombreField.getText();
                int stockCantidad = Integer.parseInt(stockCantidadField.getText());
                int cantidad = Integer.parseInt(cantidadField.getText());
                double vrUnidad = Double.parseDouble(vrUnidadField.getText());
                double porcentajeImpuesto = Double.parseDouble(porcentajeImpuestoField.getText());

                Insertproductos5 insertproductos = new Insertproductos5();
                insertproductos.insertarProducto(nombre, stockCantidad, cantidad, vrUnidad, porcentajeImpuesto);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ProductoForm().setVisible(true);
            }
        });
    }
}
