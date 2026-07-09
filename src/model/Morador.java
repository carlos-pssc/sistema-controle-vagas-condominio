package model;

public class Morador {

	private String cpf;
	private String nome_completo;
	private String email, telefones;
	private int numero_apartamento;
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNome_completo() {
		return nome_completo;
	}
	public void setNome_completo(String nome_completo) {
		this.nome_completo = nome_completo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefones() {
		return telefones;
	}
	public void setTelefones(String telefones) {
		this.telefones = telefones;
	}
	public int getNumero_apartamento() {
		return numero_apartamento;
	}
	public void setNumero_apartamento(int numero_apartamento) {
		this.numero_apartamento = numero_apartamento;
	}
	public Morador() {
		super();
	}
	public Morador(String cpf, String nome_completo, String email, String telefones, int numero_apartamento) {
		super();
		this.cpf = cpf;
		this.nome_completo = nome_completo;
		this.email = email;
		this.telefones = telefones;
		this.numero_apartamento = numero_apartamento;
	}
	@Override
	public String toString() {
		return "Morador [cpf=" + cpf + ", Nome completo=" + nome_completo + ", email=" + email + ", telefones="
				+ telefones + ", numero_apartamento=" + numero_apartamento + "]";
	}
	
}