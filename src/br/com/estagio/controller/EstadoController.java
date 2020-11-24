package br.com.estagio.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.estagio.dao.EstadoDao;
import br.com.estagio.modelo.Estado;

@ManagedBean
@SessionScoped
public class EstadoController {

	Estado estado = new Estado();
	EstadoDao dao = new EstadoDao();

	private static final String PAGINA_CADASTRO_ESTADO = "cadastroEstado.xhtml";
	private static final String PAGINA_LISTA_ESTADO = "listaEstado.xhtml";

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public void limparCampos() {
		estado = new Estado();
	}
	
	private void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public String salvar() throws Exception {
		try {
			if (estado.getId() == 0) {
				dao.incluir(estado);
				exibirMensagem("Inclusão realizada com sucesso!");
				limparCampos();
			} else {
				dao.alterar(estado);
				exibirMensagem("Alteração realizada com sucesso!");
				limparCampos();
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde. " + e.getMessage());
		}
		return PAGINA_CADASTRO_ESTADO;
	}

	public String excluir() throws Exception {
		try {
			dao.excluir(estado);
			exibirMensagem("Exclusão realizada com sucesso!");
			limparCampos();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde. " + e.getMessage());
		}
		return PAGINA_LISTA_ESTADO;
	}

	public String editar() {
		return PAGINA_CADASTRO_ESTADO;
	}

	public List<Estado> getLista() throws Exception {
		List<Estado> listaRetorno = new ArrayList<>();
		try {
			listaRetorno = dao.listar();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde. " + e.getMessage());
		}
		return listaRetorno;
	}
}