package com.marketour.business;

import java.io.Serializable;

public class Proveedor extends Usuario implements Serializable {

	private String cuenta = "";
	private String descripcion = "";
	private int id = 0;
	private String nit = "";

	public static Proveedor ConvertToBProveedor(
			com.marketour.domain.Proveedor domain) {
		com.marketour.business.Proveedor business = new com.marketour.business.Proveedor();
		if (domain != null) {
			business.setId(domain.getId());
			business.setDescripcion(domain.getDescripcion());
			business.setNit(domain.getNit());
			business.setCuenta(domain.getCuenta());
		}
		return business;
	}

	public String getCuenta() {
		return cuenta;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public String getNit() {
		return nit;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setNit(String nit) {
		this.nit = nit;
	}
}
