package br.com.estagio.modelo;

public class Carro {

	private long id;
	private String modeloCarro;
	private int anoCarro;
	private String marcaCarro;
	private String descricaoCarro;

	public Carro() {
		super();
	}

	public Carro(long id, String modeloCarro, int anoCarro, String marcaCarro, String descricaoCarro) {
		super();
		this.id = id;
		this.modeloCarro = modeloCarro;
		this.anoCarro = anoCarro;
		this.marcaCarro = marcaCarro;
		this.descricaoCarro = descricaoCarro;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getModeloCarro() {
		return modeloCarro;
	}

	public void setModeloCarro(String modeloCarro) {
		this.modeloCarro = modeloCarro;
	}

	public int getAnoCarro() {
		return anoCarro;
	}

	public void setAnoCarro(int anoCarro) {
		this.anoCarro = anoCarro;
	}

	public String getMarcaCarro() {
		return marcaCarro;
	}

	public void setMarcaCarro(String marcaCarro) {
		this.marcaCarro = marcaCarro;
	}

	public String getDescricaoCarro() {
		return descricaoCarro;
	}

	public void setDescricaoCarro(String descricaoCarro) {
		this.descricaoCarro = descricaoCarro;
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
		Carro other = (Carro) obj;
		if (id != other.id)
			return false;
		return true;
	}
}