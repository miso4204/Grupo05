
package com.marketour.business;

import java.io.Serializable;

/**
 * @author Andres
 * @version 1.0
 * @created 13-abr.-2015 9:52:43 a. m.
 */
public class TipoMedioPago implements Serializable {

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	private String nombre;

	public TipoMedioPago(){

	}

	public void finalize() throws Throwable {

	}

}
