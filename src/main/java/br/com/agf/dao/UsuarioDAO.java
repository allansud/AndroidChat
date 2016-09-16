package br.com.agf.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.agf.domain.Usuario;

public class UsuarioDAO {	
	
	private static ResultSet rs = null;
	private static PreparedStatement p = null;
	
	public UsuarioDAO() { }
	
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
