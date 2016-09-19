package br.com.agf.service.implementation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import br.com.agf.business.implementation.UsuarioBusiness;
import br.com.agf.common.CodeUtil;
import br.com.agf.dao.ConnectionFactory;
import br.com.agf.domain.Usuario;
import br.com.agf.model.GenericResponse;
import br.com.agf.model.Login;
import br.com.agf.service.IUsuarioService;

@Path("/usuario")
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioServiceImpl implements IUsuarioService{
	
	private Gson gson = buildGsonInstance();
	private UsuarioBusiness business;
	
	public UsuarioServiceImpl(){
		business = new UsuarioBusiness();
	}
	
	private static Map<Long, Usuario> emps = new HashMap<Long, Usuario>();
	
	@Override
	@GET
	@Path("/add")
	public Response addUsuario(@QueryParam("parametro") String parametro) {
		
		String info = parametro != null ? CodeUtil.getStringDecoded(parametro) : "";
		Usuario usuario = gson.fromJson(info, Usuario.class);
		GenericResponse response = new GenericResponse();
		
		if(usuario != null){
			business.addUser(usuario);
			response.setStatus(true);
			response.setMessage(parametro);
			return Response.ok(response).build();				
		}
		
		response.setStatus(false);
		response.setMessage(CodeUtil.getStringCoded("Erro ao criar usuário."));
		response.setErrorCode("EC-01");
		return Response.status(422).entity(response).build();	
	}
	
	@Override
	@GET
	@Path("/go")
	public Response loginUser(@QueryParam("parametro") String parametro){
		
		GenericResponse genericResponse = new GenericResponse();
		try {
			
			String infoDecrypted = CodeUtil.getStringDecoded(parametro);
			Gson gson = new Gson();
			Login login = gson.fromJson(infoDecrypted, Login.class);
			Usuario usuario = business.login(login);
			
			if (usuario != null) {				
				return Response.ok(CodeUtil.getStringCoded(gson.toJson(usuario))).build();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		genericResponse.setStatus(false);
		genericResponse.setMessage(CodeUtil.getStringCoded("Falha no login"));
		genericResponse.setErrorCode("EC-01");
		return Response.status(401).entity(genericResponse).build();
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
		if (!ConnectionFactory.tableExists("Usuario")) {
			ConnectionFactory.createTables();
		}
		
		Usuario e = new Usuario();
		e.setName("Dummy");
		e.setId(id);
		e.setEmail("algumacoisa@coisa.com.br");
		return e;
	}
	
	@Override
	@GET
	@Path("/users")
	public List<Usuario> getAllUsuarios() {
		List<Usuario> users = business.listAllUsers();		
		return users;
	}
	
	private Gson buildGsonInstance(){
		if (gson == null) {
			gson = new Gson();
			return gson;
		}else{
			return gson;
		} 
	}
}
