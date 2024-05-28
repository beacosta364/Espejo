package Espejo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexion {
    private Connection conexion;

    public conexion() {
        try {
            // Registrar el driver JDBC de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Error al cargar el driver JDBC: " + e.getMessage(), e);
        }
        conectar();
    }

    public void conectar() {
        try {
            conexion = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/espejo", "root", "beacosta");
            System.out.println("Conexión establecida con éxito.");
        } catch (SQLException e) {
            throw new RuntimeException("Error al establecer conexión: " + e.getMessage(), e);
        }
    }

    public Connection getConexion() {
        return conexion;
    }
}
