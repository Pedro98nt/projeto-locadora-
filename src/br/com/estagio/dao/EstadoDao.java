package br.com.estagio.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.estagio.modelo.Estado;
import br.com.estagio.util.Conexao;

public class EstadoDao {

	public void incluir(Estado estado) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "INSERT INTO estados (uf_estado) VALUES (?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		try {
			ps.setString(1, estado.getUfEstado().trim());
			ps.executeUpdate();

		} catch (Exception e) {
			throw new Exception();
		} finally {

			Conexao.fecharConexao(conn, ps, null);
		}

	}

	public void alterar(Estado estado) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "UPDATE estados SET uf_estado = ? WHERE id_estado = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		try {
			ps.setString(1, estado.getUfEstado().trim());
			ps.setLong(2, estado.getId());

			ps.executeUpdate();

		} catch (Exception e) {
			throw new Exception();
		} finally {

			Conexao.fecharConexao(conn, ps, null);
		}

	}

	public void excluir(Estado estado) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "DELETE FROM estados WHERE id_estado = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		try {
			ps.setLong(1, estado.getId());
			ps.executeUpdate();

		} catch (Exception e) {
			throw new Exception();
		} finally {
			Conexao.fecharConexao(conn, ps, null);

		}

	}

	public Estado pesquisarPorId(Long id) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT id_estado, uf_estado FROM estados WHERE id_estado = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = null;
		Estado retorno = new Estado();
		try {
			ps.setLong(1, id);

			rs = ps.executeQuery();
			if (rs.next()) {
				retorno = popularEstado(rs);
			}

		} catch (Exception e) {
			throw new Exception();
		} finally {

			Conexao.fecharConexao(conn, ps, null);
		}

		return retorno;
	}

	public List<Estado> listar() throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT id_estado, uf_estado FROM estados";
		PreparedStatement ps = conn.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();
		List<Estado> listaRetorno = new ArrayList<>();
		try {
			while (rs.next()) {
				listaRetorno.add(popularEstado(rs));
			}

		} catch (Exception e) {
			throw new Exception();
		} finally {

			Conexao.fecharConexao(conn, ps, null);
		}

		return listaRetorno;
	}

	private Estado popularEstado(ResultSet rs) throws SQLException {

		Estado estado = new Estado();
		estado.setId(rs.getLong("id_estado"));
		estado.setUfEstado(rs.getString("uf_estado").trim());
		return estado;
	}
}
