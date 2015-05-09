package com.marketour.business;

public class Credenciales implements java.io.Serializable 
{

	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	private String usuario="";
	private String contrasena = "";

}
