package br.com.agf.business;

import java.util.List;

import br.com.agf.domain.Usuario;
import br.com.agf.model.Login;

public interface IUsuarioBusinbess {
	
	public List<Usuario> listAllUsers();
	
	public void deleteUser(Usuario usuario);
	
	public void updateuser(Usuario usuario);
	
	public Usuario getByEmail(String email);
	
	public void addUser(Usuario usuario);
	
	public Usuario login(Login login);
}
