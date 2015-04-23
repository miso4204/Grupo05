
package com.marketour.business;

import java.io.Serializable;

/**
 * @author Andres
 * @version 1.0
 * @created 13-abr.-2015 9:52:30 a. m.
 */
public class CashOnDelivery extends MedioPago implements Serializable {

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	private String direccion;

	public CashOnDelivery(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

}
