package br.com.agf.business.implementation;

import java.util.List;

import br.com.agf.business.IUsuarioBusinbess;
import br.com.agf.dao.UsuarioDAO;
import br.com.agf.domain.Usuario;
import br.com.agf.model.Login;

public class UsuarioBusiness implements IUsuarioBusinbess{
	
	private UsuarioDAO dao;
	
	public UsuarioBusiness() {
		dao = new UsuarioDAO();
	}
	
	@Override
	public List<Usuario> listAllUsers() {
		List<Usuario> lista = dao.getAllUsers();	
		return lista;
	}

	@Override
	public void deleteUser(Usuario usuario) {
		dao.deleteUser(usuario);
	}

	@Override
	public void updateuser(Usuario usuario) {
		dao.updateUser(usuario);
		
	}

	@Override
	public Usuario getByEmail(String email) {
		return dao.getSpecificUser(email);
	}

	@Override
	public void addUser(Usuario usuario) {
		dao.saveUser(usuario);		
	}

	@Override
	public Usuario login(Login login) {		
		return dao.loginUser(login);
	}
}
