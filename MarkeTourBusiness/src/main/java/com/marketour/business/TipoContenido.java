package com.marketour.business;

import java.io.Serializable;

public class TipoContenido implements Serializable {

	private String descripcion;
	private Integer estado;
	private Integer id;

	public static TipoContenido ConvertToBTipoContenido(
			com.marketour.domain.TipoContenido domain) {
		TipoContenido business = new TipoContenido();
		business.setId(domain.getId());
		business.setEstado(domain.getEstado());
		business.setDescripcion(domain.getDescripcion());
		return business;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public int getEstado() {
		return estado;
	}

	public Integer getId() {
		return id;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
