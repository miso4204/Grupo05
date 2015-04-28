package com.marketour.business;

import java.io.Serializable;

/**
 * @author Andres
 * @version 1.0
 * @created 13-abr.-2015 9:52:40 a. m.
 */
public class Proveedor extends Usuario implements Serializable {

	private int id = 0;
	private String usuario = "";
	private String descripcion = "";
	private String nit = "";
	private String cuenta = "";

	public static Proveedor ConvertToBProveedor(
			com.marketour.domain.Proveedor domain) {
		com.marketour.business.Proveedor business = new com.marketour.business.Proveedor();
		business.setId(domain.getId());
		business.setDescripcion(domain.getDescripcion());
		business.setNit(domain.getNit());
		business.setCuenta(domain.getCuenta());
		// User
		business.setUsuario(domain.getUsuario().getNombre());
		return business;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
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

}
