package br.com.agf.service;

import java.util.List;

import javax.ws.rs.core.Response;

import br.com.agf.domain.Usuario;

public interface IUsuarioService {
	
	public Response addUsuario(Usuario e);
	
	public Response deleteUsuario(Long id);
	
	public Usuario getUsuario(Long id);
	
	public List<Usuario> getAllUsuarios();
	
	public Response loginUser(String parametro);	
}
