package com.cadastro.banco;

/**
 * Para ultizar outro banco, somente precisa modificar a url  e colocar o drive.jar na pasta web-inf/lib 
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Banco {
	private String serverName = "localhost";
	private String database = "cadastro";
	private String url = "jdbc:mysql://" + serverName + "/" + database;
	private String username = "root";
	private String password = "";
	private String driverName = "com.mysql.jdbc.Driver";
	//private String password = "Vwmq7&76";

	/*
	 * Método getConexao()
	 * Responsável por controlar a conexão ao banco de dadoos
	 */
	public Connection getConexao() {
		try {
			// Carregando o JDBC Driver padrão
			Class.forName(driverName);
			Connection connection = DriverManager.getConnection(url, username,
					password);
			// System.out.println("Conexão Estabelecida com sucesso!!");
			return connection;
		} catch (ClassNotFoundException e) { // Driver não encontrado
			System.out.println("O driver expecificado nao foi encontrado.");
			return null;
		} catch (SQLException e) {

			System.out.println("Nao foi possivel conectar ao Banco de Dados.");
			return null;
		}

	}

}