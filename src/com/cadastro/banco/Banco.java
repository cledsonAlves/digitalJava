package com.cadastro.banco;

//Classes necessárias para uso de Banco de dados // 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Banco {
	private String serverName = "localhost";
	private String database = "CADASTRO";
	private String url = "jdbc:mysql://" + serverName + "/" + database;
	private String username = "cledson";
	private String password = "Vwmq7&76";
	private String driverName = "com.mysql.jdbc.Driver";

	public Connection getConexao() {
		try {
			// Carregando o JDBC Driver padrão
			Class.forName(driverName);
			Connection connection = DriverManager.getConnection(url, username,
					password);
			System.out.println("Conexão Estabelecida com sucesso!!");
			return connection;
		} catch (ClassNotFoundException e) { // Driver não encontrado
			System.out.println("O driver expecificado nao foi encontrado.");
			return null;
		} catch (SQLException e) {
			System.out.println("Nao foi possivel conectar ao Banco de Dados.");
			return null;
		}

	}
	public static void main(String[] args) {
		new Banco().getConexao();
	}

}