package br.com.agf.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.agf.domain.Usuario;
import br.com.agf.model.Login;

public class UsuarioDAO {	
	
	private static ResultSet rs = null;
	private static PreparedStatement p = null;
	
	public UsuarioDAO() { }
	
	public void saveUser(Usuario usuario){
				
		try {
			ConnectionFactory.getConnectionFactory().setAutoCommit(false);
			String sql = "INSERT INTO Usuario(Nome, Email, Senha) VALUES (?, ?, ?)";
			p = ConnectionFactory.getConnectionFactory().prepareStatement(sql);
			
			p.setString(1, usuario.getName());
			p.setString(2, usuario.getEmail());
			p.setString(3, usuario.getSenha());
			
			p.executeUpdate();
			
			ConnectionFactory.getConnectionFactory().commit();
			
		} catch (Exception e) {
			if (ConnectionFactory.getConnectionFactory() != null) {
				try {
					ConnectionFactory.getConnectionFactory().rollback();
				} catch (SQLException ex) {
					e.printStackTrace();
				}
				e.printStackTrace();
			}
		}finally {
			dispose();
			try {
				ConnectionFactory.getConnectionFactory().setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void updateUser(Usuario usuario){
		try {
			
			ConnectionFactory.getConnectionFactory().setAutoCommit(false);
			String sql = "UPDATE Usuario SET Nome = ?, Email = ?, Senha = ? WHERE UsuarioId = ?";
			p = ConnectionFactory.getConnectionFactory().prepareStatement(sql);

			p.setString(1, usuario.getName());
			p.setString(2, usuario.getEmail());
			p.setString(3, usuario.getSenha());
			p.setLong(4, usuario.getId());
			
			p.executeUpdate();
			
			ConnectionFactory.getConnectionFactory().commit();
			
		} catch (Exception e) {
			if (ConnectionFactory.getConnectionFactory() != null) {
				try {
					ConnectionFactory.getConnectionFactory().rollback();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
				e.printStackTrace();
			}
		}finally {
			dispose();
			try {
				ConnectionFactory.getConnectionFactory().setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void deleteUser(Usuario usuario){
		try {
			
			ConnectionFactory.getConnectionFactory().setAutoCommit(false);
			String sql = "UPDATE Usuario SET Deletado = ? WHERE UsuarioId = ?";
			
			p = ConnectionFactory.getConnectionFactory().prepareStatement(sql);
			p.setBoolean(1, true);
			p.setLong(2, usuario.getId());
			
			p.executeUpdate();
			
			ConnectionFactory.getConnectionFactory().commit();
			
		} catch (Exception e) {
			if (ConnectionFactory.getConnectionFactory() != null) {
				try {
					ConnectionFactory.getConnectionFactory().rollback();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
				e.printStackTrace();
			}
		}finally {
			dispose();
			try {
				ConnectionFactory.getConnectionFactory().setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public Usuario getSpecificUser(String email){
		
		Usuario u = new Usuario();
		try {
			String sql = "SELECT * FROM Usuario WHERE Email = ?";
			p = ConnectionFactory.getConnectionFactory().prepareStatement(sql);
			p.setString(1, email);
			rs = p.executeQuery();
			
			while(rs.next()){
				u.setName(rs.getString("Nome"));
				u.setEmail(rs.getString("Email"));
				u.setSenha(rs.getString("Senha"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return u;
	}
	
	public Usuario loginUser(Login login){
		
		Usuario u = new Usuario();
		try {
			String sql = "SELECT * FROM Usuario WHERE Email = ? AND Senha = ?";
			p = ConnectionFactory.getConnectionFactory().prepareStatement(sql);
			p.setString(1, login.getLogin());
			p.setString(2, login.getSenha());
			rs = p.executeQuery();
			
			while(rs.next()){
				u.setName(rs.getString("Nome"));
				u.setEmail(rs.getString("Email"));
				u.setSenha(rs.getString("Senha"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return u;
	}
	
	public List<Usuario> getAllUsers(){
		
		List<Usuario> lista = new ArrayList<>();
		
		try {
			String sql = "SELECT * FROM Usuario";
			p = ConnectionFactory.getConnectionFactory().prepareStatement(sql);
			rs = p.executeQuery();
			
			while (rs.next()){
				Usuario u = new Usuario();
				u.setId(rs.getLong("UsuarioId"));
				u.setName(rs.getString("Nome"));
				u.setEmail(rs.getString("Email"));			
				u.setSenha(rs.getString("Senha"));
				lista.add(u);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dispose();
		}
		
		return lista;
	}
	
	private void dispose(){
		try {
			if (p != null) {
				p.close();
			}
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	
}
