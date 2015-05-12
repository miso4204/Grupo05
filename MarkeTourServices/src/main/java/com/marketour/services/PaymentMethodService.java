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
import com.marketour.facade.FacadeMedioPago;
import com.marketour.persistence.*;

@Path("/PaymentMethodService")
@Produces(MediaType.APPLICATION_JSON)
public class PaymentMethodService {

	@SuppressWarnings("rawtypes")
	private Repository repository;

	public PaymentMethodService() {
		repository = new com.marketour.persistence.Repository<MedioPago>(
				MedioPago.class);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response FindAll() {
		return Response.status(200).header("Access-Control-Allow-Origin", "*")
				.entity(repository.FindAll()).build();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response FindById(@PathParam("id") int id) {
		return Response.status(200).header("Access-Control-Allow-Origin", "*")
				.entity(repository.FindById(id)).build();
	}
	
	@GET
	@Path("MedioCliente/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response FindByIdCliente(@PathParam("id") int id) {
		return Response.status(200).header("Access-Control-Allow-Origin", "*")
				.entity(FacadeMedioPago.ConsultarMediodePagoXCliente(id)).build();
	}
	@GET
	@Path("MedioClienteForma/{idcliente}/{idforma}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response FindByIdCliente(@PathParam("idcliente") int idcliente,@PathParam("idforma") int idforma) {
		return Response.status(200).header("Access-Control-Allow-Origin", "*")
				.entity(FacadeMedioPago.ConsultarMedioPagoXClienteForma(idcliente, idforma)).build();
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
	public Response Persist(com.marketour.business.MedioPago entity) {
		return Response.status(200).header("Access-Control-Allow-Origin", "*")
				.entity(FacadeMedioPago.CrearMediodePago(entity)).build();
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