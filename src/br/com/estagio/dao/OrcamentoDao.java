package br.com.estagio.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.estagio.modelo.Carro;
import br.com.estagio.modelo.Cidade;
import br.com.estagio.modelo.Cliente;
import br.com.estagio.modelo.Estado;
import br.com.estagio.modelo.FormaDePagamento;
import br.com.estagio.modelo.Orcamento;
import br.com.estagio.util.Conexao;

public class OrcamentoDao {

	public void incluir(Orcamento orcamento) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "INSERT INTO orcamentos (dt_locacao, dt_devolucao, id_carro, id_cliente, id_cidade, id_estado, id_forma_pagamento, diaria, valor, valor_total) VALUES (?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		try {
			ps.setDate(1, new java.sql.Date(orcamento.getDtLocacao().getTime()));
			ps.setDate(2, new java.sql.Date(orcamento.getDtDevolucao().getTime()));
			ps.setLong(3, orcamento.getCarro().getId());
			ps.setLong(4, orcamento.getCliente().getId());
			ps.setLong(5, orcamento.getCidade().getId());
			ps.setLong(6, orcamento.getEstado().getId());
			ps.setLong(7, orcamento.getFormaDePagamento().getId());
			ps.setInt(8, orcamento.getDiaria());
			ps.setDouble(9, orcamento.getValor());
			ps.setDouble(10, orcamento.getValorTotal());

