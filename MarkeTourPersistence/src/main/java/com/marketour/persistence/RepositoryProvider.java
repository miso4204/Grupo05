package com.marketour.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import com.marketour.domain.Proveedor;
import com.marketour.domain.Usuario;
import com.marketour.hibernate.HibernateUtil;

public class RepositoryProvider extends Repository<Proveedor> {

	public RepositoryProvider() {
		super(Proveedor.class);
	}
	
	public boolean getProviderById(int id){
		List<Proveedor> proveedor = this.FindByColumn("id="+id);
		if(proveedor.size()==0){
			return false;
		}else{
			return true;
		}
	}
}