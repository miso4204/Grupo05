package com.marketour.business;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.marketour.domain.ItemCompra;
import com.marketour.domain.Producto;
import com.marketour.domain.Promocion;
import com.marketour.domain.Proveedor;
import com.marketour.persistence.Repository;

/**
 * @author Andres
 * @version 1.0
 * @created 13-abr.-2015 9:52:38 a. m.
 */
public class Paquete implements Serializable {

	private Integer id = 0;
	private String nombre = "";
	private String descripcion = "";
	private BigDecimal valor = BigDecimal.ZERO;
	private Integer visitas = 0;
	private int estado = 0;
	private com.marketour.business.Proveedor proveedor = new com.marketour.business.Proveedor();
	private List<com.marketour.business.Producto> productos = new ArrayList();
	private com.marketour.business.Promocion promocion = new com.marketour.business.Promocion();

	public static Paquete ConvertToBPaquete(com.marketour.domain.Paquete domain) {
		Paquete business = new Paquete();
		business.setId(domain.getId());
		business.setNombre(domain.getNombre());
		business.setDescripcion(domain.getDescripcion());
		business.setValor(domain.getValor());
		business.setVisitas(domain.getVisitas());
		business.setEstado(domain.getEstado());
		// Provider
		if (domain.getProveedor() != null) {
			com.marketour.business.Proveedor proveedorB = com.marketour.business.Proveedor
					.ConvertToBProveedor(domain.getProveedor());
			business.setProveedor(proveedorB);
		}
		// Product
		if (domain.getProductos() != null) {
			List<com.marketour.business.Producto> productB = new ArrayList();
			for (com.marketour.domain.Producto productD : domain.getProductos()) {
				productB.add(new com.marketour.business.Producto()
						.ConvertToBProduct(productD));
			}
			business.setProductos(productB);
		}
		// Offer
		if (domain.getPromocion() != null) {
			com.marketour.business.Promocion promocionB = com.marketour.business.Promocion
					.ConvertToBPromocion(domain.getPromocion());
			business.setPromocion(promocionB);
		}
		return business;
	}

	public static com.marketour.domain.Paquete ConvertToDBPaquete(
			Paquete business, com.marketour.domain.Paquete domain) {
		domain.setNombre(business.getNombre());
		domain.setDescripcion(business.getDescripcion());
		domain.setValor(business.getValor());
		domain.setVisitas(business.getVisitas());
		domain.setEstado(business.getEstado());
		// Offer
		if (business.getPromocion() != null) {
			com.marketour.domain.Promocion promocionD = com.marketour.business.Promocion
					.ConvertToDBPromocion(business.getPromocion());
			domain.setPromocion(promocionD);
		}
		return domain;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public com.marketour.business.Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(com.marketour.business.Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public com.marketour.business.Promocion getPromocion() {
		return promocion;
	}

	public void setPromocion(com.marketour.business.Promocion promocion) {
		this.promocion = promocion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Integer getVisitas() {
		return visitas;
	}

	public void setVisitas(Integer visitas) {
		this.visitas = visitas;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public List<com.marketour.business.Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<com.marketour.business.Producto> productos) {
		this.productos = productos;
	}

}
