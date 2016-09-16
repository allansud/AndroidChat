package br.com.agf.app;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import br.com.agf.service.implementation.UsuarioServiceImpl;

public class UsuarioApplication extends Application{

	private Set<Object> singletons = new HashSet<Object>();

	public UsuarioApplication() {
		singletons.add(new UsuarioServiceImpl());
	}

	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}
}
