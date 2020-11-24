package br.com.estagio.modelo;

public class FormaDePagamento {

	private long id;
	private String formaPagamento;

	public FormaDePagamento() {
		super();
	}

	public FormaDePagamento(long id, String formaPagamento) {
		super();
		this.id = id;
		this.formaPagamento = formaPagamento;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
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
		FormaDePagamento other = (FormaDePagamento) obj;
		if (id != other.id)
			return false;
		return true;
	}

}