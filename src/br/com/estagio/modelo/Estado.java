package br.com.estagio.modelo;

public class Estado {

	private long id;
	private String ufEstado;

	public Estado() {
		super();
	}

	public Estado(long id, String ufEstado) {
		super();
		this.id = id;
		this.ufEstado = ufEstado;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUfEstado() {
		return ufEstado;
	}

	public void setUfEstado(String ufEstado) {
		this.ufEstado = ufEstado;
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
		Estado other = (Estado) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
