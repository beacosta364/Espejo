package EspejoCursores;

import Espejo.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList; // Agregar esta importación
import java.util.List; // Agregar esta importación

public class ElementosComunesXY extends conexion {

    public void encontrarElementosComunes() {
        String selectXSql = "SELECT DISTINCT valor FROM x";
        String selectYSql = "SELECT DISTINCT valor FROM y";

        try (Connection conn = getConexion();
             PreparedStatement selectXStmt = conn.prepareStatement(selectXSql);
             PreparedStatement selectYStmt = conn.prepareStatement(selectYSql);
             ResultSet rsX = selectXStmt.executeQuery();
             ResultSet rsY = selectYStmt.executeQuery()) {

            System.out.println("Elementos comunes encontrados:");

            // Cargar valores de la tabla y en una lista
            List<Integer> valoresY = new ArrayList<>();
            while (rsY.next()) {
                valoresY.add(rsY.getInt("valor"));
            }

            // Iterar sobre los valores de la tabla x
            while (rsX.next()) {
                int valorX = rsX.getInt("valor");
                // Comparar con los valores de la lista
                if (valoresY.contains(valorX)) {
                    System.out.println("Valor: " + valorX);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al encontrar elementos comunes entre las tablas x y y: " + e.getMessage(), e);
        }
    }

    public static void main(String[] args) {
        ElementosComunesXY buscador = new ElementosComunesXY();
        buscador.encontrarElementosComunes();
    }
}
