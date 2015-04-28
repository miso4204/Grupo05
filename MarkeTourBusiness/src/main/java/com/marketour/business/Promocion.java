package com.marketour.business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.marketour.domain.Paquete;
import com.marketour.domain.Producto;

public class Promocion implements Serializable {

	private Integer id = 0;
	private String descripcion = "";
	private Date fechaInicio;
	private Date fechaFin;
	private String condiciones = "";
	private long descuento = 0;
	private String codigo = "";
	private int estado = 0;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechaInicio() {
		return this.fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return this.fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getCondiciones() {
		return this.condiciones;
	}

	public void setCondiciones(String condiciones) {
		this.condiciones = condiciones;
	}

	public long getDescuento() {
		return this.descuento;
	}

	public void setDescuento(long descuento) {
		this.descuento = descuento;
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public int getEstado() {
		return this.estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public static Promocion ConvertToBPromocion(
			com.marketour.domain.Promocion domain) {
		Promocion business = new Promocion();
		business.setCodigo(domain.getCodigo());
		business.setCondiciones(domain.getCondiciones());
		business.setDescripcion(domain.getDescripcion());
		business.setDescuento(domain.getDescuento());
		business.setEstado(domain.getEstado());
		business.setFechaFin(domain.getFechaFin());
		business.setFechaInicio(domain.getFechaInicio());
		business.setId(domain.getId());
		return business;
	}

	public static com.marketour.domain.Promocion ConvertToDBPromocion(
			Promocion business) {
		com.marketour.domain.Promocion domain = new com.marketour.domain.Promocion();
		domain.setCodigo(business.getCodigo());
		domain.setCondiciones(business.getCondiciones());
		domain.setDescripcion(business.getDescripcion());
		domain.setDescuento(business.getDescuento());
		domain.setEstado(business.getEstado());
		domain.setFechaFin(business.getFechaFin());
		domain.setFechaInicio(business.getFechaInicio());
		domain.setId(business.getId());
		return domain;
	}
}
