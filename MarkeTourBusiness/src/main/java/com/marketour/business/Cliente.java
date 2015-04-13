
package com.marketour.business;

/**
 * @author Andres
 * @version 1.0
 * @created 13-abr.-2015 9:52:32 a. m.
 */
public class Cliente extends Usuario {

	private CarritoCompra m_CarritoCompra;
	private MedioPago m_MedioPago;
	private Compra m_Compra;
    private int id;
    private String descripcion;

	public Cliente(){

	}

	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public CarritoCompra getCarritoCompra(){
		return m_CarritoCompra;
	}

	public Compra getCompra(){
		return m_Compra;
	}

	public MedioPago getMedioPago(){
		return m_MedioPago;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setCarritoCompra(CarritoCompra newVal){
		m_CarritoCompra = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setCompra(Compra newVal){
		m_Compra = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setMedioPago(MedioPago newVal){
		m_MedioPago = newVal;
	}


	

}