			ps.executeUpdate();

		} catch (Exception e) {
			throw new Exception();
		} finally {
			Conexao.fecharConexao(conn, ps, null);

		}

	}

	public void alterar(Orcamento orcamento) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "UPDATE orcamentos SET dt_locacao = ?, dt_devolucao = ?, id_carro = ?, id_cliente = ?, "
				+ "	id_cidade = ?, id_estado = ?, id_forma_pagamento = ?, diaria = ?, valor = ?, valor_total = ? WHERE id_orcamento = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		try {
			ps.setDate(1, new java.sql.Date(orcamento.getDtLocacao().getTime()));
			ps.setDate(2, new java.sql.Date(orcamento.getDtDevolucao().getTime()));
			ps.setLong(3, orcamento.getCarro().getId());
			ps.setLong(4, orcamento.getCliente().getId());
			ps.setLong(5, orcamento.getCidade().getId());
			ps.setLong(6, orcamento.getEstado().getId());
			ps.setLong(7, orcamento.getFormaDePagamento().getId());
			ps.setInt(8, orcamento.getDiaria());
			ps.setDouble(9, orcamento.getValor());
			ps.setDouble(10, orcamento.getValorTotal());
			ps.setLong(11, orcamento.getId());

			ps.executeUpdate();

		} catch (Exception e) {
			throw new Exception();
		} finally {
			Conexao.fecharConexao(conn, ps, null);

		}

	}

	public void excluir(Orcamento orcamento) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "DELETE FROM orcamentos WHERE id_orcamento = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		try {
			ps.setLong(1, orcamento.getId());

			ps.executeUpdate();

		} catch (Exception e) {
			throw new Exception();
		} finally {
			Conexao.fecharConexao(conn, ps, null);

		}

	}

	public List<Orcamento> listar() throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT o.id_orcamento AS idOrcamento, " + " o.dt_locacao AS dtLocacao, "
				+ " o.dt_devolucao AS dtDevolucao, " + " o.diaria AS diaria, " + " o.valor AS valor, "
				+ " o.valor_total AS valorTotal, " + " ca.id_carro AS idCarro, "
				+ " ca.modelo_carro	AS modeloCarro, " + " ca.ano_carro AS anoCarro, "
				+ " ca.marca_carro AS marcaCarro, " + " ca.descricao_carro AS descricaoCarro, "
				+ " ci.id_cidade AS idCidade, " + " ci.nome_cidade AS nomeCidade, " + " cl.id_cliente AS idCliente, "
				+ " cl.nome_cliente AS nomeCliente, " + " cl.sobrenome AS sobrenomeCliente, "
				+ " cl.email AS emailCliente, " + " cl.telefone AS telefoneCliente, " + " cl.cnh AS cnhCliente, "
				+ " cl.cpf AS cpfCliente, " + " cl.endereco AS enderecoCliente, " + " cl.numero AS numeroCliente, "
				+ " cl.cidade_cliente AS cidadeCliente, " + " cl.bairro_cliente AS bairroCliente, "
				+ " cl.complemento AS complementoCliente, " + " e.id_estado AS idEstado, "
				+ " e.uf_estado AS ufEstado, " + " f.id_forma_pagamento AS idFormaPagamento, "
				+ " f.forma_pagamento AS formaPagamento"
				+ " FROM orcamentos o INNER JOIN carros ca ON o.id_carro = ca.id_carro"
				+ " INNER JOIN cidades ci ON o.id_cidade = ci.id_cidade"
				+ " INNER JOIN clientes cl ON o.id_cliente = cl.id_cliente"
				+ " INNER JOIN estados e ON o.id_estado = e.id_estado"
				+ " INNER JOIN forma_pagamentos f ON o.id_forma_pagamento = f.id_forma_pagamento";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = null;
		List<Orcamento> listaRetorno = new ArrayList<>();
		try {
			rs = ps.executeQuery();
			while (rs.next()) {

				Orcamento orcamento = new Orcamento();

				orcamento.setId(rs.getLong("idOrcamento"));
				orcamento.setDtLocacao(rs.getDate("dtLocacao"));
				orcamento.setDtDevolucao(rs.getDate("dtDevolucao"));
				orcamento.setDiaria(rs.getInt("diaria"));
				orcamento.setValor(rs.getDouble("valor"));
				orcamento.setValorTotal(rs.getDouble("valorTotal"));

				Carro carro = new Carro();

				carro.setId(rs.getLong("idCarro"));
				carro.setModeloCarro(rs.getString("modeloCarro").trim());
				carro.setAnoCarro(rs.getInt("anoCarro"));
				carro.setMarcaCarro(rs.getString("marcaCarro").trim());
				carro.setDescricaoCarro(rs.getString("descricaoCarro").trim());

				orcamento.setCarro(carro);

				Cidade cidade = new Cidade();

				cidade.setId(rs.getLong("idCidade"));
				cidade.setNomeCidade(rs.getString("nomeCidade").trim());

				orcamento.setCidade(cidade);

				Cliente cliente = new Cliente();

				cliente.setId(rs.getLong("idCliente"));
				cliente.setNomeCliente(rs.getString("nomeCliente").trim());
				cliente.setSobrenome(rs.getString("sobrenomeCliente").trim());
				cliente.setEmail(rs.getString("emailCliente").trim());
				cliente.setTelefone(rs.getString("telefoneCliente").trim());
				cliente.setCnh(rs.getString("cnhCliente").trim());
				cliente.setCpf(rs.getString("cpfCliente").trim());
				cliente.setEndereco(rs.getString("enderecoCliente").trim());
				cliente.setNumero(rs.getString("numeroCliente").trim());
				cliente.setCidadeCliente(rs.getString("cidadeCliente").trim());
				cliente.setBairroCliente(rs.getString("bairroCliente").trim());
				cliente.setComplemento(rs.getString("complementoCliente").trim());

				orcamento.setCliente(cliente);

				Estado estado = new Estado();

				estado.setId(rs.getLong("idEstado"));
				estado.setUfEstado(rs.getString("ufEstado").trim());

				orcamento.setEstado(estado);

				FormaDePagamento formaPagamento = new FormaDePagamento();

				formaPagamento.setId(rs.getLong("idFormaPagamento"));
				formaPagamento.setFormaPagamento(rs.getString("formaPagamento").trim());

				orcamento.setFormaDePagamento(formaPagamento);

				listaRetorno.add(orcamento);
			}
		} catch (Exception e) {
			throw new Exception();
		} finally {

			Conexao.fecharConexao(conn, ps, null);
		}

		return listaRetorno;
	}

}
