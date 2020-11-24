package br.com.estagio.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.estagio.dao.FormaDePagamentoDao;
import br.com.estagio.modelo.FormaDePagamento;

@ManagedBean
@SessionScoped
public class FormaDePagamentoController {

	FormaDePagamento formaPagamento = new FormaDePagamento();
	FormaDePagamentoDao dao = new FormaDePagamentoDao();

	private static final String PAGINA_CADASTRO_FORMA_PAGAMENTO = "cadastroFormaPagamento.xhtml";
	private static final String PAGINA_LISTA_FORMA_PAGAMENTO = "listaFormaPagamento.xhtml";

	public FormaDePagamento getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(FormaDePagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public void limparCampos() {
		formaPagamento = new FormaDePagamento();
	}

	private void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public String salvar() throws Exception {
		try {
			if (formaPagamento.getId() == 0) {
				dao.incluir(formaPagamento);
				exibirMensagem("Inclusão realizada com sucesso!");
				limparCampos();
			} else {
				dao.alterar(formaPagamento);
				exibirMensagem("Alteração realizada com sucesso!");
				limparCampos();
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde. " + e.getMessage());
		}
		return PAGINA_CADASTRO_FORMA_PAGAMENTO;
	}

	public String excluir() throws Exception {
		try {
			dao.excluir(formaPagamento);
			exibirMensagem("Exclusão realizada com sucesso!");
			limparCampos();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde. " + e.getMessage());
		}
		return PAGINA_LISTA_FORMA_PAGAMENTO;
	}

	public String editar() {
		return PAGINA_CADASTRO_FORMA_PAGAMENTO;
	}

	public List<FormaDePagamento> getLista() throws Exception {
		List<FormaDePagamento> listaRetorno = new ArrayList<>();
		try {
			listaRetorno = dao.listar();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde. " + e.getMessage());
		}
		return listaRetorno;
	}
}