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

import com.marketour.domain.Paquete;
import com.marketour.domain.Producto;
import com.marketour.domain.Promocion;
import com.marketour.facade.FacadePackage;
import com.marketour.facade.FacadeProductos;
import com.marketour.facade.FacadePromo;
import com.marketour.persistence.Repository;
import com.marketour.persistence.RepositoryPackage;

@Path("/PromoServices")
@Produces(MediaType.APPLICATION_JSON)
public class PromoService {

	@SuppressWarnings("rawtypes")
	private Repository repository;

	public PromoService() {
		repository = new com.marketour.persistence.Repository<Promocion>(
				Promocion.class);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response FindAll() {
		return Response.status(200).header("Access-Control-Allow-Origin", "*")
				.entity(FacadePromo.ConsultarPromosTodas()).build();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response FindById(@PathParam("id") int id) {
		return Response.status(200).header("Access-Control-Allow-Origin", "*")
				.entity(FacadePromo.ConsultarPromocion(id)).build();
	}
	
	/*
	@GET
	@Path("fecha/{fecha}/paquete/{idPaquete}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response FindByFechaAndPaquete(@PathParam("fecha") int fecha, @PathParam("idPaquete") int idPaquete) {
		Paquete paquete = com.marketour.business.Paquete.ConvertToDBPaquete(FacadePackage.ConsultarPaquete(idPaquete));
		return Response.status(200).header("Access-Control-Allow-Origin", "*")
				.entity(FacadePromo.ConsultarPromocion(id)).build();
	} /*
	
	/*
	@GET
	@Path("codigo/{data}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response FindByName(@PathParam("data") String data) {
		return Response
				.status(200)
				.header("Access-Control-Allow-Origin", "*")
				.entity(FacadePromo.ConsultarPromoXNombre(data))
				.build();
	} */
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Response Persist(Promocion entity) {
		System.out.println("======== Promo persist: " + entity.getDescripcion());
		return Response.status(200).header("Access-Control-Allow-Origin", "*")
				.entity(repository.Persist(entity)).build();
	}
	
	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response Delete(@PathParam("id") int id) {
		System.out.println("DELETE: " + id);
		repository.Delete(id);
		return Response.status(200).header("Access-Control-Allow-Origin", "*")
				.entity(true).build();
	}
}
