package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import model.Multa;
import util.Conexao;

public class MultaDAO {

	public static Multa salvar(Multa multa) {
		try {

			Conexao conexao = new Conexao("jdbc:mysql://localhost:3306/a3", "com.mysql.cj.jdbc.Driver", "root", "");
			Connection con = conexao.obterConexao();

			String sql = "INSERT INTO Multa (data_emissao, cpf_morador_infrator, email_funcionario) VALUES (?, ?, ?)";

			PreparedStatement comando = con.prepareStatement(sql);

			comando.setDate(1, Date.valueOf(multa.getData_emissao()));
			comando.setString(2, multa.getCpf_morador_infrator());
			comando.setString(3, multa.getEmail_funcionario());

			comando.executeUpdate();

			comando.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("Erro ao salvar multa: " + e.getMessage());
			return null;
		}
		return multa;
	}

	public static Multa[] buscarTodas() {
		Multa[] resultado = null;
		try {
			Conexao conexao = new Conexao("jdbc:mysql://localhost:3306/a3", "com.mysql.cj.jdbc.Driver", "root", "");
			Connection con = conexao.obterConexao();

		
			Statement comandoCount = con.createStatement();
			String sqlCount = "SELECT COUNT(*) FROM Multa";
			ResultSet rsCount = comandoCount.executeQuery(sqlCount);
			int total = 0;
			if (rsCount.next()) {
				total = rsCount.getInt(1);
			}
			rsCount.close();
			comandoCount.close();

			if (total == 0) {
				con.close();
				return new Multa[0];
			}

			resultado = new Multa[total];

			Statement comandoFetch = con.createStatement();
			String sqlFetch = "SELECT * FROM Multa ORDER BY data_emissao DESC";
			ResultSet rsFetch = comandoFetch.executeQuery(sqlFetch);

			int i = 0;
			while (rsFetch.next()) {
				Multa m = new Multa();
				m.setId_multa(rsFetch.getInt("id_multa"));

				m.setData_emissao(rsFetch.getDate("data_emissao").toLocalDate());
				m.setCpf_morador_infrator(rsFetch.getString("cpf_morador_infrator"));
				m.setEmail_funcionario(rsFetch.getString("email_funcionario"));

				resultado[i] = m;
				i++;
			}

			rsFetch.close();
			comandoFetch.close();
			con.close();

		} catch (SQLException e) {
			System.out.println("Erro ao buscar multas: " + e.getMessage());
		}
		return resultado;
	}

	public static void apagar(int idMulta) {
		try {
			Conexao conexao = new Conexao("jdbc:mysql://localhost:3306/a3", "com.mysql.cj.jdbc.Driver", "root", "");
			Connection con = conexao.obterConexao();

			String sql = "DELETE FROM Multa WHERE id_multa = ?";

			PreparedStatement comando = con.prepareStatement(sql);
			comando.setInt(1, idMulta);

			comando.executeUpdate();

			comando.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("Erro ao apagar multa: " + e.getMessage());
		}
	}
}