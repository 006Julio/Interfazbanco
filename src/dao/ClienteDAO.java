package dao;

import modelo.Cliente;
import util.ConexionMySQL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    // ✅ INSERTAR CLIENTE
    public boolean insertar(Cliente c) {
        String sql = """
            INSERT INTO clientes (nombres, aPaterno, aMaterno, dni, tipo_documento_id, genero_id)
            VALUES (?, ?, ?, ?, ?, ?)
        """;
        try (Connection con = ConexionMySQL.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, c.getNombres());
            ps.setString(2, c.getaPaterno());
            ps.setString(3, c.getaMaterno());
            ps.setString(4, c.getDni());
            ps.setInt(5, c.getTipoDocumentoId());
            ps.setInt(6, c.getGeneroId());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ✅ LISTAR CLIENTES
    public List<Cliente> listarClientes() {
        List<Cliente> lista = new ArrayList<>();
        String sql = """
            SELECT c.id, c.nombres, c.aPaterno, c.aMaterno, c.dni,
                   td.nombre AS tipo_documento, g.nombre AS genero
            FROM clientes c
            INNER JOIN tipo_documento td ON c.tipo_documento_id = td.id
            INNER JOIN genero g ON c.genero_id = g.id
        """;

        try (Connection con = ConexionMySQL.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Cliente cli = new Cliente();
                cli.setId(rs.getInt("id"));
                cli.setNombres(rs.getString("nombres"));
                cli.setaPaterno(rs.getString("aPaterno"));
                cli.setaMaterno(rs.getString("aMaterno"));
                cli.setDni(rs.getString("dni"));
                cli.setTipoDocumento(rs.getString("tipo_documento"));
                cli.setGenero(rs.getString("genero"));
                lista.add(cli);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // ✅ BUSCAR CLIENTE POR DNI
    public Cliente buscarPorDni(String dni) {
        Cliente cli = null;
        String sql = """
            SELECT c.id, c.nombres, c.aPaterno, c.aMaterno, c.dni,
                   td.nombre AS tipo_documento, g.nombre AS genero
            FROM clientes c
            INNER JOIN tipo_documento td ON c.tipo_documento_id = td.id
            INNER JOIN genero g ON c.genero_id = g.id
            WHERE c.dni = ?
        """;

        try (Connection con = ConexionMySQL.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, dni);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                cli = new Cliente();
                cli.setId(rs.getInt("id"));
                cli.setNombres(rs.getString("nombres"));
                cli.setaPaterno(rs.getString("aPaterno"));
                cli.setaMaterno(rs.getString("aMaterno"));
                cli.setDni(rs.getString("dni"));
                cli.setTipoDocumento(rs.getString("tipo_documento"));
                cli.setGenero(rs.getString("genero"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cli;
    }

    // ✅ ACTUALIZAR CLIENTE
    public boolean actualizar(Cliente c) {
        String sql = """
            UPDATE clientes
            SET nombres = ?, aPaterno = ?, aMaterno = ?, dni = ?, tipo_documento_id = ?, genero_id = ?
            WHERE id = ?
        """;
        try (Connection con = ConexionMySQL.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, c.getNombres());
            ps.setString(2, c.getaPaterno());
            ps.setString(3, c.getaMaterno());
            ps.setString(4, c.getDni());
            ps.setInt(5, c.getTipoDocumentoId());
            ps.setInt(6, c.getGeneroId());
            ps.setInt(7, c.getId());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ✅ ELIMINAR CLIENTE
    public boolean eliminar(int id) {
        String sql = "DELETE FROM clientes WHERE id = ?";
        try (Connection con = ConexionMySQL.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ✅ LISTAR CLIENTES PARA COMBOBOX
    public List<Cliente> listarComboClientes() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT id, nombres, aPaterno FROM clientes";
        try (Connection con = ConexionMySQL.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Cliente c = new Cliente();
                c.setId(rs.getInt("id"));
                c.setNombres(rs.getString("nombres"));
                c.setaPaterno(rs.getString("aPaterno"));
                lista.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
