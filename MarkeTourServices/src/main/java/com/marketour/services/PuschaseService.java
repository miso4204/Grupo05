package com.marketour.services;

import java.util.Date;

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

import com.marketour.domain.*;
import com.marketour.domain.Compra;
import com.marketour.facade.FacadeCompra;
import com.marketour.persistence.*;
import com.marketour.business.*;

@Path("/PuschaseService")
@Produces(MediaType.APPLICATION_JSON)
public class PuschaseService {

	@SuppressWarnings("rawtypes")
	private Repository repository;

	public PuschaseService() {
		repository = new com.marketour.persistence.Repository<Compra>(
				Compra.class);
	}

	@GET
	@Path("FormasPago")
	@Produces(MediaType.APPLICATION_JSON)
	public Response ConsultarFormasdePago() {
		return Response.status(200).header("Access-Control-Allow-Origin", "*")
				.entity(FacadeCompra.ConsultarFormasdePago()).build();
	}
	
	@GET
	@Path("MedioPagoCliente/{id}/{idForma}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response ConsultarMedioPagoXCliente(@PathParam("id") int id,@PathParam("idForma") int idForma) {
		return Response.status(200).header("Access-Control-Allow-Origin", "*")
				.entity(FacadeCompra.ConsultarMedioPagoXCliente(id,idForma)).build();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response FindById(@PathParam("id") int id) {
		return Response.status(200).header("Access-Control-Allow-Origin", "*")
				.entity(repository.FindById(id)).build();
	}
	@GET
	@Path("compraubicacion/{idubicacion}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response FindByUbicacion(@PathParam("idubicacion") int idubicacion) {
		return Response.status(200).header("Access-Control-Allow-Origin", "*")
				.entity(FacadeCompra.ConsultarCompraXLocalizacion(idubicacion)).build();
	}
	@GET
	@Path("compraperiodo/{fecha}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response FindByUbicacion(@PathParam("fecha") Date fecha) {
		return Response.status(200).header("Access-Control-Allow-Origin", "*")
				.entity(FacadeCompra.ConsultarCompra(fecha.getMonth(), fecha.getYear())).build();
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
	public Response Persist(com.marketour.business.Compra entity) {
		return Response.status(200).header("Access-Control-Allow-Origin", "*")
				.entity(FacadeCompra.RegistrarCompra(entity)).build();
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