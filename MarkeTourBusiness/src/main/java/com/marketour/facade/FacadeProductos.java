package com.marketour.facade;

import java.util.ArrayList;
import java.util.List;

import com.marketour.business.Categoria;
import com.marketour.business.Ciudad;
import com.marketour.business.Departamento;
import com.marketour.business.PlanAlimentacion;
import com.marketour.business.Producto;
import com.marketour.business.TipoContenido;
import com.marketour.domain.Alimentacion;
import com.marketour.domain.Alojamiento;
import com.marketour.domain.Contenido;
import com.marketour.domain.Tour;
import com.marketour.domain.Trasporte;
import com.marketour.persistence.Repository;
import com.marketour.persistence.RepositoryProduct;

public class FacadeProductos {

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

	public static Object ConsultarPlanAlimenticioTodos() {
		List<PlanAlimentacion> business = new ArrayList<PlanAlimentacion>();
		List<com.marketour.domain.PlanAlimentacion> domain = new ArrayList<com.marketour.domain.PlanAlimentacion>();
		Repository<com.marketour.domain.PlanAlimentacion> repository = new Repository<com.marketour.domain.PlanAlimentacion>(
				com.marketour.domain.PlanAlimentacion.class);
		domain = repository.FindAll();
		for (com.marketour.domain.PlanAlimentacion item : domain) {
			business.add(PlanAlimentacion.ConvertToBPlanAlimentacion(item));
		}
		return business;
	}

