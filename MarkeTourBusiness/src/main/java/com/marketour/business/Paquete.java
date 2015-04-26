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

/**
 * @author Andres
 * @version 1.0
 * @created 13-abr.-2015 9:52:38 a. m.
 */
public class Paquete implements Serializable {

	private Integer id;
	private Proveedor proveedor;
	private Promocion promocion;
	private String nombre;
	private String descripcion;
	private BigDecimal valor;
	private Integer visitas;
	private int estado;
	private List<com.marketour.business.Producto> productos = new ArrayList();

	public static Paquete ConvertToBPaquete(com.marketour.domain.Paquete domain) {
		Paquete business = new Paquete();
		business.setId(domain.getId());
		// business.setProveedor(domain.getProveedor());
		// business.setPromocion(domain.getPromocion());
		business.setNombre(domain.getNombre());
		business.setDescripcion(domain.getDescripcion());
		business.setValor(domain.getValor());
		business.setVisitas(domain.getVisitas());
		business.setEstado(domain.getEstado());
		List<com.marketour.business.Producto> productB = new ArrayList();
		for (com.marketour.domain.Producto productD : domain.getProductos()) {
			productB.add(new com.marketour.business.Producto()
					.ConvertToBProduct(productD));
		}
		business.setProductos(productB);
		return business;
	}

	public static com.marketour.domain.Paquete ConvertToDBPaquete(
			Paquete business) {
		com.marketour.domain.Paquete domain = new com.marketour.domain.Paquete();
		domain.setId(business.getId());
		domain.setProveedor(business.getProveedor());
		domain.setPromocion(business.getPromocion());
		domain.setNombre(business.getNombre());
		domain.setDescripcion(business.getDescripcion());
		domain.setValor(business.getValor());
		domain.setVisitas(business.getVisitas());
		domain.setEstado(business.getEstado());
		// domain.setProductos(business.getProductos());
		return domain;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public Promocion getPromocion() {
		return promocion;
	}

	public void setPromocion(Promocion promocion) {
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
