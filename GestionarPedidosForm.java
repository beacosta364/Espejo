package Espejo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GestionarPedidosForm extends JFrame {
    private JTextField codigoProductoField;
    private JButton gestionarPedidosButton;

    public GestionarPedidosForm() {
        setTitle("Gestionar Pedidos");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        codigoProductoField = new JTextField();
        gestionarPedidosButton = new JButton("Gestionar Pedidos");

        add(new JLabel("Código del Producto:"));
        add(codigoProductoField);
        add(gestionarPedidosButton);

        gestionarPedidosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int codigoProducto = Integer.parseInt(codigoProductoField.getText());

                GestionarPedidos gestionarPedidos = new GestionarPedidos();
                gestionarPedidos.gestionarPedidos(codigoProducto);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GestionarPedidosForm().setVisible(true);
            }
        });
    }
}
