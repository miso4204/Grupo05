
package com.marketour.business;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Andres
 * @version 1.0
 * @created 13-abr.-2015 9:52:34 a. m.
 */
public class Disponibilidad implements Serializable {

	public Date getFechaLlegada() {
		return fechaLlegada;
	}

	public void setFechaLlegada(Date fechaLlegada) {
		this.fechaLlegada = fechaLlegada;
	}

	public Date getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public int getNumeroPersonas() {
		return numeroPersonas;
	}

	public void setNumeroPersonas(int numeroPersonas) {
		this.numeroPersonas = numeroPersonas;
	}

	private Date fechaLlegada;
	private Date fechaSalida;
	private int numeroPersonas;

	public Disponibilidad(){

	}

	public void finalize() throws Throwable {

	}

}
