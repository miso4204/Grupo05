
package com.marketour.business;

import java.io.Serializable;


/**
 * @author Andres
 * @version 1.0
 * @created 13-abr.-2015 9:52:30 a. m.
 */
public class Categoria implements Serializable {

	private String descripcion;
	private String nonbre;

	public Categoria(){

	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNonbre() {
		return nonbre;
	}

	public void setNonbre(String nonbre) {
		this.nonbre = nonbre;
	}

	public void finalize() throws Throwable {

	}

}
