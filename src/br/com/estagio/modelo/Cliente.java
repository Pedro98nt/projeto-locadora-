package br.com.estagio.modelo;

public class Cliente {

	private long id;
	private String nomeCliente;
	private String sobrenome;
	private String email;
	private String telefone;
	private String cnh;
	private String cpf;
	private String endereco;
	private String numero;
	private String cidadeCliente;
	private String ufCliente;
	private String bairroCliente;
	private String complemento;

	public Cliente() {
		super();
	}

	public Cliente(long id, String nomeCliente, String sobrenome, String email, String telefone, String cnh, String cpf,
			String endereco, String numero, String cidadeCliente, String ufCliente, String bairroCliente,
			String complemento) {
		super();
		this.id = id;
		this.nomeCliente = nomeCliente;
		this.sobrenome = sobrenome;
		this.email = email;
		this.telefone = telefone;
		this.cnh = cnh;
		this.cpf = cpf;
		this.endereco = endereco;
		this.numero = numero;
		this.cidadeCliente = cidadeCliente;
		this.ufCliente = ufCliente;
		this.bairroCliente = bairroCliente;
		this.complemento = complemento;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCnh() {
		return cnh;
	}

	public void setCnh(String cnh) {
		this.cnh = cnh;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getCidadeCliente() {
		return cidadeCliente;
	}

	public void setCidadeCliente(String cidadeCliente) {
		this.cidadeCliente = cidadeCliente;
	}

	public String getUfCliente() {
		return ufCliente;
	}

	public void setUfCliente(String ufCliente) {
		this.ufCliente = ufCliente;
	}

	public String getBairroCliente() {
		return bairroCliente;
	}

	public void setBairroCliente(String bairroCliente) {
		this.bairroCliente = bairroCliente;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
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
		Cliente other = (Cliente) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
