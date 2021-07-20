package br.edu.ifpe.jeffersonsilva.gestaoprofessor.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.edu.ifpe.jeffersonsilva.gestaoprofessor.model.Professor;

@Repository
public class ProfessorDAO {

	public static void adiciona(Professor professor) throws ClassNotFoundException, SQLException {
		Connection connection = ConexaoMySQL.getConexaoMySQL();
		String sql = "INSERT INTO `professor`" + "( `nome`, `endereco`, `cidade`, `uf`)"
				+ "VALUES ( ? , ? , ? , ? )";

		PreparedStatement stmt = connection.prepareStatement(sql);

//		stmt.setInt(1, professor.getCod_matricula());
		stmt.setString(1, professor.getNome());
		stmt.setString(2, professor.getEndereco());
		stmt.setString(3, professor.getCidade());
		stmt.setString(4, professor.getUf());

		stmt.execute();
		stmt.close();
		connection.close();

	}

	public List<Professor> consultarTodosProfessores() throws ClassNotFoundException, SQLException {

		Connection connection = ConexaoMySQL.getConexaoMySQL();
		String sql = "SELECT `cod_matricula`, `nome`, `endereco`, `cidade`, `uf` FROM `professor`";
		
		PreparedStatement stmt = connection.prepareStatement(sql);
		ResultSet resultSet = stmt.executeQuery();

		List<Professor> listaTodosProfessores = new ArrayList<Professor>();
		while (resultSet.next()) {

			Professor professor = new Professor();
			professor.setCod_matricula(resultSet.getInt(1));
			professor.setNome(resultSet.getString(2));
			professor.setEndereco(resultSet.getString(3));
			professor.setCidade(resultSet.getString(4));
			professor.setUf(resultSet.getString(5));

			listaTodosProfessores.add(professor);
		}
		return listaTodosProfessores;

	}

	public static Professor consultarProfessores(int cod_matricula) throws ClassNotFoundException, SQLException {

		Connection connection = ConexaoMySQL.getConexaoMySQL();
		String sql = "SELECT * FROM `professor` WHERE cod_matricula = ?";
		
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setInt(1, cod_matricula);
		ResultSet resultSet = stmt.executeQuery();

		Professor professor = new Professor();

		if (resultSet.next()) {

			professor.setCod_matricula(resultSet.getInt(1));
			professor.setNome(resultSet.getString(2));
			professor.setEndereco(resultSet.getString(3));
			professor.setCidade(resultSet.getString(4));
			professor.setUf(resultSet.getString(5));
		}

		return professor;

	}

	public static void alterarProfessores(Professor professor) throws ClassNotFoundException, SQLException {

		Connection connection = ConexaoMySQL.getConexaoMySQL();
		String sql = "UPDATE `professor` SET 'nome' = ?, 'endereco' = ?, 'cidade' = ? 'uf' = ? WHERE cod_matricula = ?";

		PreparedStatement stmt = connection.prepareStatement(sql);

		stmt.setInt(1, professor.getCod_matricula());
		stmt.setString(2, professor.getNome());
		stmt.setString(3, professor.getEndereco());
		stmt.setString(4, professor.getCidade());
		stmt.setString(5, professor.getUf());

		stmt.executeUpdate();
		stmt.close();
		connection.close();

	}
	public static void deletarProfessores(int cod_matricula) throws ClassNotFoundException, SQLException {

		Connection connection = ConexaoMySQL.getConexaoMySQL();
		String sql = "DELETE FROM `professor` WHERE cod_matricula = ?";
		
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setInt(1, cod_matricula);

		
		stmt.executeUpdate();
		stmt.close();
		connection.close();

	}
}