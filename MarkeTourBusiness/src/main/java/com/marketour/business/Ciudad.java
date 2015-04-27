package com.marketour.business;

import java.io.Serializable;

/**
 * @author Andres
 * @version 1.0
 * @created 13-abr.-2015 9:52:31 a. m.
 */
public class Ciudad implements Serializable {

	private String ciudad;
	private Departamento Departamento;

	public Departamento getDepartamento() {
		return Departamento;
	}

	public void setDepartamento(Departamento newVal) {
		Departamento = newVal;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public static Ciudad ConvertToBCiudad(com.marketour.domain.Ciudad ciudad) {
		Ciudad ciu = new Ciudad();
		ciu.setDepartamento(new Departamento()); // com.marketour.business.Departamento.ConvertToBDepartamento(
		// ciudad.getDepartamento()));
		ciu.setCiudad("ciudad");
		return ciu;
	}

	public static com.marketour.domain.Ciudad ConvertToDBCiudad(Ciudad ciudad) {
		com.marketour.domain.Ciudad ciu = new com.marketour.domain.Ciudad();
		ciu.setDepartamento(com.marketour.business.Departamento
				.ConvertToDBDepartamento(ciudad.getDepartamento()));
		ciu.setDescripcion(ciudad.getCiudad());
		return ciu;
	}
}