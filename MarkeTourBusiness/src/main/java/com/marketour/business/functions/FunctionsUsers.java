package com.marketour.business.functions;


import java.util.List;

import com.marketour.domain.Usuario;
import com.marketour.persistence.Repository;


abstract class  FunctionsUsers 
{
	abstract Object Consultar(int id);
	abstract List<Object> ConsultarLista();
	Boolean CambiarContrasena(String usuario, String contrasena)
	{
		Repository repositorio= new com.marketour.persistence.RepositoryUser();
		com.marketour.domain.Usuario dUsuario= (com.marketour.domain.Usuario) repositorio.FindByColumn("login = '" + usuario+"'").get(0);
		dUsuario.setPassword(contrasena);
		
		repositorio.Update(dUsuario);
		return true;
	}
	Object Autenticar(String usuario, String contrasena)
	{
		Repository repositorio= new com.marketour.persistence.RepositoryUser();
		if(repositorio.FindByColumn("login = '" + usuario+"' AND password='"+contrasena+"'").size()>0)
		{
			return true;
			
		}
		else
		{
			return false;
		}
		
		
		
		
	}
}