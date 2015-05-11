package com.marketour.business;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Usuario", catalog = "grupocre_marketour")
public class Usuario implements java.io.Serializable {

	public static com.marketour.business.Usuario ConvertToBUsuario(
			com.marketour.domain.Usuario usuario) {
		com.marketour.business.Usuario bUsuario = new com.marketour.business.Usuario();
		bUsuario.setCelular(usuario.getCelular());
		bUsuario.setCorreo(usuario.getCorreo());
		bUsuario.setDireccion(usuario.getDireccion());
		bUsuario.setEstado(usuario.getEstado());
		bUsuario.setId(usuario.getId());
		bUsuario.setLogin(usuario.getLogin());
		bUsuario.setNombre(usuario.getNombre());
		bUsuario.setPassword(usuario.getPassword());
		bUsuario.setTelefono(usuario.getTelefono());
		if (usuario.getAdministrador() != null)
			bUsuario.setTipoUsuario("Administrador");
		if (usuario.getProveedor() != null)
			bUsuario.setTipoUsuario("Proveedor");
		if (usuario.getCliente() != null)
			bUsuario.setTipoUsuario("Cliente");
		return bUsuario;
	}

	private String celular = "";
	private String correo = "";
	private String direccion = "";
	private int estado = 0;
	private Integer id = 0;
	private String login = "";
	private String nombre = "";
	private String password = "";
	private String telefono = "";

	private String tipoUsuario = "";

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

	public String getLogin() {
		return login;
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

	public void setLogin(String login) {
		this.login = login;
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
