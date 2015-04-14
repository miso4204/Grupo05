package com.marketour.business;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Andres
 * @version 1.0
 * @created 13-abr.-2015 9:52:43 a. m.
 */
@Entity
@Table(name="Usuario"
    ,catalog="grupocre_marketour"
)
public class Usuario implements java.io.Serializable {

		
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

	private Integer id;
    private String login;
    private String password;
    private String nombre;
    private String telefono;
    private String celular;
    private String correo;
    private String direccion;
    private int estado;
    
}
