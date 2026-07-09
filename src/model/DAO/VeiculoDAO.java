package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.Veiculo;
import util.Conexao;

public class VeiculoDAO {

	public static Veiculo salvar(Veiculo veiculo) {
		try {

			Conexao conexao = new Conexao("jdbc:mysql://localhost:3306/a3", "com.mysql.cj.jdbc.Driver", "root", "");
			Connection con = conexao.obterConexao();

			String sql = "INSERT INTO veiculo (placa, fabricante, modelo, cor, numero_apartamento_vaga) VALUES (?, ?, ?, ?, ?)";

			PreparedStatement comando = con.prepareStatement(sql);

			comando.setString(1, veiculo.getPlaca());
			comando.setString(2, veiculo.getFabricante());
			comando.setString(3, veiculo.getModelo());
			comando.setString(4, veiculo.getCor());
			comando.setInt(5, veiculo.getNumero_apartamento_vaga());

			comando.executeUpdate();

			comando.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("Erro ao salvar veículo: " + e.getMessage());
			return null;
		}
		return veiculo;
	}

	public static Veiculo[] buscarTodos() {
		Veiculo[] resultado = null;
		try {
			Conexao conexao = new Conexao("jdbc:mysql://localhost:3306/a3", "com.mysql.cj.jdbc.Driver", "root", "");
			Connection con = conexao.obterConexao();

			Statement comandoCount = con.createStatement();
			String sqlCount = "SELECT COUNT(*) FROM veiculo";
			ResultSet rsCount = comandoCount.executeQuery(sqlCount);
			int total = 0;
			if (rsCount.next()) {
				total = rsCount.getInt(1);
			}
			rsCount.close();
			comandoCount.close();

			if (total == 0) {
				con.close();
				return new Veiculo[0];
			}

			resultado = new Veiculo[total];

			Statement comandoFetch = con.createStatement();
			String sqlFetch = "SELECT * FROM veiculo ORDER BY placa";
			ResultSet rsFetch = comandoFetch.executeQuery(sqlFetch);

			int i = 0;
			while (rsFetch.next()) {
				Veiculo v = new Veiculo();

				v.setPlaca(rsFetch.getString("placa"));
				v.setFabricante(rsFetch.getString("fabricante"));
				v.setModelo(rsFetch.getString("modelo"));
				v.setCor(rsFetch.getString("cor"));
				v.setNumero_apartamento_vaga(rsFetch.getInt("numero_apartamento_vaga"));
				resultado[i] = v;
				i++;
			}

			rsFetch.close();
			comandoFetch.close();
			con.close();

		} catch (SQLException e) {
			System.out.println("Erro ao buscar veículos: " + e.getMessage());
		}
		return resultado;
	}

	public static Veiculo atualizar(Veiculo veiculo) {
		try {
			Conexao conexao = new Conexao("jdbc:mysql://localhost:3306/a3", "com.mysql.cj.jdbc.Driver", "root", "");
			Connection con = conexao.obterConexao();

			String sql = "UPDATE veiculo SET fabricante = ?, modelo = ?, cor = ?, numero_apartamento_vaga = ? WHERE placa = ?";

			PreparedStatement comando = con.prepareStatement(sql);
			comando.setString(1, veiculo.getFabricante());
			comando.setString(2, veiculo.getModelo());
			comando.setString(3, veiculo.getCor());
			comando.setInt(4, veiculo.getNumero_apartamento_vaga());
			comando.setString(5, veiculo.getPlaca());

			comando.executeUpdate();

			comando.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar veículo: " + e.getMessage());
			return null;
		}
		return veiculo;
	}

	public static void apagar(String placa) {
		try {
			Conexao conexao = new Conexao("jdbc:mysql://localhost:3306/a3", "com.mysql.cj.jdbc.Driver", "root", "");
			Connection con = conexao.obterConexao();

			String sql = "DELETE FROM veiculo WHERE placa = ?";

			PreparedStatement comando = con.prepareStatement(sql);
			comando.setString(1, placa);

			comando.executeUpdate();

			comando.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("Erro ao apagar veículo: " + e.getMessage());
		}
	}
}