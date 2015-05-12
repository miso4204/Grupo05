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

@Path("/CustomerService")
@Produces(MediaType.APPLICATION_JSON)
public class CustomerService {

	@SuppressWarnings("rawtypes")
	private RepositoryUser repository;

	public CustomerService() {
		repository = new com.marketour.persistence.RepositoryUser();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response FindClienteById(@PathParam("id") int id) {
		return Response.status(200).header("Access-Control-Allow-Origin", "*")
				.entity(FacadeUsuarios.ConsultarCliente(id)).build();
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response Persist(Cliente entity) {
		return Response.status(200).header("Access-Control-Allow-Origin", "*")
				.entity(FacadeUsuarios.RegistrarCliente(entity)).build();
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
