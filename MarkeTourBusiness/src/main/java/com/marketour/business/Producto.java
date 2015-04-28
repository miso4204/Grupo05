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
	private Integer idCiudad = 0;
	private String departamento = "";
	private Integer idDepartamento = 0;
	private String categoria = "";
	private Integer idCategoria = 0;
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
		if (domain.getCategoria() != null) {
			business.setCategoria(domain.getCategoria().getDescripcion());
			business.setIdCategoria(domain.getCategoria().getId());
		}
		// Ciudad
		if (domain.getCiudad() != null) {
			business.setCiudad(domain.getCiudad().getDescripcion());
			business.setIdCiudad(domain.getCiudad().getId());
			business.setDepartamento(domain.getCiudad().getDepartamento()
					.getDescripcion());
			business.setIdDepartamento(domain.getCiudad().getDepartamento()
					.getId());
		}
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

	public static com.marketour.domain.Producto ConvertToDBProducto(
			Producto business, com.marketour.domain.Producto domain) {
		domain.setNombre(business.getNombre());
		domain.setDescripcion(business.getDescripcion());
		domain.setValor(business.getValor());
		domain.setVisitas(business.getVisitas());
		domain.setCapacidad(business.getCapacidad());
		domain.setCoordenadas(business.getCoordenadas());
		domain.setEstado(business.getEstado());
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

	public Integer getIdCiudad() {
		return idCiudad;
	}

	public void setIdCiudad(Integer idCiudad) {
		this.idCiudad = idCiudad;
	}

	public Integer getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public Integer getIdDepartamento() {
		return idDepartamento;
	}

	public void setIdDepartamento(Integer idDepartamento) {
		this.idDepartamento = idDepartamento;
	}

}
