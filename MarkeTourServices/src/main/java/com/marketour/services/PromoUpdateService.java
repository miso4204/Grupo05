package com.marketour.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.marketour.facade.FacadePromo;

@Path("/PromoUpdate")
@Produces(MediaType.APPLICATION_JSON)
public class PromoUpdateService {

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response Persist(com.marketour.business.Promocion business) {
		return Response.status(200).header("Access-Control-Allow-Origin", "*")
				.entity(FacadePromo.Update(business)).build();
	}
	
}
