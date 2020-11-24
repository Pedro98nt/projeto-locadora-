package br.com.estagio.modelo;

import java.util.Date;

public class Orcamento {

	private long id;
	private Date dtLocacao;
	private Date dtDevolucao;
	private int idCarro;
	private int idCliente;
	private int idCidade;
	private int idEstado;
	private int diaria;
	private double valor;
	private double valorTotal;
	private Cliente cliente;
	private Cidade cidade;
	private Estado estado;
	private Carro carro;
	private FormaDePagamento formaDePagamento;

	public Orcamento() {
		super();
	}

	public Orcamento(long id, Date dtLocacao, Date dtDevolucao, int idCarro, int idCliente, int idCidade, int idEstado,
			int diaria, double valor, double valorTotal, Cliente cliente, Cidade cidade, Estado estado, Carro carro,
			FormaDePagamento formaDePagamento) {
		super();
		this.id = id;
		this.dtLocacao = dtLocacao;
		this.dtDevolucao = dtDevolucao;
		this.idCarro = idCarro;
		this.idCliente = idCliente;
		this.idCidade = idCidade;
		this.idEstado = idEstado;
		this.diaria = diaria;
		this.valor = valor;
		this.valorTotal = valorTotal;
		this.cliente = cliente;
		this.cidade = cidade;
		this.estado = estado;
		this.carro = carro;
		this.formaDePagamento = formaDePagamento;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDtLocacao() {
		return dtLocacao;
	}

	public void setDtLocacao(Date dtLocacao) {
		this.dtLocacao = dtLocacao;
	}

	public Date getDtDevolucao() {
		return dtDevolucao;
	}

	public void setDtDevolucao(Date dtDevolucao) {
		this.dtDevolucao = dtDevolucao;
	}

	public int getIdCarro() {
		return idCarro;
	}

	public void setIdCarro(int idCarro) {
		this.idCarro = idCarro;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public int getIdCidade() {
		return idCidade;
	}

	public void setIdCidade(int idCidade) {
		this.idCidade = idCidade;
	}

	public int getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(int idEstado) {
		this.idEstado = idEstado;
	}

	public int getDiaria() {
		return diaria;
	}

	public void setDiaria(int diaria) {
		this.diaria = diaria;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Carro getCarro() {
		return carro;
	}

	public void setCarro(Carro carro) {
		this.carro = carro;
	}

	public FormaDePagamento getFormaDePagamento() {
		return formaDePagamento;
	}

	public void setFormaDePagamento(FormaDePagamento formaDePagamento) {
		this.formaDePagamento = formaDePagamento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Orcamento other = (Orcamento) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