	public static Producto ConsultarProducto(int id) {
		Repository<com.marketour.domain.Producto> repository = new Repository<com.marketour.domain.Producto>(
				com.marketour.domain.Producto.class);
		Producto business = new Producto();
		com.marketour.domain.Producto domain = repository.FindById(id);
		if (domain != null) {
			business = Producto.ConvertToBProduct(domain);
			switch (business.getIdCategoria()) {
			case 1:// Alojamiento
				Repository<com.marketour.domain.Alojamiento> repoAdd1 = new Repository<com.marketour.domain.Alojamiento>(
						com.marketour.domain.Alojamiento.class);
				com.marketour.domain.Alojamiento addInfo1 = repoAdd1
						.FindById(business.getId());
				if (addInfo1 != null) {
					business.setFechaEntrada(addInfo1.getFechaEntrada());
					business.setFechaSalida(addInfo1.getFechaSalida());
				}
				break;
			case 2:// Alimentación
				Repository<com.marketour.domain.Alimentacion> repoAdd2 = new Repository<com.marketour.domain.Alimentacion>(
						com.marketour.domain.Alimentacion.class);
				com.marketour.domain.Alimentacion addInfo2 = repoAdd2
						.FindById(business.getId());
				if (addInfo2 != null) {
					business.setPlanAlimentacion(addInfo2.getPlanAlimentacion()
							.getDescripcion());
					business.setIdPlanAlimentacion(addInfo2
							.getPlanAlimentacion().getId());
				}
				break;
			case 3:// Tour/Paseo
				Repository<com.marketour.domain.Tour> repoAdd3 = new Repository<com.marketour.domain.Tour>(
						com.marketour.domain.Tour.class);
				com.marketour.domain.Tour addInfo3 = repoAdd3.FindById(business
						.getId());
				if (addInfo3 != null) {
					business.setFechaEntrada(addInfo3.getFechaEntrada());
					business.setFechaSalida(addInfo3.getFechaSalida());
					business.setIdCiudadOrigen(addInfo3.getCiudadByOrigen()
							.getId());
					business.setIdCiudadDestino(addInfo3.getCiudadByDestino()
							.getId());
					business.setCiudadOrigen(addInfo3.getCiudadByOrigen()
							.getDescripcion());
					business.setCiudadDestino(addInfo3.getCiudadByDestino()
							.getDescripcion());
					business.setIdDepartamentoOrigen(addInfo3
							.getCiudadByOrigen().getDepartamento().getId());
					business.setIdDepartamentoDestino(addInfo3
							.getCiudadByDestino().getDepartamento().getId());
					business.setDepartamentoOrigen(addInfo3.getCiudadByOrigen()
							.getDepartamento().getDescripcion());
					business.setDepartamentoDestino(addInfo3
							.getCiudadByDestino().getDepartamento()
							.getDescripcion());
				}
				break;
			case 4:// Trasporte
				Repository<com.marketour.domain.Trasporte> repoAdd4 = new Repository<com.marketour.domain.Trasporte>(
						com.marketour.domain.Trasporte.class);
				com.marketour.domain.Trasporte addInfo4 = repoAdd4
						.FindById(business.getId());
				if (addInfo4 != null) {
					business.setFechaEntrada(addInfo4.getFechaEntrada());
					business.setFechaSalida(addInfo4.getFechaSalida());
					business.setIdCiudadOrigen(addInfo4.getCiudadByOrigen()
							.getId());
					business.setIdCiudadDestino(addInfo4.getCiudadByDestino()
							.getId());
					business.setCiudadOrigen(addInfo4.getCiudadByOrigen()
							.getDescripcion());
					business.setCiudadDestino(addInfo4.getCiudadByDestino()
							.getDescripcion());
					business.setIdDepartamentoOrigen(addInfo4
							.getCiudadByOrigen().getDepartamento().getId());
					business.setIdDepartamentoDestino(addInfo4
							.getCiudadByDestino().getDepartamento().getId());
					business.setDepartamentoOrigen(addInfo4.getCiudadByOrigen()
							.getDepartamento().getDescripcion());
					business.setDepartamentoDestino(addInfo4
							.getCiudadByDestino().getDepartamento()
							.getDescripcion());
				}
				break;
			}
		}
		return business;
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

	public static Object ConsultarTipoContenidoTodos() {
		List<TipoContenido> business = new ArrayList<TipoContenido>();
		List<com.marketour.domain.TipoContenido> domain = new ArrayList<com.marketour.domain.TipoContenido>();
		Repository<com.marketour.domain.TipoContenido> repository = new Repository<com.marketour.domain.TipoContenido>(
				com.marketour.domain.TipoContenido.class);
		domain = repository.FindAll();
		for (com.marketour.domain.TipoContenido item : domain) {

			business.add(TipoContenido.ConvertToBTipoContenido(item));
		}
		return business;
	}

	public static List<Producto> FiltrarProducto(String fechaInicio,
			String fechaFin, double precio1, double precio2, int idCiudad) {
		List<Producto> lstProducto = new ArrayList<Producto>();
		List<com.marketour.domain.Producto> lstdProducto = new ArrayList<com.marketour.domain.Producto>();
		RepositoryProduct repository = new RepositoryProduct();
		lstdProducto = repository.FindProducts(fechaInicio, fechaFin, precio1,
				precio2, idCiudad);
		for (com.marketour.domain.Producto producto : lstdProducto) {
			Producto pac = new Producto();
			pac = Producto.ConvertToBProduct(producto);
			lstProducto.add(pac);
		}
		return lstProducto;
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
			if (business.getProveedor() != null) {
				Repository<com.marketour.domain.Proveedor> repositoryProvider = new Repository<com.marketour.domain.Proveedor>(
						com.marketour.domain.Proveedor.class);
				domain.setProveedor(repositoryProvider.FindById(business
						.getProveedor().getId()));
			} else {
				Repository<com.marketour.domain.Proveedor> repositoryProvider = new Repository<com.marketour.domain.Proveedor>(
						com.marketour.domain.Proveedor.class);
				domain.setProveedor(repositoryProvider.FindById(1));
			}
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
		boolean newAdditional = false;

		// Info Additional
		switch (business.getIdCategoria()) {
		case 1:// Alojamiento
			Repository<com.marketour.domain.Alojamiento> repoAdd1 = new Repository<com.marketour.domain.Alojamiento>(
					com.marketour.domain.Alojamiento.class);
			com.marketour.domain.Alojamiento addInfo1 = domain.getAlojamiento();
			if (addInfo1 == null) {
				addInfo1 = new Alojamiento();
				addInfo1.setId(domain.getId());
				newAdditional = true;
			}
			if (business.getFechaEntrada() != null)
				addInfo1.setFechaEntrada(business.getFechaEntrada());
			if (business.getFechaSalida() != null)
				addInfo1.setFechaSalida(business.getFechaSalida());
			addInfo1.setProducto(domain);
			if (newAdditional)
				repoAdd1.Save(addInfo1);
			else
				repoAdd1.Update(addInfo1);
			break;
		case 2:// Alimentación
			Repository<com.marketour.domain.Alimentacion> repoAdd2 = new Repository<com.marketour.domain.Alimentacion>(
					com.marketour.domain.Alimentacion.class);
			Repository<com.marketour.domain.PlanAlimentacion> repoPlan = new Repository<com.marketour.domain.PlanAlimentacion>(
					com.marketour.domain.PlanAlimentacion.class);
			com.marketour.domain.Alimentacion addInfo2 = domain
					.getAlimentacion();
			if (addInfo2 == null) {
				addInfo2 = new Alimentacion();
				addInfo2.setId(domain.getId());
				newAdditional = true;
			}
			if (business.getIdPlanAlimentacion() > 0)
				addInfo2.setPlanAlimentacion(repoPlan.FindById(business
						.getIdPlanAlimentacion()));
			addInfo2.setProducto(domain);
			if (newAdditional)
				repoAdd2.Save(addInfo2);
			else
				repoAdd2.Update(addInfo2);
			break;
		case 3:// Tour/Paseo
			Repository<com.marketour.domain.Tour> repoAdd3 = new Repository<com.marketour.domain.Tour>(
					com.marketour.domain.Tour.class);
			Repository<com.marketour.domain.Ciudad> repoCity1 = new Repository<com.marketour.domain.Ciudad>(
					com.marketour.domain.Ciudad.class);
			com.marketour.domain.Tour addInfo3 = domain.getTour();
			if (addInfo3 == null) {
				addInfo3 = new Tour();
				addInfo3.setId(domain.getId());
				newAdditional = true;
			}
			if (business.getFechaEntrada() != null)
				addInfo3.setFechaEntrada(business.getFechaEntrada());
			if (business.getFechaSalida() != null)
				addInfo3.setFechaSalida(business.getFechaSalida());
			if (business.getIdCiudadOrigen() > 0)
				addInfo3.setCiudadByOrigen(repoCity1.FindById(business
						.getIdCiudadOrigen()));
			if (business.getIdCiudadDestino() > 0)
				addInfo3.setCiudadByDestino(repoCity1.FindById(business
						.getIdCiudadDestino()));
			addInfo3.setProducto(domain);
			if (newAdditional)
				repoAdd3.Save(addInfo3);
			else
				repoAdd3.Update(addInfo3);
			break;
		case 4:// Trasporte
			Repository<com.marketour.domain.Trasporte> repoAdd4 = new Repository<com.marketour.domain.Trasporte>(
					com.marketour.domain.Trasporte.class);
			Repository<com.marketour.domain.Ciudad> repoCity2 = new Repository<com.marketour.domain.Ciudad>(
					com.marketour.domain.Ciudad.class);
			com.marketour.domain.Trasporte addInfo4 = domain.getTrasporte();
			if (addInfo4 == null) {
				addInfo4 = new Trasporte();
				addInfo4.setId(domain.getId());
				newAdditional = true;
			}
			if (business.getFechaEntrada() != null)
				addInfo4.setFechaEntrada(business.getFechaEntrada());
			if (business.getFechaSalida() != null)
				addInfo4.setFechaSalida(business.getFechaSalida());
			if (business.getIdCiudadOrigen() > 0)
				addInfo4.setCiudadByOrigen(repoCity2.FindById(business
						.getIdCiudadOrigen()));
			if (business.getIdCiudadDestino() > 0)
				addInfo4.setCiudadByDestino(repoCity2.FindById(business
						.getIdCiudadDestino()));
			addInfo4.setProducto(domain);
			if (newAdditional)
				repoAdd4.Save(addInfo4);
			else
				repoAdd4.Update(addInfo4);
			break;
		}

		if (business.getContenidos() != null) {
			Repository<com.marketour.domain.Contenido> repoContent = new Repository<com.marketour.domain.Contenido>(
					com.marketour.domain.Contenido.class);
			Repository<com.marketour.domain.TipoContenido> repoContentType = new Repository<com.marketour.domain.TipoContenido>(
					com.marketour.domain.TipoContenido.class);
			for (com.marketour.business.Contenido bcontent : business
					.getContenidos()) {
				if (bcontent.getId() > 0) {
					Contenido dcontent = repoContent.FindById(bcontent.getId());
					dcontent.setProducto(domain);
					dcontent.setContenido(bcontent.getContenido());
					if (bcontent.getIdTipoContenido() > 0)
						dcontent.setTipoContenido(repoContentType
								.FindById(bcontent.getIdTipoContenido()));
					dcontent.setEstado(bcontent.getEstado());
					repoContent.Update(dcontent);
				} else {
					Contenido dcontent = new Contenido();
					dcontent.setProducto(domain);
					dcontent.setContenido(bcontent.getContenido());
					if (bcontent.getIdTipoContenido() > 0)
						dcontent.setTipoContenido(repoContentType
								.FindById(bcontent.getIdTipoContenido()));
					dcontent.setEstado(bcontent.getEstado());
					repoContent.Save(dcontent);
				}
			}
		}

		return ConsultarProducto(domain.getId());
	}
}