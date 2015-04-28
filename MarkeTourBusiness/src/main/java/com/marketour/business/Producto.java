package com.marketour.business;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import com.marketour.domain.Categoria;
import com.marketour.domain.Proveedor;

/**
 * @author Andres
 * @version 1.0
 * @created 13-abr.-2015 9:52:39 a. m.
 */
public class Producto implements Serializable {

	private Integer id = 0;
	private String ciudad = "";
	private String categoria = "";
	private String nombre = "";
	private String descripcion = "";
	private BigDecimal valor = BigDecimal.ZERO;
	private Integer visitas = 0;
	private Integer capacidad = 0;
	private String coordenadas = "";
	private int estado = 0;
	private com.marketour.business.Proveedor proveedor = new com.marketour.business.Proveedor();
	private List<com.marketour.business.Contenido> contenidos = new ArrayList<com.marketour.business.Contenido>();

	public static Producto ConvertToBProduct(
			com.marketour.domain.Producto domain) {
		Producto business = new Producto();
		business.setId(domain.getId());
		business.setNombre(domain.getNombre());
		business.setDescripcion(domain.getDescripcion());
		business.setValor(domain.getValor());
		business.setVisitas(domain.getVisitas());
		business.setCapacidad(domain.getCapacidad());
		business.setCoordenadas(domain.getCoordenadas());
		business.setEstado(domain.getEstado());
		// Category
		if (domain.getCategoria() != null)
			business.setCategoria(domain.getCategoria().getDescripcion());
		// Ciudad
		if (domain.getCiudad() != null)
			business.setCiudad(domain.getCiudad().getDescripcion());
		// Content
		if (domain.getContenidos() != null) {
			List<com.marketour.business.Contenido> contentB = new ArrayList<com.marketour.business.Contenido>();
			for (com.marketour.domain.Contenido contentD : domain
					.getContenidos()) {
				contentB.add(new com.marketour.business.Contenido()
						.ConvertToBContent(contentD));
			}
			business.setContenidos(contentB);
		}
		return business;
	}

	public static com.marketour.domain.Producto ConvertToDBProduct(
			Producto producto) {
		com.marketour.domain.Producto pro = new com.marketour.domain.Producto();
		// pro.setActivo(producto.getActivo());
		pro.setCapacidad(producto.getCapacidad());
		// pro.setCordenadas(producto.getCordenadas());
		pro.setDescripcion(producto.getDescripcion());
		pro.setNombre(producto.getNombre());
		pro.setValor(producto.getValor());
		return pro;

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

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
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

	public Integer getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(Integer capacidad) {
		this.capacidad = capacidad;
	}

	public String getCoordenadas() {
		return coordenadas;
	}

	public void setCoordenadas(String coordenadas) {
		this.coordenadas = coordenadas;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public List<com.marketour.business.Contenido> getContenidos() {
		return contenidos;
	}

	public void setContenidos(List<com.marketour.business.Contenido> contenidos) {
		this.contenidos = contenidos;
	}

}
