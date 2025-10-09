package dao;

import modelo.Cliente;
import util.ConexionMySQL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    // ✅ 1️⃣ INSERTAR NUEVO CLIENTE
    public boolean insertar(Cliente c) {
        String sql = """
            INSERT INTO clientes (nombres, a_paterno, a_materno, doi_numero, doi_tipo_id, genero_id)
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

    // ✅ 2️⃣ LISTAR CLIENTES
    public List<Cliente> listarClientes() {
        List<Cliente> lista = new ArrayList<>();
        String sql = """
            SELECT c.cliente_id, c.nombres, c.a_paterno, c.a_materno, c.doi_numero,
                   d.descripcion AS tipo_documento, g.descripcion AS genero
            FROM clientes c
            INNER JOIN doi_tipos d ON c.doi_tipo_id = d.doi_tipo_id
            INNER JOIN generos g ON c.genero_id = g.genero_id;
        """;

        try (Connection con = ConexionMySQL.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Cliente cli = new Cliente();
                cli.setId(rs.getInt("cliente_id"));
                cli.setNombres(rs.getString("nombres"));
                cli.setaPaterno(rs.getString("a_paterno"));
                cli.setaMaterno(rs.getString("a_materno"));
                cli.setDni(rs.getString("doi_numero"));
                cli.setTipoDocumento(rs.getString("tipo_documento"));
                cli.setGenero(rs.getString("genero"));
                lista.add(cli);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // ✅ 3️⃣ BUSCAR CLIENTE POR DNI
    public Cliente buscarPorDni(String dni) {
        Cliente cli = null;
        String sql = """
            SELECT c.cliente_id, c.nombres, c.a_paterno, c.a_materno, c.doi_numero,
                   d.descripcion AS tipo_documento, g.descripcion AS genero
            FROM clientes c
            INNER JOIN doi_tipos d ON c.doi_tipo_id = d.doi_tipo_id
            INNER JOIN generos g ON c.genero_id = g.genero_id
            WHERE c.doi_numero = ?;
        """;

        try (Connection con = ConexionMySQL.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, dni);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                cli = new Cliente();
                cli.setId(rs.getInt("cliente_id"));
                cli.setNombres(rs.getString("nombres"));
                cli.setaPaterno(rs.getString("a_paterno"));
                cli.setaMaterno(rs.getString("a_materno"));
                cli.setDni(rs.getString("doi_numero"));
                cli.setTipoDocumento(rs.getString("tipo_documento"));
                cli.setGenero(rs.getString("genero"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cli;
    }

    // ✅ 4️⃣ ACTUALIZAR CLIENTE
    public boolean actualizar(Cliente c) {
        String sql = """
            UPDATE clientes
            SET nombres = ?, a_paterno = ?, a_materno = ?, doi_numero = ?, doi_tipo_id = ?, genero_id = ?
            WHERE cliente_id = ?
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

    // ✅ 5️⃣ ELIMINAR CLIENTE
    public boolean eliminar(int id) {
        String sql = "DELETE FROM clientes WHERE cliente_id = ?";
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
