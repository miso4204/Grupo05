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
	
	public Map<String, String> authenticate(String login, String pass){
		List<Usuario> usuarios = this.FindByColumn("login='"+login+"'");
		HashMap<String, String> map = new HashMap<String, String>();
		//El usuario no existe
		if(usuarios.size()==0){
			map.put("response", "false");
			return map;
		}else{
			Usuario usuario = usuarios.get(0);
			if(usuario.getPassword().equals(pass)){
				map.put("response", "true");
				map.put("id", String.valueOf(usuario.getId()));
				map.put("username", usuario.getNombre());
				return map;
			}
			map.put("response", "false");
			return map;
		}
	}
}