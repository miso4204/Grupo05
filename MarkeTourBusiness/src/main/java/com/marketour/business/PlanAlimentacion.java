package com.marketour.business;

import java.io.Serializable;

public class PlanAlimentacion implements Serializable {

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

	public static PlanAlimentacion ConvertToBPlanAlimentacion(
			com.marketour.domain.PlanAlimentacion domain) {
		PlanAlimentacion business = new PlanAlimentacion();
		business.setId(domain.getId());
		business.setEstado(domain.getEstado());
		business.setDescripcion(domain.getDescripcion());
		return business;
	}
}
