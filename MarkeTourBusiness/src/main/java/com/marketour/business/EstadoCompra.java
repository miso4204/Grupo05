
package com.marketour.business;

/**
 * @author Andres
 * @version 1.0
 * @created 13-abr.-2015 9:52:35 a. m.
 */
public class EstadoCompra {

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
