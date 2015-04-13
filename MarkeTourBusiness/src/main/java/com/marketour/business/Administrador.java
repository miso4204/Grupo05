
package com.marketour.business;

/**
 * @author Andres
 * @version 1.0
 * @created 13-abr.-2015 9:52:27 a. m.
 */
public class Administrador extends Usuario {


    private int id;
    private String descripcion;

	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


}