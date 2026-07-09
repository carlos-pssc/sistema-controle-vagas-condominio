package model;

public class Veiculo {

	private String placa;
	private String fabricante;
	private String modelo;
	private String cor;
	private int numero_apartamento_vaga;
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getFabricante() {
		return fabricante;
	}
	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getCor() {
		return cor;
	}
	public void setCor(String cor) {
		this.cor = cor;
	}
	public int getNumero_apartamento_vaga() {
		return numero_apartamento_vaga;
	}
	public void setNumero_apartamento_vaga(int numero_apartamento_vaga) {
		this.numero_apartamento_vaga = numero_apartamento_vaga;
	}
	public Veiculo() {
		super();
	}
	public Veiculo(String placa, String fabricante, String modelo, String cor, int numero_apartamento_vaga) {
		super();
		this.placa = placa;
		this.fabricante = fabricante;
		this.modelo = modelo;
		this.cor = cor;
		this.numero_apartamento_vaga = numero_apartamento_vaga;
	}
	@Override
	public String toString() {
		return "Veiculo [placa=" + placa + ", fabricante=" + fabricante + ", modelo=" + modelo + ", cor=" + cor
				+ ", numero_apartamento_vaga=" + numero_apartamento_vaga + "]";
	}

}