package Espejo;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TresPorcientoMasForm extends JFrame {
    private JTextField codigoProductoField;
    private JButton actualizarButton;

    public TresPorcientoMasForm() {
        setTitle("Actualizar Precio del Producto");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        codigoProductoField = new JTextField();
        actualizarButton = new JButton("Actualizar Precio");

        add(new JLabel("Código del Producto:"));
        add(codigoProductoField);
        add(actualizarButton);

        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int codProducto = Integer.parseInt(codigoProductoField.getText());

                TresPorcientoMas updateProductos = new TresPorcientoMas();
                // Aumento del 3%
                double porcentajeAumento = 0.03;

                updateProductos.actualizarPrecio(codProducto, porcentajeAumento);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TresPorcientoMasForm().setVisible(true);
            }
        });
    }
}