package com.marketour.facade;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.marketour.business.Paquete;
import com.marketour.business.Producto;
import com.marketour.persistence.Repository;
import com.marketour.persistence.RepositoryProduct;

public class FacadePackage {

	public static boolean isPromo(){

		boolean promo = false;
		
		try {
			
			String pathConfigFile = System.getProperty("user.dir").replace("MarkeTourServices", "MarkeTourFeatures");
			System.out.println("Path config desde facade: " + System.getProperty("user.dir").replace("MarkeTourServices", "MarkeTourFeatures"));
			pathConfigFile = pathConfigFile + File.separator + "configs" + File.separator + "default.config";
			BufferedReader in = new BufferedReader(new FileReader(pathConfigFile));
		
			String line;
			
			while((line = in.readLine()) != null)
			{
				System.out.println(line);
				
				if(line.trim().equalsIgnoreCase("SpecialOffers")){
					System.out.println("TIENE PROMO!!!!!!!!!!!");
					promo = true;
				}
			}
		
			in.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(promo){
			System.out.println("RETORNA PROMO!!!!!!!!!!!");
			return true;
		} else {
			System.out.println("RETORNA NULL!!!!!!!!!!!");
			return false;
		}
	}
	
	public static Paquete ConsultarPaquete(int id) {
		Repository<com.marketour.domain.Paquete> repository = new Repository<com.marketour.domain.Paquete>(
				com.marketour.domain.Paquete.class);
		Paquete business = new Paquete();
		com.marketour.domain.Paquete domain = repository.FindById(id);
		if (domain != null)
			business = Paquete.ConvertToBPaquete(domain);
		if(isPromo()){
			return business;
		} else {
			business.setPromocion(null);
			return business;
		}
	}

	public static List<Paquete> ConsultarPaquetesTodos() {
		List<Paquete> lstPaquete = new ArrayList<Paquete>();
		List<com.marketour.domain.Paquete> lstdPaquete = new ArrayList<com.marketour.domain.Paquete>();
		Repository<com.marketour.domain.Paquete> repository = new Repository<com.marketour.domain.Paquete>(
				com.marketour.domain.Paquete.class);
		lstdPaquete = repository.FindAll();
		for (com.marketour.domain.Paquete paquete : lstdPaquete) {
			lstPaquete.add(Paquete.ConvertToBPaquete(paquete));
		}
		if(isPromo()){
			return lstPaquete;
		} else {
			for(Paquete paq : lstPaquete){
				paq.setPromocion(null);
			}
			return lstPaquete;
		}
	}

	public static List<Paquete> ConsultarPaquetesXNombre(String nombre) {
		List<Paquete> lstPaquete = new ArrayList<Paquete>();
		List<com.marketour.domain.Paquete> lstdPaquete = new ArrayList<com.marketour.domain.Paquete>();
		Repository<com.marketour.domain.Paquete> repository = new Repository<com.marketour.domain.Paquete>(
				com.marketour.domain.Paquete.class);
		lstdPaquete = repository.FindByColumn("nombre LIKE '%" + nombre + "%'");
		for (com.marketour.domain.Paquete paquete : lstdPaquete) {
			Paquete pac = new Paquete();
			pac = Paquete.ConvertToBPaquete(paquete);
			lstPaquete.add(pac);
		}
		if(isPromo()){
			return lstPaquete;
		} else {
			for(Paquete paq : lstPaquete){
				paq.setPromocion(null);
			}
			return lstPaquete;
		}
	}

	public static List<Paquete> ConsultarPaquetesXProveedor(int idProveedor) {
		List<Paquete> lstPaquete = new ArrayList<Paquete>();
		List<com.marketour.domain.Paquete> lstdPaquete = new ArrayList<com.marketour.domain.Paquete>();
		Repository<com.marketour.domain.Paquete> repository = new Repository<com.marketour.domain.Paquete>(
				com.marketour.domain.Paquete.class);
		lstdPaquete = repository.FindByColumn("proveedor = " + idProveedor);
		for (com.marketour.domain.Paquete paquete : lstdPaquete) {
			lstPaquete.add(Paquete.ConvertToBPaquete(paquete));
		}
		if(isPromo()){
			return lstPaquete;
		} else {
			for(Paquete paq : lstPaquete){
				paq.setPromocion(null);
			}
			return lstPaquete;
		}
	}

	public static List<Paquete> FiltrarPaquetes(String fechaInicio,
			String fechaFin, double precio1, double precio2, int idCiudad) {
		List<Paquete> lstPaquete = new ArrayList<Paquete>();
		List<com.marketour.domain.Paquete> lstdPaquete = new ArrayList<com.marketour.domain.Paquete>();
		Repository<com.marketour.domain.Paquete> repository = new Repository<com.marketour.domain.Paquete>(
				com.marketour.domain.Paquete.class);

		RepositoryProduct repositoryProductos = new RepositoryProduct();
		if (precio1 > 0 && precio2 > 0) {
			lstdPaquete = repository.FindByColumn("valor>=" + precio1
					+ " and valor<=" + precio2);
		} else {
			lstdPaquete = repository.FindAll();
		}

		for (com.marketour.domain.Paquete paquete : lstdPaquete) {
			if (repositoryProductos.FindProductsPorPaquete(fechaInicio,
					fechaFin, precio1, precio2, idCiudad, paquete.getId())
					.size() > 0) {
				lstPaquete.add(Paquete.ConvertToBPaquete(paquete));
			}

		}
		if(isPromo()){
			return lstPaquete;
		} else {
			for(Paquete paq : lstPaquete){
				paq.setPromocion(null);
			}
			return lstPaquete;
		}
	}

	public static com.marketour.business.Paquete Persist(Paquete business) {
		Repository<com.marketour.domain.Paquete> repositoryPackage = new Repository<com.marketour.domain.Paquete>(
				com.marketour.domain.Paquete.class);
		com.marketour.domain.Paquete domain = null;
		if (business.getId() > 0) {
			domain = repositoryPackage.FindById(business.getId());
			domain = Paquete.ConvertToDBPaquete(business, domain);
			// Offer
			Repository<com.marketour.domain.Promocion> repositoryOffer = new Repository<com.marketour.domain.Promocion>(
					com.marketour.domain.Promocion.class);
			repositoryOffer.Update(domain.getPromocion());
			// Products
			Repository<com.marketour.domain.Producto> repositoryProduct = new Repository<com.marketour.domain.Producto>(
					com.marketour.domain.Producto.class);
			for (Producto productB : business.getProductos()) {
				domain.getProductos().add(
						repositoryProduct.FindById(productB.getId()));
			}
			repositoryPackage.Update(domain);
		} else {
			domain = Paquete.ConvertToDBPaquete(business,
					new com.marketour.domain.Paquete());
			// Provider
			Repository<com.marketour.domain.Proveedor> repositoryProvider = new Repository<com.marketour.domain.Proveedor>(
					com.marketour.domain.Proveedor.class);
			domain.setProveedor(repositoryProvider.FindById(1));
			// Offer
			Repository<com.marketour.domain.Promocion> repositoryOffer = new Repository<com.marketour.domain.Promocion>(
					com.marketour.domain.Promocion.class);
			repositoryOffer.Save(domain.getPromocion());
			// Products
			Repository<com.marketour.domain.Producto> repositoryProduct = new Repository<com.marketour.domain.Producto>(
					com.marketour.domain.Producto.class);
			for (Producto productB : business.getProductos()) {
				domain.getProductos().add(
						repositoryProduct.FindById(productB.getId()));
			}
			repositoryPackage.Save(domain);
		}

		return ConsultarPaquete(domain.getId());
	}
}