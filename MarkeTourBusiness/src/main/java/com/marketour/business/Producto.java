
package com.marketour.business;

import java.io.Serializable;
import java.math.BigDecimal;



/**
 * @author Andres
 * @version 1.0
 * @created 13-abr.-2015 9:52:39 a. m.
 */
public class Producto  implements Serializable {

	private int activo;
	private Integer capacidad;
	private String cordenadas;
	private String descripcion;
	private String nombre;
	private BigDecimal valor;
	private Categoria Categoria;
	private Ciudad Ciudad;
	private ItemCompra ItemCompra;
	private Contenido Contenido;
	private Disponibilidad Disponibilidad;
	

	public int getActivo() {
		return activo;
	}

	public void setActivo(int activo) {
		this.activo = activo;
	}

	public Integer getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(Integer capacidad) {
		this.capacidad = capacidad;
	}

	public String getCordenadas() {
		return cordenadas;
	}

	public void setCordenadas(String cordenadas) {
		this.cordenadas = cordenadas;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Categoria getCategoria() {
		return Categoria;
	}

	public void setCategoria(Categoria Categoria) {
		this.Categoria = Categoria;
	}

	public Ciudad getCiudad() {
		return Ciudad;
	}

	public void setCiudad(Ciudad Ciudad) {
		this.Ciudad = Ciudad;
	}

	public ItemCompra getItemCompra() {
		return ItemCompra;
	}

	public void setItemCompra(ItemCompra ItemCompra) {
		this.ItemCompra = ItemCompra;
	}

	public Contenido getContenido() {
		return Contenido;
	}

	public void setContenido(Contenido Contenido) {
		this.Contenido = Contenido;
	}

	public Disponibilidad getM_Disponibilidad() {
		return Disponibilidad;
	}

	public void setM_Disponibilidad(Disponibilidad m_Disponibilidad) {
		this.Disponibilidad = m_Disponibilidad;
	}

	public Producto(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	public static Producto ConvertToBProduct(com.marketour.domain.Producto producto)
    {
		Producto pro=new Producto();
		pro.setActivo(producto.getEstado());
		pro.setCapacidad(producto.getCapacidad());
		pro.setCordenadas(producto.getCoordenadas());
		pro.setDescripcion(producto.getDescripcion());
		pro.setNombre(producto.getNombre());
		pro.setValor(producto.getValor());
		pro.setCiudad(com.marketour.business.Ciudad.ConvertToBCiudad(producto.getCiudad()));
		pro.setContenido(new Contenido());
		pro.setItemCompra(new ItemCompra());
		
		return pro;
    	
    }
	public static com.marketour.domain.Producto ConvertToDBProduct(Producto producto)
    {
		com.marketour.domain.Producto pro=new com.marketour.domain.Producto();
		//pro.setActivo(producto.getActivo());
		pro.setCapacidad(producto.getCapacidad());
		//pro.setCordenadas(producto.getCordenadas());
		pro.setDescripcion(producto.getDescripcion());
		pro.setNombre(producto.getNombre());
		pro.setValor(producto.getValor());
		return pro;
    	
    }
	
}
