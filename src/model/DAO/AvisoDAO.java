package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import model.Aviso;
import util.Conexao;

public class AvisoDAO {

	public static Aviso salvar(Aviso aviso) {
		try {

			Conexao conexao = new Conexao("jdbc:mysql://localhost:3306/a3", "com.mysql.cj.jdbc.Driver", "root", "");
			Connection con = conexao.obterConexao();

			String sql = "INSERT INTO Aviso (data_emissao, cpf_morador_infrator, email_funcionario) VALUES (?, ?, ?)";

			PreparedStatement comando = con.prepareStatement(sql);
			comando.setDate(1, Date.valueOf(aviso.getData_emissao()));
			comando.setString(2, aviso.getCpf_morador_infrator());
			comando.setString(3, aviso.getEmail_funcionario());

			comando.executeUpdate();

			comando.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("Erro ao salvar aviso: " + e.getMessage());
			return null;
		}
		return aviso;
	}

	public static Aviso[] buscarTodos() {
		Aviso[] resultado = null;
		try {
			Conexao conexao = new Conexao("jdbc:mysql://localhost:3306/a3", "com.mysql.cj.jdbc.Driver", "root", "");
			Connection con = conexao.obterConexao();

			Statement comandoCount = con.createStatement();
			String sqlCount = "SELECT COUNT(*) FROM Aviso";
			ResultSet rsCount = comandoCount.executeQuery(sqlCount);
			int total = 0;
			if (rsCount.next())
				total = rsCount.getInt(1);
			rsCount.close();
			comandoCount.close();

			if (total == 0) {
				con.close();
				return new Aviso[0];
			}

			resultado = new Aviso[total];

			Statement comandoFetch = con.createStatement();
			String sqlFetch = "SELECT * FROM Aviso ORDER BY data_emissao DESC";
			ResultSet rsFetch = comandoFetch.executeQuery(sqlFetch);

			int i = 0;
			while (rsFetch.next()) {
				Aviso a = new Aviso();
				a.setId_aviso(rsFetch.getInt("id_aviso"));
				a.setData_emissao(rsFetch.getDate("data_emissao").toLocalDate());
				a.setCpf_morador_infrator(rsFetch.getString("cpf_morador_infrator"));
				a.setEmail_funcionario(rsFetch.getString("email_funcionario"));
				resultado[i] = a;
				i++;
			}

			rsFetch.close();
			comandoFetch.close();
			con.close();

		} catch (SQLException e) {
			System.out.println("Erro ao buscar avisos: " + e.getMessage());
		}
		return resultado;
	}

	public static int contarAvisosPorCpf(String cpf) {
		int total = 0;
		try {
			Conexao conexao = new Conexao("jdbc:mysql://localhost:3306/a3", "com.mysql.cj.jdbc.Driver", "root", "");
			Connection con = conexao.obterConexao();

			String sql = "SELECT COUNT(*) FROM Aviso WHERE cpf_morador_infrator = ?";

			PreparedStatement comando = con.prepareStatement(sql);
			comando.setString(1, cpf);
			ResultSet rs = comando.executeQuery();

			if (rs.next()) {
				total = rs.getInt(1);
			}

			rs.close();
			comando.close();
			con.close();

		} catch (SQLException e) {
			System.out.println("Erro ao contar avisos: " + e.getMessage());
		}
		return total;
	}
}