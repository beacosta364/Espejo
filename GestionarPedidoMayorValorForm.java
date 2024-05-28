package Espejo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GestionarPedidoMayorValorForm extends javax.swing.JFrame {

    public GestionarPedidoMayorValorForm() {
        initComponents();
    }

    private void initComponents() {

        btnGestionarPedido = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gestionar Pedido Mayor Valor");

        btnGestionarPedido.setText("Gestionar Pedido");
        btnGestionarPedido.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnGestionarPedidoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(130, 130, 130)
                                .addComponent(btnGestionarPedido)
                                .addContainerGap(137, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(89, 89, 89)
                                .addComponent(btnGestionarPedido)
                                .addContainerGap(103, Short.MAX_VALUE))
        );

        pack();
    }

    private void btnGestionarPedidoActionPerformed(java.awt.event.ActionEvent evt) {
        GestionarPedidoMayorValor gestionarPedido = new GestionarPedidoMayorValor();
        gestionarPedido.gestionarPedidoMayorValor();
        JOptionPane.showMessageDialog(null, "Pedido gestionado correctamente.");
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GestionarPedidoMayorValorForm().setVisible(true);
            }
        });
    }

    private javax.swing.JButton btnGestionarPedido;
}
