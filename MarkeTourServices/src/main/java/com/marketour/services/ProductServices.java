package com.marketour.services;

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

@Path("/ProductServices")
@Produces(MediaType.APPLICATION_JSON)
public class ProductServices {

	private RepositoryProduct repository;

	public ProductServices() {
		repository = new com.marketour.persistence.RepositoryProduct();
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
				.entity(FacadeProductos.ConsultarProducto(id)).build();
	}

	@GET
	@Path("package/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response FindByPackage(@PathParam("id") int id) {
		return Response.status(200).header("Access-Control-Allow-Origin", "*")
				.entity(FacadeProductos.ConsultarProductosXPaquetes(id)).build();
	}

	@GET
	@Path("provider/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response FindByProvider(@PathParam("id") int id) {
		return Response.status(200).header("Access-Control-Allow-Origin", "*")
				.entity(FacadeProductos.ConsultarProductosXProveedor(id)).build();
	}

	@GET
	@Path("name/{data}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response FindByName(@PathParam("data") String data) {
		return Response
				.status(200)
				.header("Access-Control-Allow-Origin", "*")
				.entity(FacadeProductos.ConsultarProductoXNombre(data))
				.build();
	}

	@GET
	@Path("category/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response FindByCategory(@PathParam("id") int id) {
		return Response.status(200).header("Access-Control-Allow-Origin", "*")
				.entity(repository.FindByColumn("categoria = " + id)).build();
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
	public Response Persist(Producto entity) {
		return Response.status(200).header("Access-Control-Allow-Origin", "*")
				.entity(repository.Persist(entity)).build();
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
