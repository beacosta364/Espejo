package Espejo;

import javax.swing.*;

public class ActualizarFacturaForm {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Crear el cuadro de diálogo para introducir el código de la factura
            String codigoFacturaStr = JOptionPane.showInputDialog(null, "Ingrese el código de la factura a actualizar:", "Actualizar Factura", JOptionPane.QUESTION_MESSAGE);
            
            if (codigoFacturaStr != null) {
                try {
                    // Convertir el código de la factura a entero
                    int codigoFactura = Integer.parseInt(codigoFacturaStr);
                    
                    // Llamar al método para actualizar la factura
                    ActualizarFactura.conectar();
                    ActualizarFactura.actualizarFactura(codigoFactura);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "El código de la factura debe ser un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Error al actualizar la factura: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
