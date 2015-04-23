
package com.marketour.business;
import java.io.Serializable;
import java.util.Date;



/**
 * @author Andres
 * @version 1.0
 * @created 13-abr.-2015 9:52:41 a. m.
 */
public class TarjetaCredito extends MedioPago implements Serializable {

	private Date fechaExpiracion;
	private String nombreTitular;
	private String numeroTarjeta;

	public TarjetaCredito(){

	}

	public Date getFechaExpiracion() {
		return fechaExpiracion;
	}

	public void setFechaExpiracion(Date fechaExpiracion) {
		this.fechaExpiracion = fechaExpiracion;
	}

	public String getNombreTitular() {
		return nombreTitular;
	}

	public void setNombreTitular(String nombreTitular) {
		this.nombreTitular = nombreTitular;
	}

	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}

	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}

	public void finalize() throws Throwable {
		super.finalize();
	}

}
