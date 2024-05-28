package Espejo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

public class VendedorForm extends JFrame {
    private JTextField nombreField;
    private JTextField dirField;
    private JTextField telefonoField;
    private JTextField fechaNacField;
    private JTextField ccField;
    private JButton insertarButton;

    public VendedorForm() {
        setTitle("Insertar Vendedor");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        nombreField = new JTextField();
        dirField = new JTextField();
        telefonoField = new JTextField();
        fechaNacField = new JTextField();
        ccField = new JTextField();
        insertarButton = new JButton("Insertar Vendedor");

        add(new JLabel("Nombre:"));
        add(nombreField);
        add(new JLabel("Dirección:"));
        add(dirField);
        add(new JLabel("Teléfono:"));
        add(telefonoField);
        add(new JLabel("Fecha de Nacimiento (YYYY-MM-DD):"));
        add(fechaNacField);
        add(new JLabel("Cédula:"));
        add(ccField);
        add(insertarButton);

        insertarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = nombreField.getText();
                String dir = dirField.getText();
                String telefono = telefonoField.getText();
                Date fechaNac = Date.valueOf(fechaNacField.getText());
                String cc = ccField.getText();

                InsertVendedores5 insertVendedores = new InsertVendedores5();
                insertVendedores.insertarVendedor(nombre, dir, telefono, fechaNac, cc);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new VendedorForm().setVisible(true);
            }
        });
    }
}
