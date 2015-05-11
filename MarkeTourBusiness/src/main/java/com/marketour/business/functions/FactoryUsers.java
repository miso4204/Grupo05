package com.marketour.business.functions;

import java.util.List;

import com.marketour.business.Usuario;

public class FactoryUsers {

	private FunctionsUsers functionEntidad;

	public FactoryUsers(String entidad) {
		if (entidad == "Cliente") {
			functionEntidad = new FunctionsClientes();
		} else if (entidad == "Proveedor") {
			functionEntidad = new FunctionsProveedores();
		} else if (entidad == "Administrador") {
			functionEntidad = new FunctionsAdministrador();
		} else {
			functionEntidad = new FunctionsUsuarios();
		}
	}

	public Boolean Autenticar(String usuario, String contrasena) {
		if (true) {
			return (Boolean) functionEntidad.Autenticar(usuario, contrasena);
		} else {
			return false;
		}
	}

	public Boolean CambiarContrasena(String usuario, String contrasena) {
		if (true) {
			return functionEntidad.CambiarContrasena(usuario, contrasena);
		} else {
			return false;
		}
	}

	public Boolean CambiarDireccion(Usuario usuario) {
		if (true) {
			return functionEntidad.CambiarDireccion(usuario);
		} else {
			return false;
		}
	}

	public Object Consultar(int id) {
		return functionEntidad.Consultar(id);
	}

	public List<Object> ConsultarLista() {
		return functionEntidad.ConsultarLista();
	}
}
