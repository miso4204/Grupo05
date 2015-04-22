package com.marketour.persistence;

import com.marketour.domain.*;

public class RepositoryPackage extends Repository<Paquete> {

	public RepositoryPackage() {
		super(Paquete.class);
	}

	@SuppressWarnings({ "rawtypes" })
	public boolean PersistProduct(int id, int data) {
		RepositoryProduct repositoryProduct = new com.marketour.persistence.RepositoryProduct();
		boolean exists = false;
		boolean result = false;
		for (Producto product : repositoryProduct.FindByPackage(id)) {
			if (product.getId().equals(data)) {
				exists = true;
			}
		}
		/*
		 * if (!exists) { Repository repositoryRelation = new
		 * com.marketour.persistence.Repository<PaqueteProducto>(
		 * PaqueteProducto.class); PaqueteProducto relation = new
		 * PaqueteProducto(); // relation.setPaquete(id); //
		 * relation.setProducto(data); repositoryRelation.Persist(relation);
		 * result = true; }
		 */
		return result;
	}

	public boolean DeleteProduct(int id, int data) {
		/*
		 * Repository<PaqueteProducto> repositoryRelation = new
		 * com.marketour.persistence.Repository<PaqueteProducto>(
		 * PaqueteProducto.class);
		 */
		boolean result = false;
		/*
		 * for (PaqueteProducto relation : repositoryRelation
		 * .FindByColumn("paquete = " + id)) { if
		 * (relation.getProducto().equals(data)) {
		 * repositoryRelation.Delete(relation.getId()); result = true; } }
		 */
		return result;
	}
}
