package br.com.estagio.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.estagio.dao.ClienteDao;
import br.com.estagio.modelo.Cliente;

@ManagedBean
@SessionScoped
public class ClienteController {

	Cliente cliente = new Cliente();
	ClienteDao dao = new ClienteDao();

	private static final String PAGINA_CADASTRO_CLIENTE = "cadastroCliente.xhtml";
	private static final String PAGINA_LISTA_CLIENTE = "listaCliente.xhtml";

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void limparCampos() {
		cliente = new Cliente();
	}

	private void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public String salvar() throws Exception {
		try {
			if (cliente.getId() == 0) {
				dao.incluir(cliente);
				exibirMensagem("Inclusão realizada com sucesso!");
				limparCampos();
			} else {
				dao.alterar(cliente);
				exibirMensagem("Alteração realizada com sucesso!");
				limparCampos();
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde. " + e.getMessage());
		}
		return PAGINA_CADASTRO_CLIENTE;
	}

	public String excluir() throws Exception {
		try {
			dao.excluir(cliente);
			exibirMensagem("Exclusão realizada com sucesso!");
			limparCampos();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde. " + e.getMessage());
		}
		return PAGINA_LISTA_CLIENTE;
	}

	public String editar() {
		return PAGINA_CADASTRO_CLIENTE;
	}

	public List<Cliente> getLista() throws Exception {
		List<Cliente> listaRetorno = new ArrayList<>();
		try {
			listaRetorno = dao.listar();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde. " + e.getMessage());
		}
		return listaRetorno;
	}
}