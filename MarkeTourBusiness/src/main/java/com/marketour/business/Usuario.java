package com.marketour.business;

public class Usuario implements java.io.Serializable {

	private String celular = "";
	private String correo = "";
	private String direccion = "";
	private int estado = 0;
	private Integer id = 0;
	private Integer idMoneda = 0;
	private String login = "";
	private String moneda = "";
	private String nombre = "";
	private String password = "";
	private String telefono = "";
	private String tipoUsuario = "";

	public static com.marketour.domain.Usuario ConvertToBDUsuario(
			Usuario business) {
		com.marketour.domain.Usuario domain = new com.marketour.domain.Usuario();
		if (business != null) {
			domain.setId(business.getId());
			domain.setLogin(business.getLogin());
			domain.setNombre(business.getNombre());
			domain.setPassword(business.getPassword());
			domain.setTelefono(business.getTelefono());
			domain.setCelular(business.getCelular());
			domain.setCorreo(business.getCorreo());
			domain.setDireccion(business.getDireccion());
			domain.setEstado(business.getEstado());
		}
		return domain;
	}

	public static com.marketour.business.Usuario ConvertToBUsuario(
			com.marketour.domain.Usuario domain) {
		com.marketour.business.Usuario business = new com.marketour.business.Usuario();
		if (domain != null) {
			business.setId(domain.getId());
			business.setLogin(domain.getLogin());
			business.setNombre(domain.getNombre());
			business.setPassword(domain.getPassword());
			business.setTelefono(domain.getTelefono());
			business.setCelular(domain.getCelular());
			business.setCorreo(domain.getCorreo());
			business.setDireccion(domain.getDireccion());
			business.setEstado(domain.getEstado());
			if (domain.getMoneda() != null) {
				business.setMoneda(domain.getMoneda().getDescripcion());
				business.setIdMoneda(domain.getMoneda().getId());
			}
			if (domain.getAdministrador() != null)
				business.setTipoUsuario("Administrador");
			if (domain.getProveedor() != null)
				business.setTipoUsuario("Proveedor");
			if (domain.getCliente() != null)
				business.setTipoUsuario("Cliente");
		}
		return business;
	}

	public String getCelular() {
		return celular;
	}

	public String getCorreo() {
		return correo;
	}

	public String getDireccion() {
		return direccion;
	}

	public int getEstado() {
		return estado;
	}

	public Integer getId() {
		return id;
	}

	public Integer getIdMoneda() {
		return idMoneda;
	}

	public String getLogin() {
		return login;
	}

	public String getMoneda() {
		return moneda;
	}

	public String getNombre() {
		return nombre;
	}

	public String getPassword() {
		return password;
	}

	public String getTelefono() {
		return telefono;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setIdMoneda(Integer idMoneda) {
		this.idMoneda = idMoneda;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

}
