package dao;

import modelo.Cuenta;
import util.ConexionMySQL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CuentaDAO {

    // ✅ INSERTAR NUEVA CUENTA
    public boolean insertar(Cuenta c) {
        String sql = """
            INSERT INTO cuentas (numero_cuenta, cliente_id, tipo_cuenta_id, moneda_id, estado_id, saldo)
            VALUES (?, ?, ?, ?, ?, ?)
        """;
        try (Connection con = ConexionMySQL.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, c.getNumeroCuenta());
            ps.setInt(2, c.getClienteId());
            ps.setInt(3, c.getTipoCuentaId());
            ps.setInt(4, c.getMonedaId());
            ps.setInt(5, c.getEstadoId());
            ps.setDouble(6, c.getSaldo());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ✅ LISTAR TODAS LAS CUENTAS
    public List<Cuenta> listarCuentas() {
        List<Cuenta> lista = new ArrayList<>();
        String sql = """
            SELECT c.id, c.numero_cuenta, c.saldo,
                   CONCAT(cl.nombres, ' ', cl.aPaterno) AS cliente,
                   tc.nombre AS tipo_cuenta,
                   m.nombre AS moneda,
                   e.nombre AS estado
            FROM cuentas c
            INNER JOIN clientes cl ON c.cliente_id = cl.id
            INNER JOIN tipo_cuenta tc ON c.tipo_cuenta_id = tc.id
            INNER JOIN moneda m ON c.moneda_id = m.id
            INNER JOIN estado e ON c.estado_id = e.id
        """;

        try (Connection con = ConexionMySQL.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Cuenta cu = new Cuenta();
                cu.setId(rs.getInt("id"));
                cu.setNumeroCuenta(rs.getString("numero_cuenta"));
                cu.setSaldo(rs.getDouble("saldo"));
                cu.setClienteNombre(rs.getString("cliente"));
                cu.setTipoCuenta(rs.getString("tipo_cuenta"));
                cu.setMoneda(rs.getString("moneda"));
                cu.setEstado(rs.getString("estado"));
                lista.add(cu);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    // ✅ BUSCAR CUENTA POR NÚMERO
    public Cuenta buscarPorNumero(String numero) {
        Cuenta cu = null;
        String sql = """
            SELECT c.id, c.numero_cuenta, c.saldo,
                   CONCAT(cl.nombres, ' ', cl.aPaterno) AS cliente,
                   tc.nombre AS tipo_cuenta,
                   m.nombre AS moneda,
                   e.nombre AS estado
            FROM cuentas c
            INNER JOIN clientes cl ON c.cliente_id = cl.id
            INNER JOIN tipo_cuenta tc ON c.tipo_cuenta_id = tc.id
            INNER JOIN moneda m ON c.moneda_id = m.id
            INNER JOIN estado e ON c.estado_id = e.id
            WHERE c.numero_cuenta = ?
        """;

        try (Connection con = ConexionMySQL.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, numero);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                cu = new Cuenta();
                cu.setId(rs.getInt("id"));
                cu.setNumeroCuenta(rs.getString("numero_cuenta"));
                cu.setSaldo(rs.getDouble("saldo"));
                cu.setClienteNombre(rs.getString("cliente"));
                cu.setTipoCuenta(rs.getString("tipo_cuenta"));
                cu.setMoneda(rs.getString("moneda"));
                cu.setEstado(rs.getString("estado"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cu;
    }

    // ✅ ACTUALIZAR CUENTA
    public boolean actualizar(Cuenta c) {
        String sql = """
            UPDATE cuentas
            SET numero_cuenta = ?, cliente_id = ?, tipo_cuenta_id = ?, moneda_id = ?, estado_id = ?, saldo = ?
            WHERE id = ?
        """;
        try (Connection con = ConexionMySQL.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, c.getNumeroCuenta());
            ps.setInt(2, c.getClienteId());
            ps.setInt(3, c.getTipoCuentaId());
            ps.setInt(4, c.getMonedaId());
            ps.setInt(5, c.getEstadoId());
            ps.setDouble(6, c.getSaldo());
            ps.setInt(7, c.getId());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ✅ ELIMINAR CUENTA
    public boolean eliminar(int id) {
        String sql = "DELETE FROM cuentas WHERE id = ?";
        try (Connection con = ConexionMySQL.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
