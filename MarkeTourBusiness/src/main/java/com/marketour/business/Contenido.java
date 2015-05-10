package com.marketour.business;

import java.io.Serializable;

import com.marketour.domain.Producto;

public class Contenido implements Serializable {

	private String contenido;
	private int estado;
	private Integer id;
	private Integer idTipoContenido;
	private Producto producto;
	private String tipoContenido;

	public com.marketour.business.Contenido ConvertToBContent(
			com.marketour.domain.Contenido domain) {
		com.marketour.business.Contenido business = new Contenido();
		business.setId(domain.getId());
		business.setTipoContenido(domain.getTipoContenido().getDescripcion());
		business.setIdTipoContenido(domain.getTipoContenido().getId());
		business.setContenido(domain.getContenido());
		business.setEstado(domain.getEstado());
		return business;
	}

	public String getContenido() {
		return contenido;
	}

	public int getEstado() {
		return estado;
	}

	public Integer getId() {
		return id;
	}

	public Integer getIdTipoContenido() {
		return idTipoContenido;
	}

	public Producto getProducto() {
		return producto;
	}

	public String getTipoContenido() {
		return tipoContenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setIdTipoContenido(Integer idTipoContenido) {
		this.idTipoContenido = idTipoContenido;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public void setTipoContenido(String tipoContenido) {
		this.tipoContenido = tipoContenido;
	}
}
