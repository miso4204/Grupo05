package com.marketour.services;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.marketour.domain.*;
import com.marketour.facade.FacadeProductos;
import com.marketour.persistence.*;

@Path("/PackageServices")
@Produces(MediaType.APPLICATION_JSON)
public class PackageServices {

	@SuppressWarnings("rawtypes")
	private RepositoryPackage repository;

	public PackageServices() {
		repository = new com.marketour.persistence.RepositoryPackage();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response FindAll() {
		return Response.status(200).header("Access-Control-Allow-Origin", "*")
				.entity(FacadeProductos.ConsultarPaquetesTodos()).build();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response FindById(@PathParam("id") int id) {
		return Response.status(200).header("Access-Control-Allow-Origin", "*")
				.entity(repository.FindById(id)).build();
	}

	@GET
	@Path("provider/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response FindByProvider(@PathParam("id") int id) {
		return Response.status(200).header("Access-Control-Allow-Origin", "*")
				.entity(repository.FindByColumn("proveedor = " + id)).build();
	}

	@GET
	@Path("name/{data}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response FindByName(@PathParam("data") String data) {
		return Response
				.status(200)
				.header("Access-Control-Allow-Origin", "*")
				.entity(repository.FindByColumn("nombre LIKE '%" + data + "%'"))
				.build();
	}

	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response Delete(@PathParam("id") int id) {
		repository.Delete(id);
		return Response.status(200).header("Access-Control-Allow-Origin", "*")
				.entity(true).build();
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Response Persist(Paquete entity) {
		return Response.status(200).header("Access-Control-Allow-Origin", "*")
				.entity(repository.Persist(entity)).build();
	}

	@GET
	@Path("{id}/product/{data}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response PersistProduct(@PathParam("id") int id,
			@PathParam("data") int data) {
		return Response.status(200).header("Access-Control-Allow-Origin", "*")
				.entity(repository.PersistProduct(id, data)).build();
	}

	@DELETE
	@Path("{id}/product/{data}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response DeleteProduct(@PathParam("id") int id,
			@PathParam("data") int data) {
		return Response.status(200).header("Access-Control-Allow-Origin", "*")
				.entity(repository.DeleteProduct(id, data)).build();
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
