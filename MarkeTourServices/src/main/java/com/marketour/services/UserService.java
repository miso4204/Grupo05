package com.marketour.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.marketour.business.Cliente;
import com.marketour.business.Credenciales;
import com.marketour.business.Proveedor;
import com.marketour.business.Usuario;
import com.marketour.facade.FacadeUsuarios;
import com.marketour.persistence.RepositoryUser;

@Path("/UserService")
@Produces(MediaType.APPLICATION_JSON)
public class UserService {

	@SuppressWarnings("rawtypes")
	private RepositoryUser repository;

	public UserService() {
		repository = new com.marketour.persistence.RepositoryUser();
	}

	@PUT
	@Path("autenticar")
	@Produces(MediaType.APPLICATION_JSON)
	public Response Autenticar(Credenciales credenciales) {
		return Response.status(200).header("Access-Control-Allow-Origin", "*")
				.entity(FacadeUsuarios.Autenticar(credenciales)).build();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response authenticate(Usuario entity) {
		return Response
				.status(200)
				.header("Access-Control-Allow-Origin", "*")
				.entity(repository.authenticate(entity.getLogin(),
						entity.getPassword())).build();
	}

	@PUT
	@Path("cambiarpassword")
	@Produces(MediaType.APPLICATION_JSON)
	public Response Cambiar(Credenciales credenciales) {
		return Response.status(200).header("Access-Control-Allow-Origin", "*")
				.entity(FacadeUsuarios.CambiarContrasena(credenciales)).build();
	}

	@PUT
	@Path("cambiardireccion")
	@Produces(MediaType.APPLICATION_JSON)
	public Response CambiarDireccion(com.marketour.business.Usuario usuario) {
		return Response.status(200).header("Access-Control-Allow-Origin", "*")
				.entity(FacadeUsuarios.CambiarDireccion(usuario)).build();
	}

	@GET
	@Path("delete/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response Delete(@PathParam("id") int id) {
		repository.Delete(id);
		return Response.status(200).header("Access-Control-Allow-Origin", "*")
				.entity(true).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response FindAll() {
		return Response.status(200).header("Access-Control-Allow-Origin", "*")
				.entity(FacadeUsuarios.ConsultarUsuarios()).build();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response FindById(@PathParam("id") int id) {
		return Response.status(200).header("Access-Control-Allow-Origin", "*")
				.entity(FacadeUsuarios.ConsultarUsuario(id)).build();
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response Persist(Usuario entity) {
		return Response.status(200).header("Access-Control-Allow-Origin", "*")
				.entity(FacadeUsuarios.RegistrarUsuario(entity)).build();
	}

	@OPTIONS
	public Response cors(@javax.ws.rs.core.Context HttpHeaders requestHeaders) {
		return Response
				.status(200)
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods",
						"GET, POST, PUT, DELETE, OPTIONS")
				.header("Access-Control-Allow-Headers",
						"AUTHORIZATION, content-type, accept").build();
	}
}
