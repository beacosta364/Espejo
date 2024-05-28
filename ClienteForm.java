package Espejo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClienteForm extends JFrame {
    private JTextField nombreField;
    private JTextField tipoField;
    private JTextField direccionField;
    private JTextField telefonoField;
    private JTextField idNitField;
    private JButton insertarButton;

    public ClienteForm() {
        setTitle("Insertar Cliente");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        nombreField = new JTextField();
        tipoField = new JTextField();
        direccionField = new JTextField();
        telefonoField = new JTextField();
        idNitField = new JTextField();
        insertarButton = new JButton("Insertar Cliente");

        add(new JLabel("Nombre:"));
        add(nombreField);
        add(new JLabel("Tipo:"));
        add(tipoField);
        add(new JLabel("Dirección:"));
        add(direccionField);
        add(new JLabel("Teléfono:"));
        add(telefonoField);
        add(new JLabel("ID/NIT:"));
        add(idNitField);
        add(insertarButton);

        insertarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = nombreField.getText();
                String tipo = tipoField.getText();
                String direccion = direccionField.getText();
                String telefono = telefonoField.getText();
                String idNit = idNitField.getText();

                InsertCliente5 insertCliente = new InsertCliente5();
                insertCliente.insertarCliente(nombre, tipo, direccion, telefono, idNit);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ClienteForm().setVisible(true);
            }
        });
    }
}
