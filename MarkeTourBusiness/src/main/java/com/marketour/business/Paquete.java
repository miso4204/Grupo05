
package com.marketour.business;

/**
 * @author Andres
 * @version 1.0
 * @created 13-abr.-2015 9:52:38 a. m.
 */
public class Paquete extends ItemCompra {

	private boolean activo;
	private String descripcion;
	private String nombre;
	private Producto m_Producto;

	public Paquete(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	public Producto getProducto(){
		return m_Producto;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setProducto(Producto newVal){
		m_Producto = newVal;
	}

}
