package com.marketour.business;

import java.io.Serializable;

/**
 * @author Andres
 * @version 1.0
 * @created 13-abr.-2015 9:52:31 a. m.
 */
public class Ciudad implements Serializable {

	private Integer id;
	private Integer departamento;
	private String descripcion;
	private String codigo;
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

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public Integer getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Integer departamento) {
		this.departamento = departamento;
	}

	public static Ciudad ConvertToBCiudad(com.marketour.domain.Ciudad domain) {
		Ciudad business = new Ciudad();
		business.setId(domain.getId());
		business.setDepartamento(domain.getDepartamento().getId());
		business.setDescripcion(domain.getDescripcion());
		business.setCodigo(domain.getCodigo());
		business.setEstado(domain.getEstado());
		return business;
	}
}