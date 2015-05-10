package com.marketour.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.marketour.facade.FacadePromo;

@Path("/SocialNetwork")
@Produces(MediaType.APPLICATION_JSON)
public class SocialNetworkService {
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public Response socialNetwork() {
	    return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(isSocialNetwork()).build();
	}
	
	public static String isSocialNetwork(){

		boolean sn = false;
		boolean facebook = false;
		boolean twitter = false;
		
		try {
			
			String pathConfigFile = System.getProperty("user.dir").replace("MarkeTourServices", "MarkeTourFeatures");
			System.out.println("Path config desde SocialService: " + System.getProperty("user.dir").replace("MarkeTourServices", "MarkeTourFeatures"));
			pathConfigFile = pathConfigFile + File.separator + "configs" + File.separator + "default.config";
			BufferedReader in = new BufferedReader(new FileReader(pathConfigFile));
		
			String line;
			
			while((line = in.readLine()) != null)
			{
				System.out.println(line);
				
				if(line.trim().equalsIgnoreCase("SocialNetwork")){
					System.out.println("TIENE Social Netw!!!!!!!!!!!");
					sn = true;
				}
				if(line.trim().equalsIgnoreCase("Facebook")){
					System.out.println("TIENE Facebook!!!!!!!!!!!");
					facebook = true;
				}
				if(line.trim().equalsIgnoreCase("Twitter")){
					System.out.println("TIENE Twitter!!!!!!!!!!!");
					twitter = true;
				}
			}
		
			in.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(facebook){
			System.out.println("RETORNA FB!!!!!!!!!!!");
			return "facebook";
		} else if(twitter) {
			System.out.println("RETORNA Twitter!!!!!!!!!!!");
			return "twitter";
		}
		
		return null;
	}
	
}
