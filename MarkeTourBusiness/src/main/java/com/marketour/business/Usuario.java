package com.marketour.business;

import javax.persistence.Entity;
import javax.persistence.Table;



/**
 * @author Andres
 * @version 1.0
 * @created 13-abr.-2015 9:52:43 a. m.
 */
@Entity
@Table(name = "Usuario", catalog = "grupocre_marketour")
public class Usuario implements java.io.Serializable {

	private Integer id = 0;
	private String login = "";
	private String password = "";
	private String nombre = "";
	private String telefono = "";
	private String celular = "";
	private String correo = "";
	private String direccion = "";
	private int estado = 0;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}
	public static com.marketour.business.Usuario ConvertToBUsuario(Usuario usuario)
	{
		
		com.marketour.business.Usuario bUsuario=new com.marketour.business.Usuario();
		bUsuario.setCelular(usuario.getCelular());
		bUsuario.setCorreo(usuario.getCorreo());
		bUsuario.setDireccion(usuario.getDireccion());
		bUsuario.setEstado(usuario.getEstado());
		bUsuario.setId(usuario.getId());
		bUsuario.setLogin(usuario.getLogin());
		bUsuario.setNombre(usuario.getNombre());
		bUsuario.setPassword(usuario.getPassword());
		bUsuario.setTelefono(usuario.getTelefono());
		return bUsuario;
	}

}
