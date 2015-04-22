
package com.marketour.business;

import java.math.BigDecimal;



/**
 * @author Andres
 * @version 1.0
 * @created 13-abr.-2015 9:52:39 a. m.
 */
public class Producto extends ItemCompra {

	private int activo;
	private int capacidad;
	private String cordenadas;
	private String descripcion;
	private String nombre;
	private BigDecimal valor;
	private Categoria m_Categoria;
	private Ciudad m_Ciudad;
	private ItemCompra m_ItemCompra;
	private Contenido m_Contenido;
	private Disponibilidad m_Disponibilidad;
	

	public int getActivo() {
		return activo;
	}

	public void setActivo(int activo) {
		this.activo = activo;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
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

	public Categoria getM_Categoria() {
		return m_Categoria;
	}

	public void setM_Categoria(Categoria m_Categoria) {
		this.m_Categoria = m_Categoria;
	}

	public Ciudad getM_Ciudad() {
		return m_Ciudad;
	}

	public void setM_Ciudad(Ciudad m_Ciudad) {
		this.m_Ciudad = m_Ciudad;
	}

	public ItemCompra getM_ItemCompra() {
		return m_ItemCompra;
	}

	public void setM_ItemCompra(ItemCompra m_ItemCompra) {
		this.m_ItemCompra = m_ItemCompra;
	}

	public Contenido getM_Contenido() {
		return m_Contenido;
	}

	public void setM_Contenido(Contenido m_Contenido) {
		this.m_Contenido = m_Contenido;
	}

	public Disponibilidad getM_Disponibilidad() {
		return m_Disponibilidad;
	}

	public void setM_Disponibilidad(Disponibilidad m_Disponibilidad) {
		this.m_Disponibilidad = m_Disponibilidad;
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
