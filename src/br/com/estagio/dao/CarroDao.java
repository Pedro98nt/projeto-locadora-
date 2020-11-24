package br.com.estagio.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.estagio.modelo.Carro;
import br.com.estagio.util.Conexao;

public class CarroDao {

	public void incluir(Carro carro) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "INSERT INTO carros (modelo_carro, ano_carro, marca_carro, descricao_carro) VALUES (?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		try {
			ps.setString(1, carro.getModeloCarro().trim());
			ps.setInt(2, carro.getAnoCarro());
			ps.setString(3, carro.getMarcaCarro().trim());
			ps.setString(4, carro.getDescricaoCarro().trim());
			ps.executeUpdate();

		} catch (Exception e) {
			throw new Exception();
		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}
	}

	public void alterar(Carro carro) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "UPDATE carros SET modelo_carro = ?, ano_carro = ?, marca_carro = ?, descricao_carro = ? WHERE id_carro = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		try {
			ps.setString(1, carro.getModeloCarro().trim());
			ps.setInt(2, carro.getAnoCarro());
			ps.setString(3, carro.getMarcaCarro());
			ps.setString(4, carro.getDescricaoCarro());
			ps.setLong(5, carro.getId());

			ps.executeUpdate();

		} catch (Exception e) {
			throw new Exception();
		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}
	}

	public void excluir(Carro carro) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "DELETE FROM carros WHERE id_carro = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		try {
			ps.setLong(1, carro.getId());
			ps.executeUpdate();

		} catch (Exception e) {
			throw new Exception();
		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}
	}

	public Carro pesquisarPorId(Long id) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT id_carro, modelo_carro, ano_carro, marca_carro, descricao_carro FROM carros WHERE id_carro = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = null;
		Carro retorno = new Carro();
		try {
			ps.setLong(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				retorno = popularCarro(rs);
			}

		} catch (Exception e) {
			throw new Exception();
		} finally {

			Conexao.fecharConexao(conn, ps, rs);
		}

		return retorno;
	}

	public List<Carro> listar() throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT id_carro, modelo_carro, ano_carro, marca_carro, descricao_carro FROM carros";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<Carro> listaRetorno = new ArrayList<>();
		try {
			while (rs.next()) {
				listaRetorno.add(popularCarro(rs));
			}

		} catch (Exception e) {
			throw new Exception();
		}

		Conexao.fecharConexao(conn, ps, null);

		return listaRetorno;
	}

	private Carro popularCarro(ResultSet rs) throws SQLException {

		Carro carro = new Carro();
		carro.setId(rs.getLong("id_carro"));
		carro.setModeloCarro(rs.getString("modelo_carro").trim());
		carro.setAnoCarro(rs.getInt("ano_carro"));
		carro.setMarcaCarro(rs.getString("marca_carro").trim());
		carro.setDescricaoCarro(rs.getString("descricao_carro").trim());
		return carro;
	}
}