package com.marketour.business;

import java.io.Serializable;
import java.math.BigDecimal;

public class Moneda implements Serializable {

	private Integer id;
	private String descripcion;
	private String signo;
	private BigDecimal conversion;
	private int estado;

	public static Moneda ConvertToBMoneda(com.marketour.domain.Moneda domain) {
		Moneda business = new Moneda();
		business.setId(domain.getId());
		business.setEstado(domain.getEstado());
		business.setSigno(domain.getSigno());
		business.setDescripcion(domain.getDescripcion());
		business.setConversion(domain.getConversion());
		return business;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public int getEstado() {
		return estado;
	}

	public Integer getId() {
		return id;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSigno() {
		return signo;
	}

	public void setSigno(String signo) {
		this.signo = signo;
	}

	public BigDecimal getConversion() {
		return conversion;
	}

	public void setConversion(BigDecimal conversion) {
		this.conversion = conversion;
	}
}
