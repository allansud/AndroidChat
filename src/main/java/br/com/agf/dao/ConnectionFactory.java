package br.com.agf.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionFactory {
	
	private static Connection connectionFactory = buildConnection();
	
	private static Connection buildConnection(){
		
		String DATASOURCE_CONTEXT = "java:/MySqlDS";
		Connection conn = null;
		
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup(DATASOURCE_CONTEXT);
			conn = ds.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static Connection getConnectionFactory(){
		if (connectionFactory == null) {
			connectionFactory = buildConnection();
			return connectionFactory;
		}else{
			return connectionFactory;
		}
	}
	
	public static boolean tableExists(String tableName){
		
		boolean response = false;
		
		try {
			String sql = "SHOW TABLES LIKE ?";
			PreparedStatement preparedStatement = getConnectionFactory().prepareStatement(sql);
			preparedStatement.setString(1, tableName);
			ResultSet rs = preparedStatement.executeQuery();
			
			if (rs.getRow() > 0) {
				response = true;
				return response;
			}else{
				return response;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
	
	public static void createTables(){
		
		PreparedStatement statement = null;
		
		try {			
			String sql = "CREATE TABLE Usuario ("
					     + "UsuarioId INT NOT NULL AUTO_INCREMENT," 
					     + "Nome VARCHAR(145) NOT NULL,"
					     + "Email VARCHAR(85) NOT NULL,"
					     + "Senha VARCHAR(85) NOT NULL,"
					     + "PRIMARY KEY (UsuarioId))";	
			statement = getConnectionFactory().prepareStatement(sql);
			statement.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
	}
}
