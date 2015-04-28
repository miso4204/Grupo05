package com.marketour.business;

import java.io.Serializable;

/**
 * @author Andres
 * @version 1.0
 * @created 13-abr.-2015 9:52:30 a. m.
 */
public class Categoria implements Serializable {

	private Integer id;
	private String descripcion;
	private int estado;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public static Categoria ConvertToBCategoria(
			com.marketour.domain.Categoria domain) {
		Categoria business = new Categoria();
		business.setId(domain.getId());
		business.setEstado(domain.getEstado());
		business.setDescripcion(domain.getDescripcion());
		return business;
	}
}
