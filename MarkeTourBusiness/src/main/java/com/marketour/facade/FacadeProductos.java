package com.marketour.facade;

import java.util.ArrayList;
import java.util.List;

import com.marketour.business.Producto;
import com.marketour.business.Paquete;
import com.marketour.domain.Proveedor;
import com.marketour.persistence.Repository;

public class FacadeProductos {

	public static List<Paquete> ConsultarPaquetesTop(int cantidad) {
		List<Paquete> lstPaquete = new ArrayList<Paquete>();
		return lstPaquete;
	}

	public static List<Producto> ConsultarProductosXPaquetes(int idPaquete) {
		List<Producto> lstProducto = new ArrayList<Producto>();
		List<com.marketour.domain.Producto> lstdProducto = new ArrayList<com.marketour.domain.Producto>();
		Repository<com.marketour.domain.Producto> repository = new Repository<com.marketour.domain.Producto>(
				com.marketour.domain.Producto.class);
		lstdProducto = repository.FindByColumn("paquetes = " + idPaquete);
		for (com.marketour.domain.Producto producto : lstdProducto) {
			Producto pac = new Producto();
			pac = Producto.ConvertToBProduct(producto);
			lstProducto.add(pac);
		}
		return lstProducto;
	}

	public static List<Producto> ConsultarProductosXProveedor(int idProveedor) {
		List<Producto> lstProducto = new ArrayList<Producto>();
		List<com.marketour.domain.Producto> lstdProducto = new ArrayList<com.marketour.domain.Producto>();
		Repository<com.marketour.domain.Producto> repository = new Repository<com.marketour.domain.Producto>(
				com.marketour.domain.Producto.class);
		lstdProducto = repository.FindByColumn("proveedor = " + idProveedor);
		for (com.marketour.domain.Producto producto : lstdProducto) {
			Producto pac = new Producto();
			pac = Producto.ConvertToBProduct(producto);
			lstProducto.add(pac);
		}
		return lstProducto;
	}

	public static Paquete ConsultarPaquete(int id) {
		Paquete paquete = new Paquete();
		Repository<com.marketour.domain.Paquete> repository = new Repository<com.marketour.domain.Paquete>(
				com.marketour.domain.Paquete.class);
		Paquete business = new Paquete();
		com.marketour.domain.Paquete domain = repository.FindById(id);
		if (domain != null)
			business = Paquete.ConvertToBPaquete(domain);
		return business;
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
			Repository<com.marketour.domain.Producto> repository1 = new Repository<com.marketour.domain.Producto>(
					com.marketour.domain.Producto.class);
			lstPaquete.add(pac);
		}
		return lstPaquete;
	}

	public static List<Producto> ConsultarProductoXNombre(String nombre) {
		List<Producto> lstProducto = new ArrayList<Producto>();
		List<com.marketour.domain.Producto> lstdProducto = new ArrayList<com.marketour.domain.Producto>();
		Repository<com.marketour.domain.Producto> repository = new Repository<com.marketour.domain.Producto>(
				com.marketour.domain.Producto.class);
		lstdProducto = repository
				.FindByColumn("nombre LIKE '%" + nombre + "%'");
		for (com.marketour.domain.Producto producto : lstdProducto) {
			Producto pac = new Producto();
			pac = Producto.ConvertToBProduct(producto);
			lstProducto.add(pac);
		}
		return lstProducto;
	}

	public static Producto ConsultarProducto(int id) {
		Repository<com.marketour.domain.Producto> repository = new Repository<com.marketour.domain.Producto>(
				com.marketour.domain.Producto.class);
		Producto business = new Producto();
		com.marketour.domain.Producto domain = repository.FindById(id);
		if (domain != null)
			business = Producto.ConvertToBProduct(domain);
		return business;
	}

	public static List<Paquete> ConsultarPaquetesXProveedor(int idProveedor) {
		List<Paquete> lstPaquete = new ArrayList<Paquete>();
		List<com.marketour.domain.Paquete> lstdPaquete = new ArrayList<com.marketour.domain.Paquete>();
		Repository<com.marketour.domain.Paquete> repository = new Repository<com.marketour.domain.Paquete>(
				com.marketour.domain.Paquete.class);
		lstdPaquete = repository.FindByColumn("proveedor = " + idProveedor);
		for (com.marketour.domain.Paquete paquete : lstdPaquete) {
			Paquete pac = new Paquete();
			pac = Paquete.ConvertToBPaquete(paquete);
			Repository<com.marketour.domain.Producto> repository1 = new Repository<com.marketour.domain.Producto>(
					com.marketour.domain.Producto.class);
			lstPaquete.add(pac);
		}
		return lstPaquete;
	}

	public static List<Paquete> ConsultarPaquetesTodos() {
		List<Paquete> lstPaquete = new ArrayList<Paquete>();
		List<com.marketour.domain.Paquete> lstdPaquete = new ArrayList<com.marketour.domain.Paquete>();
		Repository<com.marketour.domain.Paquete> repository = new Repository<com.marketour.domain.Paquete>(
				com.marketour.domain.Paquete.class);
		lstdPaquete = repository.FindAll();
		for (com.marketour.domain.Paquete paquete : lstdPaquete) {
			Paquete pac = new Paquete();
			pac = Paquete.ConvertToBPaquete(paquete);
			Repository<com.marketour.domain.Producto> repository1 = new Repository<com.marketour.domain.Producto>(
					com.marketour.domain.Producto.class);
			lstPaquete.add(pac);
		}
		return lstPaquete;
	}

	public static List<Producto> ConsultarProductoTodos() {
		List<Producto> lstProducto = new ArrayList<Producto>();
		List<com.marketour.domain.Producto> lstdProducto = new ArrayList<com.marketour.domain.Producto>();
		Repository<com.marketour.domain.Producto> repository = new Repository<com.marketour.domain.Producto>(
				com.marketour.domain.Producto.class);
		lstdProducto = repository.FindAll();
		for (com.marketour.domain.Producto producto : lstdProducto) {
			Producto pac = new Producto();
			pac = Producto.ConvertToBProduct(producto);
			lstProducto.add(pac);
		}
		return lstProducto;
	}

	public static com.marketour.business.Paquete Persist(Paquete business) {
		Repository<com.marketour.domain.Paquete> repository = new Repository<com.marketour.domain.Paquete>(
				com.marketour.domain.Paquete.class);

		com.marketour.domain.Paquete domain = repository.FindById(business
				.getId());

		if (domain != null) {
			domain = business.ConvertToDBPaquete(business, domain);
			repository.Update(domain);
		} else {
			domain = business.ConvertToDBPaquete(business,
					new com.marketour.domain.Paquete());
			repository.Save(domain);

		}

		return null /* ConsultarPaquete(domain.getId()) */;
	}
}
