package com.marketour.facade;

import java.util.ArrayList;
import java.util.List;

import com.marketour.business.Paquete;
import com.marketour.business.Producto;
import com.marketour.persistence.Repository;
import com.marketour.persistence.RepositoryProduct;

public class FacadePackage {

	public static List<Paquete> ConsultarPaquetesTodos() {
		List<Paquete> lstPaquete = new ArrayList<Paquete>();
		List<com.marketour.domain.Paquete> lstdPaquete = new ArrayList<com.marketour.domain.Paquete>();
		Repository<com.marketour.domain.Paquete> repository = new Repository<com.marketour.domain.Paquete>(
				com.marketour.domain.Paquete.class);
		lstdPaquete = repository.FindAll();
		for (com.marketour.domain.Paquete paquete : lstdPaquete) {
			lstPaquete.add(Paquete.ConvertToBPaquete(paquete));
		}
		return lstPaquete;
	}
	public static List<Paquete> FiltrarPaquetes(String fechaInicio,String fechaFin,double precio1,double precio2,int idCiudad) {
		List<Paquete> lstPaquete = new ArrayList<Paquete>();
		List<com.marketour.domain.Paquete> lstdPaquete = new ArrayList<com.marketour.domain.Paquete>();
		Repository<com.marketour.domain.Paquete> repository = new Repository<com.marketour.domain.Paquete>(
				com.marketour.domain.Paquete.class);
		
		RepositoryProduct repositoryProductos = new RepositoryProduct();
		lstdPaquete = repository.FindByColumn("valor>="+precio1+" and valor<="+precio2);
		for (com.marketour.domain.Paquete paquete : lstdPaquete) {
			if(repositoryProductos.FindProductsPorPaquete(fechaInicio, fechaFin, precio1, precio2, idCiudad, paquete.getId()).size()>0){
				lstPaquete.add(Paquete.ConvertToBPaquete(paquete));	
			}
			
		}
		return lstPaquete;
	}
	public static Paquete ConsultarPaquete(int id) {
		Repository<com.marketour.domain.Paquete> repository = new Repository<com.marketour.domain.Paquete>(
				com.marketour.domain.Paquete.class);
		Paquete business = new Paquete();
		com.marketour.domain.Paquete domain = repository.FindById(id);
		if (domain != null)
			business = Paquete.ConvertToBPaquete(domain);
		return business;
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
		return lstPaquete;
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
		return lstPaquete;
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