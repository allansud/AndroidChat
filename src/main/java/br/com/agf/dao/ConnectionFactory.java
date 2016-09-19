package br.com.agf.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
			Context initCtx = new InitialContext();
			DataSource ds = (DataSource) initCtx.lookup(DATASOURCE_CONTEXT);
			conn = ds.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
//	private static Connection buildConnectionFactoryByDriver(){
//		
//		String DB_CONN_STRING = "jdbc:mysql://192.168.0.104:3306/mysqldbapps";
//	    String DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
//	    String USER_NAME = "****";
//	    String PASSWORD = "****";
//	    
//	    Connection result = null;
//	    
//	    try {
//	    	Class.forName(DRIVER_CLASS_NAME).newInstance();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	    
//	    try {
//	    	result = DriverManager.getConnection(DB_CONN_STRING, USER_NAME, PASSWORD);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	    
//	    return result;
//	}
	
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
			ConnectionFactory.getConnectionFactory().setAutoCommit(false);
			
			String sql = "CREATE TABLE Usuario ("
					     + "UsuarioId INT NOT NULL AUTO_INCREMENT," 
					     + "Nome VARCHAR(145) NOT NULL,"
					     + "Email VARCHAR(85) NOT NULL,"
					     + "Senha VARCHAR(85) NOT NULL,"
					     + "Deletado BIT(1) NOT NULL,"
					     + "PRIMARY KEY (UsuarioId))";	
			
			statement = getConnectionFactory().prepareStatement(sql);
			statement.executeUpdate(sql);
			ConnectionFactory.getConnectionFactory().commit();
			
		} catch (Exception e) {
			if (ConnectionFactory.getConnectionFactory() != null) {
				try {
					ConnectionFactory.getConnectionFactory().rollback();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			e.printStackTrace();
		}finally {
			try {
				statement.close();
				ConnectionFactory.getConnectionFactory().setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
	}
}
