package br.com.estagio.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.estagio.modelo.Cliente;
import br.com.estagio.util.Conexao;

public class ClienteDao {

	public void incluir(Cliente cliente) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "INSERT INTO clientes (nome_cliente, sobrenome, email, telefone, cnh, cpf, endereco, numero, cidade_cliente, bairro_cliente, complemento) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		try {
			ps.setString(1, cliente.getNomeCliente().trim());
			ps.setString(2, cliente.getSobrenome().trim());
			ps.setString(3, cliente.getEmail().trim());
			ps.setString(4, cliente.getTelefone().trim());
			ps.setString(5, cliente.getCnh().trim());
			ps.setString(6, cliente.getCpf().trim());
			ps.setString(7, cliente.getEndereco().trim());
			ps.setString(8, cliente.getNumero().trim());
			ps.setString(9, cliente.getCidadeCliente().trim());
			ps.setString(10, cliente.getBairroCliente().trim());
			ps.setString(11, cliente.getComplemento().trim());

			ps.executeUpdate();

		} catch (Exception e) {
			throw new Exception();
		} finally {

			Conexao.fecharConexao(conn, ps, null);
		}

	}

	public void alterar(Cliente cliente) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "UPDATE clientes SET nome_cliente = ?, sobrenome = ?, email = ?, telefone = ?, cnh = ?, cpf = ?, endereco = ?, numero = ?, "
				+ " cidade_cliente = ?, bairro_cliente = ?, complemento = ? WHERE id_cliente = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		try {
			ps.setString(1, cliente.getNomeCliente().trim());
			ps.setString(2, cliente.getSobrenome().trim());
			ps.setString(3, cliente.getEmail().trim());
			ps.setString(4, cliente.getTelefone().trim());
			ps.setString(5, cliente.getCnh().trim());
			ps.setString(6, cliente.getCpf().trim());
			ps.setString(7, cliente.getEndereco().trim());
			ps.setString(8, cliente.getNumero().trim());
			ps.setString(9, cliente.getCidadeCliente().trim());
			ps.setString(10, cliente.getBairroCliente().trim());
			ps.setString(11, cliente.getComplemento().trim());
			ps.setLong(12, cliente.getId());

			ps.executeUpdate();

		} catch (Exception e) {
			throw new Exception();
		} finally {

			Conexao.fecharConexao(conn, ps, null);
		}

	}

	public void excluir(Cliente cliente) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "DELETE FROM clientes WHERE id_cliente = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		try {
			ps.setLong(1, cliente.getId());
			ps.executeUpdate();

		} catch (Exception e) {
			throw new Exception();
		} finally {

			Conexao.fecharConexao(conn, ps, null);
		}

	}

	public Cliente pesquisarPorId(Long id) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT id_cliente, nome_cliente, sobrenome, email, telefone, cnh, cpf, endereco, numero, cidade_cliente, "
				+ "bairro_cliente, complemento FROM clientes WHERE id_cliente = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		Cliente retorno = new Cliente();
		try {
			ps.setLong(1, id);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				retorno = popularCliente(rs);
			}

		} catch (Exception e) {
			throw new Exception();
		} finally {

			Conexao.fecharConexao(conn, ps, null);
		}

		return retorno;
	}

	public List<Cliente> listar() throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT id_cliente, nome_cliente, sobrenome, email, telefone, cnh, cpf, endereco, numero, cidade_cliente, "
				+ "bairro_cliente, complemento FROM clientes";
		PreparedStatement ps = conn.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();
		List<Cliente> listaRetorno = new ArrayList<>();
		try {
			while (rs.next()) {
				listaRetorno.add(popularCliente(rs));
			}

		} catch (Exception e) {
			throw new Exception();
		} finally {

			Conexao.fecharConexao(conn, ps, null);

		}

		return listaRetorno;
	}

	private Cliente popularCliente(ResultSet rs) throws SQLException {

		Cliente cliente = new Cliente();

		cliente.setId(rs.getLong("id_cliente"));
		cliente.setNomeCliente(rs.getString("nome_cliente").trim());
		cliente.setSobrenome(rs.getString("sobrenome").trim());
		cliente.setEmail(rs.getString("email").trim());
		cliente.setTelefone(rs.getString("telefone").trim());
		cliente.setCnh(rs.getString("cnh").trim());
		cliente.setCpf(rs.getString("cpf").trim());
		cliente.setEndereco(rs.getString("endereco").trim());
		cliente.setNumero(rs.getString("numero").trim());
		cliente.setCidadeCliente(rs.getString("cidade_cliente").trim());
		cliente.setBairroCliente(rs.getString("bairro_cliente").trim());
		cliente.setComplemento(rs.getString("complemento").trim());

		return cliente;
	}
}
