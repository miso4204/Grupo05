
package com.marketour.business;

import java.io.Serializable;



/**
 * @author Andres
 * @version 1.0
 * @created 13-abr.-2015 9:52:35 a. m.
 */
public class ItemCompra implements Serializable {

	private int numeroProductos;

	public ItemCompra(){

	}

	public int getNumeroProductos() {
		return numeroProductos;
	}

	public void setNumeroProductos(int numeroProductos) {
		this.numeroProductos = numeroProductos;
	}

	public void finalize() throws Throwable {

	}

}
