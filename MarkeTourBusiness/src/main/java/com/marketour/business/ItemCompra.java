
package com.marketour.business;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.marketour.domain.Compra;
import com.marketour.domain.Paquete;
import com.marketour.domain.Producto;



/**
 * @author Andres
 * @version 1.0
 * @created 13-abr.-2015 9:52:35 a. m.
 */
@Entity
@Table(name="ItemCompra"
    ,catalog="devfloor_marketour"
)
public class ItemCompra implements Serializable {

	private int id;
    private Integer producto;
    private Integer paquete;
    private Integer compra;
    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Integer getProducto() {
		return producto;
	}
	public void setProducto(Integer producto) {
		this.producto = producto;
	}
	public Integer getPaquete() {
		return paquete;
	}
	public void setPaquete(Integer paquete) {
		this.paquete = paquete;
	}
	public Integer getCompra() {
		return compra;
	}
	public void setCompra(Integer compra) {
		this.compra = compra;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	private BigDecimal valor;
    private int cantidad;
	public void finalize() throws Throwable {

	}
	public static ItemCompra ConvertTobItemCompra(com.marketour.domain.ItemCompra itemCompra)
	{
		ItemCompra item=new ItemCompra();
		item.setCantidad(itemCompra.getCantidad());
		item.setCompra(itemCompra.getCompra().getId());
		item.setId(itemCompra.getId());
		//item.setPaquete(itemCompra.getPaquete().getId());
		//item.setProducto(itemCompra.getProducto().getId());
		item.setValor(itemCompra.getValor());
		return item;
	}

}
