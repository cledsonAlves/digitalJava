package com.cadastro.banco;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.cadastro.objetos.Usuario;

/**
 * Classe responsável pela manipulação direta no banco de dados contendo
 * (insert, delete,update, drop, etc..)
 */

public class ManipulaBanco {
	private Banco banco;
	private ResultSet result;
	private Statement stmt;
	private Connection conexao;

	public ManipulaBanco() {
		banco = new Banco();
	}

	// método cadastra usuario
	public boolean cadastraUsuario(Usuario usuario) {
		 
		conexao = banco.getConexao();
		String sql = "INSERT INTO USUARIO VALUES ('" + usuario.getEmail()
				+ "','" + usuario.getNome() + "','" + usuario.getSobrenome()
				+ "','" + usuario.getSenha() + "');";
		try {
			stmt = conexao.createStatement();
			stmt.execute(sql);

	        conexao.commit();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	// método teste interno
	public void consulta() {
		 banco = new Banco();
		String sql = "SELECT * FROM USUARIOS";
		conexao = banco.getConexao();
		try {

			stmt = conexao.createStatement();
			result = stmt.executeQuery(sql);
			while (result.next()) {
				System.out.println(result.getString(1) + " "
						+ result.getString(2));
			}
			// fechando a conexão
			stmt.close();
			result.close();
			conexao.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
