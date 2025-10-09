package test;

import util.ConexionMySQL;
import java.sql.Connection;

public class TestConexion {
    public static void main(String[] args) {
        Connection con = ConexionMySQL.getConnection();
        if (con != null) {
            System.out.println("Conexión exitosa 😎");
        }
    }
}
