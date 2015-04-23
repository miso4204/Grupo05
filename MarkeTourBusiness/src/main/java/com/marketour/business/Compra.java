
package com.marketour.business;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Andres
 * @version 1.0
 * @created 13-abr.-2015 9:52:32 a. m.
 */
public class Compra implements Serializable{

	public int getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(int calificacion) {
		this.calificacion = calificacion;
	}

	public Date getFechaCompra() {
		return fechaCompra;
	}

	public void setFechaCompra(Date fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

	public MedioPago getM_MedioPago() {
		return m_MedioPago;
	}

	public void setM_MedioPago(MedioPago m_MedioPago) {
		this.m_MedioPago = m_MedioPago;
	}

	private int calificacion;
	private Date fechaCompra;
	private MedioPago m_MedioPago;

	public Compra() {

	}

	public void finalize() throws Throwable {

	}

	public MedioPago getMedioPago(){
		return m_MedioPago;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setMedioPago(MedioPago newVal){
		m_MedioPago = newVal;
	}

}
