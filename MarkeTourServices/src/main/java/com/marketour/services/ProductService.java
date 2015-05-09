package com.marketour.services;

import javax.ws.rs.Consumes;
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

@Path("/ProductService")
@Produces(MediaType.APPLICATION_JSON)
public class ProductService {

	@SuppressWarnings("rawtypes")
	private Repository repository;

	public ProductService() {
		repository = new com.marketour.persistence.Repository<Producto>(
				Producto.class);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response FindAll() {
		return Response.status(200).header("Access-Control-Allow-Origin", "*")
				.entity(FacadeProductos.ConsultarProductoTodos()).build();
	}
	
	@GET
	@Path("FiltrarProductos/{fechaInicio}/{fechaFin}/{precio1}/{precio2}/{idCiudad}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response FiltrarProducto(@PathParam("fechaInicio") String fechaInicio,@PathParam("fechaFin") String fechaFin,@PathParam("precio1") double precio1,@PathParam("precio2") double precio2,@PathParam("idCiudad") int idCiudad) {
		return Response.status(200).header("Access-Control-Allow-Origin", "*")
				.entity(FacadeProductos.FiltrarProducto(fechaInicio, fechaFin, precio1, precio2, idCiudad)).build();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response FindById(@PathParam("id") int id) {
		return Response.status(200).header("Access-Control-Allow-Origin", "*")
				.entity(FacadeProductos.ConsultarProducto(id)).build();
	}

	@GET
	@Path("delete/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response Delete(@PathParam("id") int id) {
		repository.Delete(id);
		return Response.status(200).header("Access-Control-Allow-Origin", "*")
				.entity(true).build();
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response Persist(com.marketour.business.Producto business) {
		return Response.status(200).header("Access-Control-Allow-Origin", "*")
				.entity(FacadeProductos.Persist(business)).build();
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
