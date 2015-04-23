
package com.marketour.business;

import java.io.Serializable;


/**
 * @author Andres
 * @version 1.0
 * @created 13-abr.-2015 9:52:37 a. m.
 */
public class MedioPago implements Serializable {

	private TipoMedioPago m_TipoMedioPago;

	public MedioPago(){

	}

	public void finalize() throws Throwable {

	}

	public TipoMedioPago getTipoMedioPago(){
		return m_TipoMedioPago;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setTipoMedioPago(TipoMedioPago newVal){
		m_TipoMedioPago = newVal;
	}

}
