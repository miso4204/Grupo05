package com.marketour.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import com.marketour.domain.Usuario;
import com.marketour.hibernate.HibernateUtil;

public class RepositoryUser extends Repository<Usuario> {

	public RepositoryUser() {
		super(Usuario.class);
	}
	
	public Usuario authenticate(String login, String pass){
		List<Usuario> usuarios = this.FindByColumn("login='"+login+"'");
		
		//El usuario no existe
		if(usuarios.size()==0){
			
			return null;
		}else{
			Usuario usuario = usuarios.get(0);
			if(usuario.getPassword().equals(pass)){
				
				return usuario;
			}
			
			return null;
		}
	}
}
