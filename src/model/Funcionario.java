package model;

public class Funcionario {
	private String email;
	private String nome_completo;
	private String senha;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome_completo() {
		return nome_completo;
	}

	public void setNome_completo(String nome_completo) {
		this.nome_completo = nome_completo;
	}

	public Funcionario() {
		super();
	}

	public Funcionario(String email, String senha, String nome_completo) {
		super();
		this.email = email;
		this.nome_completo= senha;
		this.senha = nome_completo;
	}

	@Override
	public String toString() {
		return "Funcionario [email=" + email + ", senha=" + senha + ", nome_completo=" + nome_completo + "]";
	}

}