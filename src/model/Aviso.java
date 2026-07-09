package model;

import java.time.LocalDate;

public class Aviso {

	private int id_aviso;
	private LocalDate data_emissao = LocalDate.now();
	private String cpf_morador_infrator;
	private String email_funcionario;

	public int getId_aviso() {
		return id_aviso;
	}

	public void setId_aviso(int id_aviso) {
		this.id_aviso = id_aviso;
	}

	public LocalDate getData_emissao() {
		return data_emissao;
	}

	public void setData_emissao(LocalDate data_emissao) {
		this.data_emissao = data_emissao;
	}

	public String getCpf_morador_infrator() {
		return cpf_morador_infrator;
	}

	public void setCpf_morador_infrator(String cpf_morador_infrator) {
		this.cpf_morador_infrator = cpf_morador_infrator;
	}

	public String getEmail_funcionario() {
		return email_funcionario;
	}

	public void setEmail_funcionario(String email_funcionario) {
		this.email_funcionario = email_funcionario;
	}

	public Aviso() {
		super();
	}

	public Aviso(int id_aviso, LocalDate data_emissao, String cpf_morador_infrator, String email_funcionario) {
		super();
		this.id_aviso = id_aviso;
		this.data_emissao = data_emissao;
		this.cpf_morador_infrator = cpf_morador_infrator;
		this.email_funcionario = email_funcionario;
	}

	@Override
	public String toString() {
		return "Aviso [id_aviso=" + id_aviso + ", data_emissao=" + data_emissao + ", cpf_morador_infrator="
				+ cpf_morador_infrator + ", email_funcionario=" + email_funcionario + "]";
	}

}