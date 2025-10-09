package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionMySQL {
    private static final String URL = "jdbc:mysql://localhost:3306/banco_db?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "";

    public static Connection getConnection() {
        Connection con = null;
        try {
            // 🔹 Cargar el driver manualmente (esto ayuda en NetBeans)
            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("✅ Conectado a MySQL correctamente.");
        } catch (ClassNotFoundException e) {
            System.out.println("❌ No se encontró el driver JDBC: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("❌ Error al conectar a MySQL: " + e.getMessage());
        }
        return con;
    }
}
