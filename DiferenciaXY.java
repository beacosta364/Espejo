package EspejoCursores;

import Espejo.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class DiferenciaXY extends conexion {

    public void encontrarDiferencia() {
        String selectXSql = "SELECT DISTINCT valor FROM x";
        String selectYSql = "SELECT DISTINCT valor FROM y";

        try (Connection conn = getConexion();
             PreparedStatement selectXStmt = conn.prepareStatement(selectXSql);
             PreparedStatement selectYStmt = conn.prepareStatement(selectYSql);
             ResultSet rsX = selectXStmt.executeQuery();
             ResultSet rsY = selectYStmt.executeQuery()) {

            System.out.println("Diferencia entre tabla x y tabla y:");

            Set<Integer> valoresX = new HashSet<>();
            Set<Integer> valoresY = new HashSet<>();
            
            while (rsX.next()) {
                valoresX.add(rsX.getInt("valor"));
            }
            
            while (rsY.next()) {
                valoresY.add(rsY.getInt("valor"));
            }

            for (int valor : valoresX) {
                if (!valoresY.contains(valor)) {
                    System.out.println("Valor en tabla x pero no en tabla y: " + valor);
                }
            }
            
            for (int valor : valoresY) {
                if (!valoresX.contains(valor)) {
                    System.out.println("Valor en tabla y pero no en tabla x: " + valor);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al encontrar diferencia entre las tablas x y y: " + e.getMessage(), e);
        }
    }

    public static void main(String[] args) {
        DiferenciaXY buscador = new DiferenciaXY();
        buscador.encontrarDiferencia();
    }
}
