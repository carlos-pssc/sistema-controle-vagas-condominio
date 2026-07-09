package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.Funcionario;
import util.Conexao;

public class FuncionarioDAO {

	public static Funcionario salvar(Funcionario func) {
		try {

			Conexao conexao = new Conexao("jdbc:mysql://localhost:3306/a3", "com.mysql.cj.jdbc.Driver", "root", "");
			Connection con = conexao.obterConexao();

			String sql = "INSERT INTO funcionario (email, nome_completo, senha) VALUES (?, ?, ?)";

			PreparedStatement comando = con.prepareStatement(sql);

			comando.setString(1, func.getEmail());
			comando.setString(2, func.getNome_completo());
			comando.setString(3, func.getSenha());

			comando.executeUpdate();

			comando.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("Erro ao salvar funcionário: " + e.getMessage());
			return null;
		}
		return func;
	}

	public static Funcionario buscarPorLogin(String email, String senha) {
		Funcionario func = null;
		try {
			Conexao conexao = new Conexao("jdbc:mysql://localhost:3306/a3", "com.mysql.cj.jdbc.Driver", "root", "");
			Connection con = conexao.obterConexao();

			String sql = "SELECT * FROM funcionario WHERE email = ? AND senha = ?";

			PreparedStatement comando = con.prepareStatement(sql);
			comando.setString(1, email);
			comando.setString(2, senha);

			ResultSet rs = comando.executeQuery();

			if (rs.next()) {
				func = new Funcionario();

				func.setEmail(rs.getString("email"));
				func.setNome_completo(rs.getString("nome_completo"));
				func.setSenha(rs.getString("senha"));
			}

			rs.close();
			comando.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("Erro ao autenticar funcionário: " + e.getMessage());
		}
		return func;
	}

	public static Funcionario[] buscarTodos() {
		Funcionario[] resultado = null;
		try {
			Conexao conexao = new Conexao("jdbc:mysql://localhost:3306/a3", "com.mysql.cj.jdbc.Driver", "root", "");
			Connection con = conexao.obterConexao();

			Statement comandoCount = con.createStatement();
			String sqlCount = "SELECT COUNT(*) FROM funcionario";
			ResultSet rsCount = comandoCount.executeQuery(sqlCount);
			int total = 0;
			if (rsCount.next())
				total = rsCount.getInt(1);
			rsCount.close();
			comandoCount.close();

			if (total == 0) {
				con.close();
				return new Funcionario[0];
			}

			resultado = new Funcionario[total];

			Statement comandoFetch = con.createStatement();
			String sqlFetch = "SELECT * FROM funcionario ORDER BY nome_completo";
			ResultSet rsFetch = comandoFetch.executeQuery(sqlFetch);

			int i = 0;
			while (rsFetch.next()) {
				Funcionario f = new Funcionario();
				f.setEmail(rsFetch.getString("email"));
				f.setNome_completo(rsFetch.getString("nome_completo"));
				f.setSenha(rsFetch.getString("senha"));
				resultado[i] = f;
				i++;
			}

			rsFetch.close();
			comandoFetch.close();
			con.close();

		} catch (SQLException e) {
			System.out.println("Erro ao buscar funcionários: " + e.getMessage());
		}
		return resultado;
	}
}