package br.com.estagio.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.estagio.modelo.FormaDePagamento;
import br.com.estagio.util.Conexao;

public class FormaDePagamentoDao {

	public void incluir(FormaDePagamento formapagamento) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "INSERT INTO forma_pagamentos (forma_pagamento) VALUES (?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		try {
			ps.setString(1, formapagamento.getFormaPagamento().trim());
			ps.executeUpdate();

		} catch (Exception e) {
			throw new Exception();
		} finally {

			Conexao.fecharConexao(conn, ps, null);
		}

	}

	public void alterar(FormaDePagamento formapagamento) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "UPDATE forma_pagamentos SET forma_pagamento = ? WHERE id_forma_pagamento = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		try {
			ps.setString(1, formapagamento.getFormaPagamento().trim());
			ps.setLong(2, formapagamento.getId());

			ps.executeUpdate();
		} catch (Exception e) {
			throw new Exception();
		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}

	}

	public void excluir(FormaDePagamento formapagamento) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "DELETE FROM forma_pagamentos WHERE id_forma_pagamento = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		try {
			ps.setLong(1, formapagamento.getId());
			ps.executeUpdate();

		} catch (Exception e) {
			throw new Exception();
		} finally {

			Conexao.fecharConexao(conn, ps, null);
		}

	}

	public FormaDePagamento pesquisarPorId(Long id) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT id_forma_pagamento, forma_pagamento FROM forma_pagamentos WHERE id_forma_pagamento = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setLong(1, id);
		ResultSet rs = null;
		FormaDePagamento retorno = new FormaDePagamento();
		try {
			rs = ps.executeQuery();
			if (rs.next()) {
				retorno = popularFormaPagamento(rs);
			}

		} catch (Exception e) {
			throw new Exception();
		} finally {
			Conexao.fecharConexao(conn, ps, rs);
		}

		return retorno;
	}

	public List<FormaDePagamento> listar() throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT id_forma_pagamento, forma_pagamento FROM forma_pagamentos";
		PreparedStatement ps = conn.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();
		List<FormaDePagamento> listaRetorno = new ArrayList<>();
		try {
			while (rs.next()) {
				listaRetorno.add(popularFormaPagamento(rs));
			}

		} catch (Exception e) {
			throw new Exception();
		} finally {

			Conexao.fecharConexao(conn, ps, null);
		}

		return listaRetorno;
	}

	private FormaDePagamento popularFormaPagamento(ResultSet rs) throws SQLException {

		FormaDePagamento formapagamento = new FormaDePagamento();
		formapagamento.setId(rs.getLong("id_forma_pagamento"));
		formapagamento.setFormaPagamento(rs.getString("forma_pagamento").trim());
		return formapagamento;
	}
}
