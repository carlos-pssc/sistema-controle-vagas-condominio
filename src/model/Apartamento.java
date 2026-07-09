package model;

public class Apartamento {

	private int numero;
	private String tipo_responsavel;

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getTipo_responsavel() {
		return tipo_responsavel;
	}

	public void setTipo_responsavel(String tipo_responsavel) {
		this.tipo_responsavel = tipo_responsavel;
	}

	public Apartamento() {
		super();
	}

	public Apartamento(int numero, String tipo_responsavel) {
		super();
		this.numero = numero;
		this.tipo_responsavel = tipo_responsavel;
	}

	@Override
	public String toString() {
		return "Apartamento [numero=" + numero + ", tipo_responsavel=" + tipo_responsavel + "]";
	}

}