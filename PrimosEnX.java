package EspejoCursores;

import Espejo.conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PrimosEnX extends conexion {

    public void verificarPrimosEnX() {
        String sql = "SELECT valor FROM x";
        
        try (Connection conn = getConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int valor = rs.getInt("valor");
                if (esPrimo(valor)) {
                    System.out.println("El número " + valor + " es primo.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al verificar números primos en la tabla x: " + e.getMessage(), e);
        }
    }

    public boolean esPrimo(int numero) {
        if (numero <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(numero); i++) {
            if (numero % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        PrimosEnX verificador = new PrimosEnX();
        verificador.verificarPrimosEnX();
    }
}
