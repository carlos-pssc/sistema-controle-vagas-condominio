package model;

import java.time.LocalDate;

public class Multa {

	private int id_multa;

	 private LocalDate data_emissao = LocalDate.now();

	private final double valor = 200.00;

	private String cpf_morador_infrator;

	private String email_funcionario;

	public int getId_multa() {
		return id_multa;
	}

	public void setId_multa(int id_multa) {
		this.id_multa = id_multa;
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

	public double getValor() {
		return valor;
	}

	public Multa() {
		super();
	}

	public Multa(int id_multa, LocalDate data_emissao, String cpf_morador_infrator, String email_funcionario) {
		super();
		this.id_multa = id_multa;
		this.data_emissao = data_emissao;
		this.cpf_morador_infrator = cpf_morador_infrator;
		this.email_funcionario = email_funcionario;
	}

	@Override
	public String toString() {
		return "Multa [id_multa=" + id_multa + ", data_emissao=" + data_emissao + ", valor=" + valor
				+ ", cpf_morador_infrator=" + cpf_morador_infrator + ", email_funcionario=" + email_funcionario + "]";
	}

}