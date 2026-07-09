package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.Morador;
import util.Conexao;

public class MoradorDAO {

	public static Morador salvar(Morador morador) {
		try {
			Conexao conexao = new Conexao("jdbc:mysql://localhost:3306/a3", "com.mysql.cj.jdbc.Driver", "root", "");
			Connection con = conexao.obterConexao();

			String sql = "INSERT INTO morador (cpf, nome_completo, email, telefones, numero_apartamento) VALUES (?, ?, ?, ?, ?)";

			PreparedStatement comando = con.prepareStatement(sql);

			comando.setString(1, morador.getCpf());
			comando.setString(2, morador.getNome_completo());
			comando.setString(3, morador.getEmail());
			comando.setString(4, morador.getTelefones());
			comando.setInt(5, morador.getNumero_apartamento());

			comando.executeUpdate();

			comando.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("Erro ao salvar morador: " + e.getMessage());
			return null;
		}
		return morador;
	}

	public static Morador[] buscarTodos() {
		Morador[] resultado = null;
		try {
			Conexao conexao = new Conexao("jdbc:mysql://localhost:3306/a3", "com.mysql.cj.jdbc.Driver", "root", "");
			Connection con = conexao.obterConexao();

			Statement comandoCount = con.createStatement();
			String sqlCount = "SELECT COUNT(*) FROM morador";
			ResultSet rsCount = comandoCount.executeQuery(sqlCount);
			int total = 0;
			if (rsCount.next()) {
				total = rsCount.getInt(1);
			}
			rsCount.close();
			comandoCount.close();

			if (total == 0) {
				con.close();
				return new Morador[0];
			}

			resultado = new Morador[total];

			Statement comandoFetch = con.createStatement();
			String sqlFetch = "SELECT * FROM morador ORDER BY nome_completo";
			ResultSet rsFetch = comandoFetch.executeQuery(sqlFetch);

			int i = 0;
			while (rsFetch.next()) {
				Morador m = new Morador();

				m.setCpf(rsFetch.getString("cpf"));
				m.setNome_completo(rsFetch.getString("nome_completo"));
				m.setEmail(rsFetch.getString("email"));
				m.setTelefones(rsFetch.getString("telefones"));
				m.setNumero_apartamento(rsFetch.getInt("numero_apartamento"));
				resultado[i] = m;
				i++;
			}

			rsFetch.close();
			comandoFetch.close();
			con.close();

		} catch (SQLException e) {
			System.out.println("Erro ao buscar moradores: " + e.getMessage());
		}
		return resultado;
	}

	public static Morador atualizar(Morador morador) {
		try {
			Conexao conexao = new Conexao("jdbc:mysql://localhost:3306/a3", "com.mysql.cj.jdbc.Driver", "root", "");
			Connection con = conexao.obterConexao();

			String sql = "UPDATE morador SET nome_completo = ?, email = ?, telefones = ?, numero_apartamento = ? WHERE cpf = ?";

			PreparedStatement comando = con.prepareStatement(sql);
			comando.setString(1, morador.getNome_completo());
			comando.setString(2, morador.getEmail());
			comando.setString(3, morador.getTelefones());
			comando.setInt(4, morador.getNumero_apartamento());
			comando.setString(5, morador.getCpf());

			comando.executeUpdate();

			comando.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar morador: " + e.getMessage());
			return null;
		}
		return morador;
	}

	public static void apagar(String cpf) {
		try {
			Conexao conexao = new Conexao("jdbc:mysql://localhost:3306/a3", "com.mysql.cj.jdbc.Driver", "root", "");
			Connection con = conexao.obterConexao();

			String sql = "DELETE FROM morador WHERE cpf = ?";

			PreparedStatement comando = con.prepareStatement(sql);
			comando.setString(1, cpf);

			comando.executeUpdate();

			comando.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("Erro ao apagar morador: " + e.getMessage());
		}
	}
}