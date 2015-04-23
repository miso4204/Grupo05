
package com.marketour.business;

import java.io.Serializable;



/**
 * @author Andres
 * @version 1.0
 * @created 13-abr.-2015 9:52:42 a. m.
 */
public class TarjetaDebito extends MedioPago implements Serializable {

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	private String url;

	public TarjetaDebito(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

}
