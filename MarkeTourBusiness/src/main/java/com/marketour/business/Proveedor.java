
package com.marketour.business;

import java.io.Serializable;

/**
 * @author Andres
 * @version 1.0
 * @created 13-abr.-2015 9:52:40 a. m.
 */
public class Proveedor extends Usuario implements Serializable{

	  
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

	public String getNit() {
		return nit;
	}

	public void setNit(String nit) {
		this.nit = nit;
	}

	public String getCuenta() {
		return cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	public String getPagina() {
		return pagina;
	}

	public void setPagina(String pagina) {
		this.pagina = pagina;
	}

	public String getTipoProveedor() {
		return tipoProveedor;
	}

	public void setTipoProveedor(String tipoProveedor) {
		this.tipoProveedor = tipoProveedor;
	}

	private int id;
	 private String descripcion;
	 private String nit;
	 private String cuenta;
	private String pagina;
	private String tipoProveedor;
	     
}
