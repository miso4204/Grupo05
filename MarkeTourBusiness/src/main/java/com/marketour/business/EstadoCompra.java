
package com.marketour.business;

import java.io.Serializable;

/**
 * @author Andres
 * @version 1.0
 * @created 13-abr.-2015 9:52:35 a. m.
 */
public class EstadoCompra implements Serializable {

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Compra getM_Compra() {
		return m_Compra;
	}

	public void setM_Compra(Compra m_Compra) {
		this.m_Compra = m_Compra;
	}

	private String nombre;
	private Compra m_Compra;

	public EstadoCompra(){

	}

	public void finalize() throws Throwable {

	}

	public Compra getCompra(){
		return m_Compra;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setCompra(Compra newVal){
		m_Compra = newVal;
	}

}
