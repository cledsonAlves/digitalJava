package com.cadastro.banco;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.cadastro.logica.Criptografia;
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
		conexao = banco.getConexao();
	}

	// método verifica email
	public boolean buscaRegistro(String email) {
		String sql = "Select * FROM USUARIOS WHERE EMAIL= '" + email + "';";
		conexao = banco.getConexao();
		try {
			stmt = conexao.createStatement();
			result = stmt.executeQuery(sql);
			if (result.next()) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	// método cadastra usuario
	public boolean cadastraUsuario(Usuario usuario) {

		// criptografa a senha e gera o token(c/email) para ativar registro
		String novaSenha = "";
		String token = "";
		try {
			novaSenha = Criptografia.criptografa(usuario.getSenha());
			token = Criptografia.criptografa(usuario.getEmail());
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		conexao = banco.getConexao();
		String sql = "INSERT INTO USUARIOS VALUES ('" + usuario.getEmail()
				+ "','" + usuario.getNome() + "','" + usuario.getSobrenome()
				+ "','" + novaSenha + "','" + token + "','0');";
		try {
			stmt = conexao.createStatement();
			stmt.execute(sql);
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	// Método busca token
	public String buscaToken(String email) {
		String sql = "SELECT TOKEN FROM USUARIOS WHERE EMAIL = '" + email + "'";
		String token = "";
		conexao = banco.getConexao();
		try {
			stmt = conexao.createStatement();
			result = stmt.executeQuery(sql);
			if (result.next()) {
				token = result.getString(1);

			}

		} catch (SQLException e) {
			e.printStackTrace();

		}
		return token;

	}

	// Método ativa registro
	public boolean ativaRegistro(String token) {
		String sql = "UPDATE USUARIOS SET ATIVO = 1 WHERE TOKEN = '" + token
				+ "'";
		
		try {
			stmt = conexao.createStatement();
			stmt.execute(sql);
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;

		}

	}

	// Metodo deleta registro
	public void deletaRegistro(String id) {

	}

}
