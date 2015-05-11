package com.marketour.services;

import java.sql.Date;

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
import com.marketour.facade.FacadeCompra;
import com.marketour.facade.FacadeProductos;
import com.marketour.persistence.*;

@Path("/ReporteServices")
@Produces(MediaType.APPLICATION_JSON)
public class ReporteServices {
	@SuppressWarnings("rawtypes")
	private Repository repositoryFactura;

	@SuppressWarnings("rawtypes")
	private Repository repositoryFacturaProducto;

	public ReporteServices() {
		// repositoryFactura = new
		// com.marketour.persistence.Repository<Factura>(Factura.class);
		// repositoryFacturaProducto = new
		// com.marketour.persistence.Repository<FacturaProducto>(
		// FacturaProducto.class);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response FindAll() {
		return Response.status(200).header("Access-Control-Allow-Origin", "*")
				.entity(repositoryFactura.FindAll()).build();
	}

	@GET
	@Path("sales/{fecha}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response FindByPackage(@PathParam("fecha") Date fecha) {
		return Response.status(200).header("Access-Control-Allow-Origin", "*")
				.entity(FacadeCompra.ConsultarCompra(fecha.getMonth(), fecha.getYear()))
				.build();
	}
	@GET
	@Path("salesubication/{idciudad}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response FindByCiudad(@PathParam("idciudad") int idciudad) {
		return Response.status(200).header("Access-Control-Allow-Origin", "*")
				.entity(FacadeCompra.ConsultarCompraXLocalizacion(idciudad))
				.build();
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
