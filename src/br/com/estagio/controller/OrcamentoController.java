package br.com.estagio.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.estagio.dao.OrcamentoDao;
import br.com.estagio.modelo.Carro;
import br.com.estagio.modelo.Cidade;
import br.com.estagio.modelo.Cliente;
import br.com.estagio.modelo.Estado;
import br.com.estagio.modelo.FormaDePagamento;
import br.com.estagio.modelo.Orcamento;

@ManagedBean
@SessionScoped
public class OrcamentoController {

	Orcamento orcamento = new Orcamento();
	OrcamentoDao dao = new OrcamentoDao();
	Carro carroSelecionado = new Carro();
	Cidade cidadeSelecionado = new Cidade();
	Cliente clienteSelecionado = new Cliente();
	Estado estadoSelecionado = new Estado();
	FormaDePagamento formaPagamentoSelecionado = new FormaDePagamento();

	private static final String PAGINA_CADASTRO_ORCAMENTO = "cadastroOrcamento.xhtml";
	private static final String PAGINA_LISTA_ORCAMENTO = "listaOrcamento.xhtml";

	public Orcamento getOrcamento() {
		return orcamento;
	}

	public void setOrcamento(Orcamento orcamento) {
		this.orcamento = orcamento;
	}

	public Carro getCarroSelecionado() {
		return carroSelecionado;
	}

	public void setCarroSelecionado(Carro carroSelecionado) {
		this.carroSelecionado = carroSelecionado;
	}

	public Cidade getCidadeSelecionado() {
		return cidadeSelecionado;
	}

	public void setCidadeSelecionado(Cidade cidadeSelecionado) {
		this.cidadeSelecionado = cidadeSelecionado;
	}

	public Cliente getClienteSelecionado() {
		return clienteSelecionado;
	}

	public void setClienteSelecionado(Cliente clienteSelecionado) {
		this.clienteSelecionado = clienteSelecionado;
	}

	public Estado getEstadoSelecionado() {
		return estadoSelecionado;
	}

	public void setEstadoSelecionado(Estado estadoSelecionado) {
		this.estadoSelecionado = estadoSelecionado;
	}

	public FormaDePagamento getFormaPagamentoSelecionado() {
		return formaPagamentoSelecionado;
	}

	public void setFormaPagamentoSelecionado(FormaDePagamento formaPagamentoSelecionado) {
		this.formaPagamentoSelecionado = formaPagamentoSelecionado;
	}

	public void limparCampos() {
		orcamento = new Orcamento();
		carroSelecionado = new Carro();
		cidadeSelecionado = new Cidade();
		clienteSelecionado = new Cliente();
		estadoSelecionado = new Estado();
		formaPagamentoSelecionado = new FormaDePagamento();
	}

	private void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public String salvar() throws Exception {
		try {
			if (orcamento.getId() == 0) {
				orcamento.setCarro(carroSelecionado);
				orcamento.setCidade(cidadeSelecionado);
				orcamento.setCliente(clienteSelecionado);
				orcamento.setEstado(estadoSelecionado);
				orcamento.setFormaDePagamento(formaPagamentoSelecionado);
				dao.incluir(orcamento);
				exibirMensagem("Inclusão realizada com sucesso!");
				limparCampos();
			} else {
				orcamento.setCarro(carroSelecionado);
				orcamento.setCidade(cidadeSelecionado);
				orcamento.setCliente(clienteSelecionado);
				orcamento.setEstado(estadoSelecionado);
				orcamento.setFormaDePagamento(formaPagamentoSelecionado);
				dao.alterar(orcamento);
				exibirMensagem("Alteração realizada com sucesso!");
				limparCampos();
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde." + e.getMessage());
		}

		return PAGINA_CADASTRO_ORCAMENTO;
	}

	public String excluir() throws Exception {
		try {
			dao.excluir(orcamento);
			exibirMensagem("Exclusão realizada com sucesso!");
			limparCampos();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde. " + e.getMessage());
		}
		return PAGINA_LISTA_ORCAMENTO;
	}

	public String editar() {
		carroSelecionado = orcamento.getCarro();
		cidadeSelecionado = orcamento.getCidade();
		clienteSelecionado = orcamento.getCliente();
		estadoSelecionado = orcamento.getEstado();
		formaPagamentoSelecionado = orcamento.getFormaDePagamento();

		return PAGINA_CADASTRO_ORCAMENTO;
	}

	public List<Orcamento> getLista() throws Exception {
		List<Orcamento> listaRetorno = new ArrayList<>();
		try {
			listaRetorno = dao.listar();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, tente novamente mais tarde. " + e.getMessage());
		}
		return listaRetorno;
	}
}