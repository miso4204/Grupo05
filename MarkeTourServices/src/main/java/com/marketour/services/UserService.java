package com.marketour.services;

import javax.ws.rs.DELETE;
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
import com.marketour.domain.*;
import com.marketour.facade.FacadeUsuarios;
import com.marketour.persistence.*;

@Path("/UserService")
@Produces(MediaType.APPLICATION_JSON)
public class UserService {

	@SuppressWarnings("rawtypes")
	private RepositoryUser repository;

	public UserService() {
		repository = new com.marketour.persistence.RepositoryUser();
	}

	@GET
	@Path("cliente/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response FindAll() {
		return Response.status(200).header("Access-Control-Allow-Origin", "*")
				.entity(FacadeUsuarios.ConsultarClientes()).build();
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

	@GET
	@Path("cliente/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response FindById(@PathParam("id") int id) {
		return Response.status(200).header("Access-Control-Allow-Origin", "*")
				.entity(FacadeUsuarios.ConsultarCliente(id)).build();
	}
	@GET
	@Path("proveedor/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response FindByProveedorId(@PathParam("id") int id) {
		return Response.status(200).header("Access-Control-Allow-Origin", "*")
				.entity(FacadeUsuarios.ConsultarProveedor(id)).build();
	}
	

	@GET
	@Path("delete/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response Delete(@PathParam("id") int id) {
		repository.Delete(id);
		return Response.status(200).header("Access-Control-Allow-Origin", "*")
				.entity(true).build();
	}

	/*
	 * @PUT
	 * 
	 * @Produces(MediaType.APPLICATION_JSON) public Response Persist(Usuario
	 * entity) { return
	 * Response.status(200).header("Access-Control-Allow-Origin", "*")
	 * .entity(repository.Persist(entity)).build(); }
	 */
	@POST
	@Path("registrarcliente")
	@Produces(MediaType.APPLICATION_JSON)
	public Response Persist(Cliente entity) {
		return Response.status(200).header("Access-Control-Allow-Origin", "*")
				.entity(FacadeUsuarios.RegistrarCliente(entity)).build();
	}
	@PUT
	@Path("cambiarpassword")
	@Produces(MediaType.APPLICATION_JSON)
	public Response Cambiar(Credenciales credenciales) {
		return Response.status(200).header("Access-Control-Allow-Origin", "*")
				.entity(FacadeUsuarios.CambiarContrasena(credenciales)).build();
	}
	@PUT
	@Path("autenticar")
	@Produces(MediaType.APPLICATION_JSON)
	public Response Autenticar(Credenciales credenciales) {
		return Response.status(200).header("Access-Control-Allow-Origin", "*")
				.entity(FacadeUsuarios.Autenticar(credenciales)).build();
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
