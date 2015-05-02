package com.marketour.facade;

import java.util.ArrayList;
import java.util.List;

import com.marketour.business.Categoria;
import com.marketour.business.Ciudad;
import com.marketour.business.Departamento;
import com.marketour.business.Producto;
import com.marketour.business.Paquete;
import com.marketour.persistence.Repository;
import com.marketour.persistence.RepositoryProduct;

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

	public static List<Categoria> ConsultarCategoriasTodos() {
		List<Categoria> business = new ArrayList<Categoria>();
		List<com.marketour.domain.Categoria> domain = new ArrayList<com.marketour.domain.Categoria>();
		Repository<com.marketour.domain.Categoria> repository = new Repository<com.marketour.domain.Categoria>(
				com.marketour.domain.Categoria.class);
		domain = repository.FindAll();
		for (com.marketour.domain.Categoria item : domain) {

			business.add(Categoria.ConvertToBCategoria(item));
		}
		return business;
	}

	public static List<Ciudad> ConsultarCiudadesTodos() {
		List<Ciudad> business = new ArrayList<Ciudad>();
		List<com.marketour.domain.Ciudad> domain = new ArrayList<com.marketour.domain.Ciudad>();
		Repository<com.marketour.domain.Ciudad> repository = new Repository<com.marketour.domain.Ciudad>(
				com.marketour.domain.Ciudad.class);
		domain = repository.FindAll();
		for (com.marketour.domain.Ciudad item : domain) {

			business.add(Ciudad.ConvertToBCiudad(item));
		}
		return business;
	}

	public static List<Departamento> ConsultarDepartamentosTodos() {
		List<Departamento> business = new ArrayList<Departamento>();
		List<com.marketour.domain.Departamento> domain = new ArrayList<com.marketour.domain.Departamento>();
		Repository<com.marketour.domain.Departamento> repository = new Repository<com.marketour.domain.Departamento>(
				com.marketour.domain.Departamento.class);
		domain = repository.FindAll();
		for (com.marketour.domain.Departamento item : domain) {
			business.add(Departamento.ConvertToBDepartamento(item));
		}
		return business;
	}

	public static Producto Persist(com.marketour.business.Producto business) {
		Repository<com.marketour.domain.Producto> repositoryProduct = new Repository<com.marketour.domain.Producto>(
				com.marketour.domain.Producto.class);
		com.marketour.domain.Producto domain = null;
		if (business.getId() > 0) {
			domain = repositoryProduct.FindById(business.getId());
			domain = Producto.ConvertToDBProducto(business, domain);
			// Category
			Repository<com.marketour.domain.Categoria> repositoryCategory = new Repository<com.marketour.domain.Categoria>(
					com.marketour.domain.Categoria.class);
			domain.setCategoria(repositoryCategory.FindById(business
					.getIdCategoria()));
			// City
			Repository<com.marketour.domain.Ciudad> repositoryCity = new Repository<com.marketour.domain.Ciudad>(
					com.marketour.domain.Ciudad.class);
			domain.setCiudad(repositoryCity.FindById(business.getIdCiudad()));
			repositoryProduct.Update(domain);
		} else {
			domain = Producto.ConvertToDBProducto(business,
					new com.marketour.domain.Producto());
			// Provider
			Repository<com.marketour.domain.Proveedor> repositoryProvider = new Repository<com.marketour.domain.Proveedor>(
					com.marketour.domain.Proveedor.class);
			domain.setProveedor(repositoryProvider.FindById(1));
			// Category
			Repository<com.marketour.domain.Categoria> repositoryCategory = new Repository<com.marketour.domain.Categoria>(
					com.marketour.domain.Categoria.class);
			domain.setCategoria(repositoryCategory.FindById(business
					.getIdCategoria()));
			// City
			Repository<com.marketour.domain.Ciudad> repositoryCity = new Repository<com.marketour.domain.Ciudad>(
					com.marketour.domain.Ciudad.class);
			domain.setCiudad(repositoryCity.FindById(business.getIdCiudad()));
			repositoryProduct.Save(domain);
		}
		return ConsultarProducto(domain.getId());
	}
	
	
	
	public static List<Producto> FiltrarProducto(String fechaInicio,String fechaFin,double precio1,double precio2,int idCiudad) {
		List<Producto> lstProducto = new ArrayList<Producto>();
		List<com.marketour.domain.Producto> lstdProducto = new ArrayList<com.marketour.domain.Producto>();
		RepositoryProduct repository = new RepositoryProduct();
		lstdProducto = repository.FindProducts(fechaInicio, fechaFin, precio1, precio2, idCiudad);
		for (com.marketour.domain.Producto producto : lstdProducto) {
			Producto pac = new Producto();
			pac = Producto.ConvertToBProduct(producto);
			lstProducto.add(pac);
		}
		return lstProducto;
	}
}