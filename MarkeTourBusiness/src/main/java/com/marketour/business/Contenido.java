
package com.marketour.business;

import java.io.Serializable;


/**
 * @author Andres
 * @version 1.0
 * @created 13-abr.-2015 9:52:33 a. m.
 */
public class Contenido implements Serializable{

	private String contenido;
	private String tipoContenido;

	public Contenido() {

	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public String getTipoContenido() {
		return tipoContenido;
	}

	public void setTipoContenido(String tipoContenido) {
		this.tipoContenido = tipoContenido;
	}

	public void finalize() throws Throwable {

	}

}
