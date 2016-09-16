package br.com.agf.service.implementation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.agf.dao.ConnectionFactory;
import br.com.agf.domain.Usuario;
import br.com.agf.model.GenericResponse;
import br.com.agf.service.IUsuarioService;

@Path("/usuario")
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioServiceImpl implements IUsuarioService{
	
	private static Map<Long, Usuario> emps = new HashMap<Long, Usuario>();
	
	@Override
	@POST
	@Path("/add")
	public Response addUsuario(Usuario e) {
		
		GenericResponse response = new GenericResponse();
		if(emps.get(e.getId()) != null){
			response.setStatus(false);
			response.setMessage("Usuário já existe");
			response.setErrorCode("EC-01");
			return Response.status(422).entity(response).build();
		}
		emps.put(e.getId(), e);
		response.setStatus(true);
		response.setMessage("Usuário criado com sucesso");
		return Response.ok(response).build();
		
	}

	@Override
	@DELETE
    @Path("/{id}/delete")
	public Response deleteUsuario(@PathParam("id") Long id) {
		GenericResponse response = new GenericResponse();
		if(emps.get(id) == null){
			response.setStatus(false);
			response.setMessage("Usuário não existe");
			response.setErrorCode("EC-02");
			return Response.status(404).entity(response).build();
		}
		emps.remove(id);
		response.setStatus(true);
		response.setMessage("Usuário deletado com sucesso");
		return Response.ok(response).build();
	}

	@Override
	@GET
	@Path("/{id}/get")
	public Usuario getUsuario(Long id) {
		return emps.get(id);
	}
	
	@GET
	@Path("/{id}/getDummy")
	public Usuario getDummyUsuario(@PathParam("id") Long id) {
		if (ConnectionFactory.tableExists("Usuario")) {
			ConnectionFactory.createTables();
		}
		
		Usuario e = new Usuario();
		e.setName("Dummy");
		e.setId(id);
		e.setEmail("algumacoisa@coisa.com.br");
		return e;
	}

	@Override
	public List<Usuario> getAllUsuarios() {
		List<Usuario> users = new ArrayList<Usuario>();
		Set<Long> ids = emps.keySet();
		Usuario[] e = new Usuario[ids.size()];
		int i = 0;
		for(Long id : ids){
			e[i] = emps.get(id);
			i++;
		}
		return users;
	}	
}
