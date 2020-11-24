package br.com.estagio.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.estagio.dao.CidadeDao;
import br.com.estagio.modelo.Cidade;

@ManagedBean
@SessionScoped
public class CidadeController {

	Cidade cidade = new Cidade();
	CidadeDao dao = new CidadeDao();

	private static final String PAGINA_CADASTRO_CIDADE = "cadastroCidade.xhtml";
	private static final String PAGINA_LISTA_CIDADE = "listaCidade.xhtml";

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public void limparCampos() {
		cidade = new Cidade();
	}
	
	private void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public String salvar() throws Exception {
		try {
			if (cidade.getId() == 0) {
				dao.incluir(cidade);
				exibirMensagem("Inclusão realizada com sucesso!");
				limparCampos();
			} else {
				dao.alterar(cidade);
				exibirMensagem("Alteração realizada com sucesso!");
				limparCampos();
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde. " + e.getMessage());
		}
		return PAGINA_CADASTRO_CIDADE;
	}

	public String excluir() throws Exception {
		try {
			dao.excluir(cidade);
			exibirMensagem("Exclusão realizada com sucesso!");
			limparCampos();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde. " + e.getMessage());
		}
		return PAGINA_LISTA_CIDADE;
	}

	public String editar() {
		return PAGINA_CADASTRO_CIDADE;
	}

	public List<Cidade> getLista() throws Exception {
		List<Cidade> listaRetorno = new ArrayList<>();
		try {
			listaRetorno = dao.listar();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde. " + e.getMessage());
		}
		return listaRetorno;
	}
}