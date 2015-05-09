package com.marketour.business;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Producto implements Serializable {

	private Integer capacidad = 0;
	private String categoria = "";
	private String ciudad = "";
	private String ciudadDestino = "";
	private String ciudadOrigen = "";
	private List<com.marketour.business.Contenido> contenidos = new ArrayList<com.marketour.business.Contenido>();
	private String coordenadas = "";
	private String descripcion = "";
	private Integer estado = 0;
	private Date fechaEntrada = new Date();
	private Date fechaSalida = new Date();
	private Integer id = 0;
	private Integer idCategoria = 0;
	private Integer idCiudad = 0;
	private Integer idCiudadDestino = 0;
	private Integer idCiudadOrigen = 0;
	private Integer idDepartamento = 0;
	private Integer idDepartamentoDestino = 0;
	private Integer idDepartamentoOrigen = 0;
	private Integer idPlanAlimentacion = 0;
	private String nombre = "";
	private String planAlimentacion = "";
	private com.marketour.business.Proveedor proveedor = new com.marketour.business.Proveedor();
	private BigDecimal valor = BigDecimal.ZERO;
	private Integer visitas = 0;

	public static Producto ConvertToBProduct(
			com.marketour.domain.Producto domain) {
		Producto business = new Producto();
		business.setId(domain.getId());
		business.setNombre(domain.getNombre());
		business.setDescripcion(domain.getDescripcion());
		business.setValor(domain.getValor());
		business.setVisitas(domain.getVisitas());
		business.setCapacidad(domain.getCapacidad());
		business.setCoordenadas(domain.getCoordenadas());
		business.setEstado(domain.getEstado());
		// Category
		com.marketour.domain.Categoria category = domain.getCategoria();
		if (category != null) {
			business.setCategoria(category.getDescripcion());
			business.setIdCategoria(category.getId());
		}
		// Ciudad
		if (domain.getCiudad() != null) {
			business.setCiudad(domain.getCiudad().getDescripcion());
			business.setIdCiudad(domain.getCiudad().getId());
			business.setIdDepartamento(domain.getCiudad().getDepartamento()
					.getId());
		}
		// Content
		if (domain.getContenidos() != null) {
			List<com.marketour.business.Contenido> contentB = new ArrayList<com.marketour.business.Contenido>();
			for (com.marketour.domain.Contenido contentD : domain
					.getContenidos()) {
				contentB.add(new com.marketour.business.Contenido()
						.ConvertToBContent(contentD));
			}
			business.setContenidos(contentB);
		}
		return business;
	}

	public static com.marketour.domain.Producto ConvertToDBProducto(
			Producto business, com.marketour.domain.Producto domain) {
		domain.setNombre(business.getNombre());
		domain.setDescripcion(business.getDescripcion());
		domain.setValor(business.getValor());
		domain.setVisitas(business.getVisitas());
		domain.setCapacidad(business.getCapacidad());
		domain.setCoordenadas(business.getCoordenadas());
		domain.setEstado(business.getEstado());
		return domain;
	}

	public Integer getCapacidad() {
		return capacidad;
	}

	public String getCategoria() {
		return categoria;
	}

	public String getCiudad() {
		return ciudad;
	}

	public String getCiudadDestino() {
		return ciudadDestino;
	}

	public String getCiudadOrigen() {
		return ciudadOrigen;
	}

	public List<com.marketour.business.Contenido> getContenidos() {
		return contenidos;
	}

	public String getCoordenadas() {
		return coordenadas;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public Integer getEstado() {
		return estado;
	}

	public Date getFechaEntrada() {
		return fechaEntrada;
	}

	public Date getFechaSalida() {
		return fechaSalida;
	}

	public Integer getId() {
		return id;
	}

	public Integer getIdCategoria() {
		return idCategoria;
	}

	public Integer getIdCiudad() {
		return idCiudad;
	}

	public Integer getIdCiudadDestino() {
		return idCiudadDestino;
	}

	public Integer getIdCiudadOrigen() {
		return idCiudadOrigen;
	}

	public Integer getIdDepartamento() {
		return idDepartamento;
	}

	public Integer getIdDepartamentoDestino() {
		return idDepartamentoDestino;
	}

	public Integer getIdDepartamentoOrigen() {
		return idDepartamentoOrigen;
	}

	public Integer getIdPlanAlimentacion() {
		return idPlanAlimentacion;
	}

	public String getNombre() {
		return nombre;
	}

	public String getPlanAlimentacion() {
		return planAlimentacion;
	}

	public com.marketour.business.Proveedor getProveedor() {
		return proveedor;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public Integer getVisitas() {
		return visitas;
	}

	public void setCapacidad(Integer capacidad) {
		this.capacidad = capacidad;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public void setCiudadDestino(String ciudadDestino) {
		this.ciudadDestino = ciudadDestino;
	}

	public void setCiudadOrigen(String ciudadOrigen) {
		this.ciudadOrigen = ciudadOrigen;
	}

	public void setContenidos(List<com.marketour.business.Contenido> contenidos) {
		this.contenidos = contenidos;
	}

	public void setCoordenadas(String coordenadas) {
		this.coordenadas = coordenadas;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public void setFechaEntrada(Date fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}

	public void setIdCiudad(Integer idCiudad) {
		this.idCiudad = idCiudad;
	}

	public void setIdCiudadDestino(Integer idCiudadDestino) {
		this.idCiudadDestino = idCiudadDestino;
	}

	public void setIdCiudadOrigen(Integer idCiudadOrigen) {
		this.idCiudadOrigen = idCiudadOrigen;
	}

	public void setIdDepartamento(Integer idDepartamento) {
		this.idDepartamento = idDepartamento;
	}

	public void setIdDepartamentoDestino(Integer idDepartamentoDestino) {
		this.idDepartamentoDestino = idDepartamentoDestino;
	}

	public void setIdDepartamentoOrigen(Integer idDepartamentoOrigen) {
		this.idDepartamentoOrigen = idDepartamentoOrigen;
	}

	public void setIdPlanAlimentacion(Integer idPlanAlimentacion) {
		this.idPlanAlimentacion = idPlanAlimentacion;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setPlanAlimentacion(String planAlimentacion) {
		this.planAlimentacion = planAlimentacion;
	}

	public void setProveedor(com.marketour.business.Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public void setVisitas(Integer visitas) {
		this.visitas = visitas;
	}
}
