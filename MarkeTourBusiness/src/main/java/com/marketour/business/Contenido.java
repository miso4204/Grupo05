package com.marketour.business;

import java.io.Serializable;

/**
 * @author Andres
 * @version 1.0
 * @created 13-abr.-2015 9:52:33 a. m.
 */
public class Contenido implements Serializable {

	private Integer id = 0;
	private String tipoContenido = "";
	private String contenido = "";
	private int estado = 0;

	public com.marketour.business.Contenido ConvertToBContent(
			com.marketour.domain.Contenido domain) {
		com.marketour.business.Contenido business = new Contenido();
		business.setId(domain.getId());
		business.setTipoContenido(domain.getTipoContenido().getDescripcion());
		business.setContenido(domain.getContenido());
		business.setEstado(domain.getEstado());
		return business;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTipoContenido() {
		return tipoContenido;
	}

	public void setTipoContenido(String tipoContenido) {
		this.tipoContenido = tipoContenido;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

}
