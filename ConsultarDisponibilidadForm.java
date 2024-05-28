package Espejo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConsultarDisponibilidadForm {
    private JFrame frame;
    private JTextField codigoProductoField;
    private JTextField cantidadField;
    private JButton consultarButton;

    public ConsultarDisponibilidadForm() {
        frame = new JFrame("Consultar Disponibilidad");
        frame.setSize(300, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        codigoProductoField = new JTextField();
        cantidadField = new JTextField();
        consultarButton = new JButton("Consultar Disponibilidad");

        frame.add(new JLabel("Código del Producto:"));
        frame.add(codigoProductoField);
        frame.add(new JLabel("Cantidad:"));
        frame.add(cantidadField);
        frame.add(consultarButton);

        consultarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int codProducto = Integer.parseInt(codigoProductoField.getText());
                int cantidad = Integer.parseInt(cantidadField.getText());

                ConsultarDisponibilidad consulta = new ConsultarDisponibilidad();
                consulta.consultarDisponibilidad(codProducto, cantidad);
            }
        });
    }

    public void setVisible(boolean visible) {
        frame.setVisible(visible);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ConsultarDisponibilidadForm form = new ConsultarDisponibilidadForm();
                form.setVisible(true);
            }
        });
    }
}
