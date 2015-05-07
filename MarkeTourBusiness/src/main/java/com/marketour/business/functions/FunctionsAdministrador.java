package com.marketour.business.functions;

import java.util.List;

import com.marketour.business.Administrador;

class FunctionsAdministrador extends FunctionsUsers 
{

	public Object Consultar(int id) {
		com.marketour.persistence.Repository<com.marketour.domain.Administrador> repository;
		repository = new com.marketour.persistence.Repository<com.marketour.domain.Administrador>(
				com.marketour.domain.Administrador.class);
		com.marketour.domain.Administrador administrador = repository
				.FindById(id);
		Administrador bAdministrador = new Administrador();
		bAdministrador.setDescripcion(administrador.getDescripcion());
		bAdministrador.setId(administrador.getId());

		return bAdministrador;
	}

	public List<Object> ConsultarLista() {
		// TODO Auto-generated method stub
		return null;
	}

}
