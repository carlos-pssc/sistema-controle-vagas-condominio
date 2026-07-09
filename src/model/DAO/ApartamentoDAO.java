package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.Apartamento;
import util.Conexao;

public class ApartamentoDAO {

	public static Apartamento salvar(Apartamento apto) {
		try {

			Conexao conexao = new Conexao("jdbc:mysql://localhost:3306/a3", "com.mysql.cj.jdbc.Driver", "root", "");
			Connection con = conexao.obterConexao();

			String sql = "INSERT INTO apartamento (numero, tipo_responsavel) VALUES (?, ?)";

			PreparedStatement comando = con.prepareStatement(sql);
			comando.setInt(1, apto.getNumero());
			comando.setString(2, apto.getTipo_responsavel());

			comando.executeUpdate();

			comando.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("Erro ao salvar apartamento: " + e.getMessage());
			return null;
		}
		return apto;
	}

	public static Apartamento[] buscarTodos() {
		Apartamento[] resultado = null;
		try {
			Conexao conexao = new Conexao("jdbc:mysql://localhost:3306/a3", "com.mysql.cj.jdbc.Driver", "root", "");
			Connection con = conexao.obterConexao();

			Statement comandoCount = con.createStatement();
			String sqlCount = "SELECT COUNT(*) FROM apartamento";
			ResultSet rsCount = comandoCount.executeQuery(sqlCount);
			int total = 0;
			if (rsCount.next()) {
				total = rsCount.getInt(1);
			}
			rsCount.close();
			comandoCount.close();

			if (total == 0) {
				con.close();
				return new Apartamento[0];
			}

			resultado = new Apartamento[total];

			Statement comandoFetch = con.createStatement();
			String sqlFetch = "SELECT * FROM apartamento ORDER BY numero";
			ResultSet rsFetch = comandoFetch.executeQuery(sqlFetch);

			int i = 0;
			while (rsFetch.next()) {
				Apartamento a = new Apartamento();
				a.setNumero(rsFetch.getInt("numero"));

				a.setTipo_responsavel(rsFetch.getString("tipo_responsavel"));
				resultado[i] = a;
				i++;
			}

			rsFetch.close();
			comandoFetch.close();
			con.close();

		} catch (SQLException e) {
			System.out.println("Erro ao buscar apartamentos: " + e.getMessage());
		}
		return resultado;
	}

	public static Apartamento atualizar(Apartamento apto) {
		try {
			Conexao conexao = new Conexao("jdbc:mysql://localhost:3306/a3", "com.mysql.cj.jdbc.Driver", "root", "");
			Connection con = conexao.obterConexao();

			String sql = "UPDATE apartamento SET tipo_responsavel = ? WHERE numero = ?";

			PreparedStatement comando = con.prepareStatement(sql);
			comando.setString(1, apto.getTipo_responsavel());
			comando.setInt(2, apto.getNumero());

			comando.executeUpdate();

			comando.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar apartamento: " + e.getMessage());
			return null;
		}
		return apto;
	}

	public static void apagar(int numero) {
		try {
			Conexao conexao = new Conexao("jdbc:mysql://localhost:3306/a3", "com.mysql.cj.jdbc.Driver", "root", "");
			Connection con = conexao.obterConexao();

			String sql = "DELETE FROM apartamento WHERE numero = ?";

			PreparedStatement comando = con.prepareStatement(sql);
			comando.setInt(1, numero);

			comando.executeUpdate();

			comando.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("Erro ao apagar apartamento: " + e.getMessage());
		}
	}
}