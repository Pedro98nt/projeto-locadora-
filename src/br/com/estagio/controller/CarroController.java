package br.com.estagio.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.estagio.dao.CarroDao;
import br.com.estagio.modelo.Carro;

@ManagedBean
@SessionScoped
public class CarroController {

	Carro carro = new Carro();
	CarroDao dao = new CarroDao();

	private static final String PAGINA_CADASTRO_CARRO = "cadastroCarro.xhtml";
	private static final String PAGINA_LISTA_CARRO = "listaCarro.xhtml";

	public Carro getCarro() {
		return carro;
	}

	public void setCarro(Carro carro) {
		this.carro = carro;
	}

	public void limparCampos() {
		carro = new Carro();
	}
	
	private void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public String salvar() throws Exception {
		try {
			if (carro.getId() == 0) {
				dao.incluir(carro);
				exibirMensagem("Inclusão realizada com sucesso!");
				limparCampos();
			} else {
				dao.alterar(carro);
				exibirMensagem("Alteração realizada com sucesso!");
				limparCampos();
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde: " + e.getMessage());
		}
		return PAGINA_CADASTRO_CARRO;
	}

	public String excluir() throws Exception {
		try {
			dao.excluir(carro);
			exibirMensagem("Exclusão realizada com sucesso!");
			limparCampos();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde: " + e.getMessage());
		}
		return PAGINA_LISTA_CARRO;
	}

	public String editar() {
		return PAGINA_CADASTRO_CARRO;
	}

	public List<Carro> getLista() throws Exception {
		List<Carro> listaRetorno = new ArrayList<>();
		try {
			listaRetorno = dao.listar();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde: " + e.getMessage());
		}
		return listaRetorno;
	}
}