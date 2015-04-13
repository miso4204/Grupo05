
package com.marketour.business;
import java.util.Date;



/**
 * @author Andres
 * @version 1.0
 * @created 13-abr.-2015 9:52:41 a. m.
 */
public class TarjetaCredito extends MedioPago {

	private Date fechaExpiracion;
	private String nombreTitular;
	private String numeroTarjeta;

	public TarjetaCredito(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

}
