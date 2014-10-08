package com.cadastro.banco;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.cadastro.logica.Criptografia;
import com.cadastro.objetos.Usuario;

/**
 * Classe respons�vel pela manipula��o direta no banco de dados contendo
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

	// m�todo verifica email
	public boolean buscaRegistro(String email) {
		String sql = "Select * FROM USUARIOS WHERE EMAIL= '" + email + "';";
		// abre conex�o com banco
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
		} finally {
			fechaConexao(conexao, stmt, result);
		}

	}

	// m�todo cadastra usuario
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
		// abre conex�o com banco
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
		} finally {
			fechaConexao(conexao, stmt);
		}

	}

	// M�todo busca token
	public String buscaToken(String email) {
		String sql = "SELECT TOKEN FROM USUARIOS WHERE EMAIL = '" + email + "'";
		String token = "";
		// abre conex�o com banco
		conexao = banco.getConexao();

		try {
			stmt = conexao.createStatement();
			result = stmt.executeQuery(sql);
			if (result.next()) {
				token = result.getString(1);

			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			fechaConexao(conexao, stmt, result);
		}
		return token;

	}

	// M�todo ativa registro
	public boolean ativaRegistro(String token) {
		String sql = "UPDATE USUARIOS SET ATIVO = 1 WHERE TOKEN = '" + token
				+ "'";
		// abre conex�o com banco
		conexao = banco.getConexao();

		if (consultaRegistro(token, "0")) {
			System.out.println("ativado");
			try {
				stmt = conexao.createStatement();
				stmt.execute(sql);
				return true;
			} catch (SQLException e) {
				e.printStackTrace();

			} finally {
				fechaConexao(conexao, stmt);
			}

		} else {
			System.out.println("n�o ativou");
			return false;
		}
		return false;

	}

	// m�todo verifica se o registro esta ativo / desativo
	private boolean consultaRegistro(String token, String ativo) {

		String sql = "Select * FROM USUARIOS WHERE token = '" + token
				+ "' AND ATIVO = '" + ativo + "';";
		// abre conex�o com banco
		conexao = banco.getConexao();

		try {
			stmt = conexao.createStatement();
			result = stmt.executeQuery(sql);
			if (result.next()) {
				System.out.println("0");
				return true;
			} else {
				System.out.println("1");
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			fechaConexao(conexao, stmt, result);
		}

	}

	// Consulta usuario
	public Usuario consultaUsuario(String token) {
		String sql = "SELECT * FROM USUARIOS WHERE token = '" + token + "';";
		Usuario usuario = new Usuario();

		// abre conex�o com banco
		conexao = banco.getConexao();

		try {
			stmt = conexao.createStatement();
			result = stmt.executeQuery(sql);
			result.next();
			{
				usuario.setEmail(result.getString(1));
				usuario.setNome(result.getString(2));
				usuario.setSobrenome(result.getString(3));
				usuario.setSenha(result.getString(4));
				usuario.setToken(result.getString(5));
				usuario.setAtivo(result.getString(6));
			}
			return usuario;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;

		} finally {
			fechaConexao(conexao, stmt, result);
		}

	}

	// Metodo deleta registro
	public boolean deletaRegistro(String token) {
		String sql = "DELETE  FROM USUARIOS WHERE token = '" + token + "';";
		// abre conex�o com banco
		conexao = banco.getConexao();

		try {
			stmt = conexao.createStatement();
			stmt.execute(sql);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			fechaConexao(conexao, stmt);
		}

	}

	// m�todo fecha as conex�es
	public void fechaConexao(Connection conexao, Statement st, ResultSet rs) {
		try {
			if (conexao != null || st != null) {
				st.close();
				conexao.close();

			}
			if (rs != null) {
				rs.close();
			}
			System.out.println("Conex�es fechadas");

		} catch (Exception e) {
			System.out.println("N�o foi possivel fechar as conex�es de banco");
		}

	}

	// m�todo fecha as conex�es
	public void fechaConexao(Connection conexao, Statement st) {
		try {
			if (conexao != null || st != null) {
				st.close();
				conexao.close();
				System.out.println("Conex�oes fechadas");
			}

		} catch (Exception e) {
			System.out.println("N�o foi possivel fechar as conex�es de banco");
		}

	}

}
