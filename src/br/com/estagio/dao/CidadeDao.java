package br.com.estagio.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.estagio.modelo.Cidade;
import br.com.estagio.util.Conexao;

public class CidadeDao {

	public void incluir(Cidade cidade) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "INSERT INTO cidades (nome_cidade) VALUES (?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		try {
			ps.setString(1, cidade.getNomeCidade().trim());
			ps.executeUpdate();

		} catch (Exception e) {
			throw new Exception();
		} finally {

			Conexao.fecharConexao(conn, ps, null);
		}

	}

	public void alterar(Cidade cidade) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "UPDATE cidades SET nome_cidade = ? WHERE id_cidade = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		try {
			ps.setString(1, cidade.getNomeCidade().trim());
			ps.setLong(2, cidade.getId());

			ps.executeUpdate();

		} catch (Exception e) {
			throw new Exception();
		} finally {

			Conexao.fecharConexao(conn, ps, null);
		}

	}

	public void excluir(Cidade cidade) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "DELETE FROM cidades WHERE id_cidade = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		try {
			ps.setLong(1, cidade.getId());
			ps.executeUpdate();

		} catch (Exception e) {
			throw new Exception();
		} finally {

			Conexao.fecharConexao(conn, ps, null);
		}

	}

	public Cidade pesquisarPorId(Long id) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT id_cidade, nome_cidade FROM cidades WHERE id_cidade = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = null;
		Cidade retorno = new Cidade();
		try {
			ps.setLong(1, id);

			rs = ps.executeQuery();
			if (rs.next()) {
				retorno = popularCidade(rs);
			}

		} catch (Exception e) {
			throw new Exception();
		} finally {

			Conexao.fecharConexao(conn, ps, null);
		}
		return retorno;

	}

	public List<Cidade> listar() throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT id_cidade, nome_cidade FROM cidades";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<Cidade> listaRetorno = new ArrayList<>();
		try {
			while (rs.next()) {
				listaRetorno.add(popularCidade(rs));
			}

		} catch (Exception e) {
			throw new Exception();
		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}

		return listaRetorno;
	}

	private Cidade popularCidade(ResultSet rs) throws SQLException {

		Cidade cidade = new Cidade();
		cidade.setId(rs.getLong("id_cidade"));
		cidade.setNomeCidade(rs.getString("nome_cidade").trim());
		return cidade;
	}
}