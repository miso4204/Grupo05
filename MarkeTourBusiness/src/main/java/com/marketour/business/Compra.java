
package com.marketour.business;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.marketour.domain.ItemCompra;

/**
 * @author Andres
 * @version 1.0
 * @created 13-abr.-2015 9:52:32 a. m.
 */
@Entity
@Table(name="Compra"
    ,catalog="grupocre_marketour"
)
public class Compra implements Serializable{
	private int cliente;
	private int calificacion;
	private Date fechaCompra;
	private int medioPago;
	private int estado;
	private Set<com.marketour.business.ItemCompra> itemCompras = new HashSet<com.marketour.business.ItemCompra>(0);
	public Compra(){
	}
	public Set<com.marketour.business.ItemCompra> getItemCompras() {
        return this.itemCompras;
    }
    
    public void setItemCompras(Set<com.marketour.business.ItemCompra> itemCompras) {
        this.itemCompras = itemCompras;
    }
	public int getCliente() {
		return cliente;
	}
	public void setCliente(int cliente) {
		this.cliente = cliente;
	}
	public int getCalificacion() {
		return calificacion;
	}
	public void setCalificacion(int calificacion) {
		this.calificacion = calificacion;
	}
	public Date getFechaCompra() {
		return fechaCompra;
	}
	public void setFechaCompra(Date fechaCompra) {
		this.fechaCompra = fechaCompra;
	}
	public int getMedioPago() {
		return medioPago;
	}
	public void setMedioPago(int medioPago) {
		this.medioPago = medioPago;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	
}

