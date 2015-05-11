package com.marketour.business.functions;

import java.util.ArrayList;
import java.util.List;

import com.marketour.business.Usuario;

class FunctionsUsuarios extends FunctionsUsers {

	public Object Consultar(int id) {
		com.marketour.persistence.Repository<com.marketour.domain.Usuario> repository;
		repository = new com.marketour.persistence.Repository<com.marketour.domain.Usuario>(
				com.marketour.domain.Usuario.class);
		com.marketour.domain.Usuario dusuario = repository.FindById(id);
		return Usuario.ConvertToBUsuario(dusuario);
	}

	public List<Object> ConsultarLista() {
		com.marketour.persistence.Repository<com.marketour.domain.Usuario> repository;
		repository = new com.marketour.persistence.Repository<com.marketour.domain.Usuario>(
				com.marketour.domain.Usuario.class);
		List<com.marketour.domain.Usuario> lstUsuario = repository.FindAll();
		List<Object> ListaUsuario = new ArrayList<Object>();
		for (com.marketour.domain.Usuario Usuario2 : lstUsuario) {
			ListaUsuario.add(Usuario.ConvertToBUsuario(Usuario2));
		}
		return ListaUsuario;
	}
}
