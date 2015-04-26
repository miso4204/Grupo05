package com.marketour.domain;

// Generated 21/04/2015 08:03:17 PM by Hibernate Tools 4.0.0

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Producto generated by hbm2java
 */
@Entity
@Table(name = "Producto", catalog = "devfloor_marketour")
public class Producto implements java.io.Serializable {

	private Integer id;
	private Proveedor proveedor;
	private Ciudad ciudad;
	private Categoria categoria;
	private String nombre;
	private String descripcion;
	private BigDecimal valor;
	private Integer visitas;
	private Integer capacidad;
	private String coordenadas;
	private int estado;
	private Set<Disponibilidad> disponibilidads = new HashSet<Disponibilidad>(0);
	private Set<ItemCompra> itemCompras = new HashSet<ItemCompra>(0);
	private Set<Paquete> paquetes = new HashSet<Paquete>(0);
	private Set<Contenido> contenidos = new HashSet<Contenido>(0);

	public Producto() {
	}

	public Producto(Proveedor proveedor, Categoria categoria, String nombre,
			BigDecimal valor, int estado) {
		this.proveedor = proveedor;
		this.categoria = categoria;
		this.nombre = nombre;
		this.valor = valor;
		this.estado = estado;
	}

	public Producto(Proveedor proveedor, Ciudad ciudad, Categoria categoria,
			String nombre, String descripcion, BigDecimal valor,
			Integer visitas, Integer capacidad, String coordenadas, int estado,
			Set<Disponibilidad> disponibilidads, Set<ItemCompra> itemCompras,
			Set<Paquete> paquetes, Set<Contenido> contenidos) {
		this.proveedor = proveedor;
		this.ciudad = ciudad;
		this.categoria = categoria;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.valor = valor;
		this.visitas = visitas;
		this.capacidad = capacidad;
		this.coordenadas = coordenadas;
		this.estado = estado;
		this.disponibilidads = disponibilidads;
		this.itemCompras = itemCompras;
		this.paquetes = paquetes;
		this.contenidos = contenidos;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(cascade = javax.persistence.CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name = "proveedor", nullable = false)
	public Proveedor getProveedor() {
		return this.proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	@ManyToOne(cascade = javax.persistence.CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name = "ubicacion")
	public Ciudad getCiudad() {
		return this.ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}

	@ManyToOne(cascade = javax.persistence.CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name = "categoria", nullable = false)
	public Categoria getCategoria() {
		return this.categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	@Column(name = "nombre", nullable = false, length = 50)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "descripcion")
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Column(name = "valor", nullable = false, precision = 10)
	public BigDecimal getValor() {
		return this.valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	@Column(name = "visitas")
	public Integer getVisitas() {
		return this.visitas;
	}

	public void setVisitas(Integer visitas) {
		this.visitas = visitas;
	}

	@Column(name = "capacidad")
	public Integer getCapacidad() {
		return this.capacidad;
	}

	public void setCapacidad(Integer capacidad) {
		this.capacidad = capacidad;
	}

	@Column(name = "coordenadas", length = 50)
	public String getCoordenadas() {
		return this.coordenadas;
	}

	public void setCoordenadas(String coordenadas) {
		this.coordenadas = coordenadas;
	}

	@Column(name = "estado", nullable = false)
	public int getEstado() {
		return this.estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	@OneToMany(cascade = javax.persistence.CascadeType.ALL, fetch=FetchType.LAZY, mappedBy = "producto")
	public Set<Disponibilidad> getDisponibilidads() {
		return this.disponibilidads;
	}

	public void setDisponibilidads(Set<Disponibilidad> disponibilidads) {
		this.disponibilidads = disponibilidads;
	}

	@OneToMany(cascade = javax.persistence.CascadeType.ALL, fetch=FetchType.LAZY, mappedBy = "producto")
	public Set<ItemCompra> getItemCompras() {
		return this.itemCompras;
	}

	public void setItemCompras(Set<ItemCompra> itemCompras) {
		this.itemCompras = itemCompras;
	}

	@ManyToMany(cascade = javax.persistence.CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinTable(name = "Paquete_Producto", catalog = "devfloor_marketour", joinColumns = { @JoinColumn(name = "producto", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "paquete", nullable = false, updatable = false) })
	public Set<Paquete> getPaquetes() {
		return this.paquetes;
	}

	public void setPaquetes(Set<Paquete> paquetes) {
		this.paquetes = paquetes;
	}

	@OneToMany(cascade = javax.persistence.CascadeType.ALL, fetch=FetchType.LAZY, mappedBy = "producto")
	public Set<Contenido> getContenidos() {
		return this.contenidos;
	}

	public void setContenidos(Set<Contenido> contenidos) {
		this.contenidos = contenidos;
	}

}
