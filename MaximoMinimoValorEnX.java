/*hallar el maximo y el minimo valor registrado en el campo valor, trata con un for */

package EspejoCursores;

import Espejo.conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MaximoMinimoValorEnX extends conexion {
    
    public void calcularMaximoMinimo() {
        String sql = "SELECT valor FROM x";
        Integer maxValor = null;
        Integer minValor = null;

        try (Connection conn = getConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int valor = rs.getInt("valor");

                if (maxValor == null || valor > maxValor) {
                    maxValor = valor;
                }

                if (minValor == null || valor < minValor) {
                    minValor = valor;
                }
            }

            System.out.println("El valor máximo es: " + (maxValor != null ? maxValor : "N/A"));
            System.out.println("El valor mínimo es: " + (minValor != null ? minValor : "N/A"));

        } catch (SQLException e) {
            throw new RuntimeException("Error al calcular valores máximo y mínimo: " + e.getMessage(), e);
        }
    }

    public static void main(String[] args) {
        MaximoMinimoValorEnX calculador = new MaximoMinimoValorEnX();
        calculador.calcularMaximoMinimo();
    }
}
