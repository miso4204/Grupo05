package com.marketour.facade;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.marketour.business.Cliente;
import com.marketour.business.Promocion;
import com.marketour.hibernate.HibernateUtil;
import com.marketour.persistence.Repository;
import com.marketour.persistence.RepositoryUser;

public class FacadePromo {
	
	public FacadePromo(){
	
	}
	
	public static List<Promocion> ConsultarPromosTodas()
	{
		List<Promocion> lstPromos=new ArrayList<Promocion>();
		List<com.marketour.domain.Promocion> lstdPromos=new ArrayList<com.marketour.domain.Promocion>();
		
		com.marketour.persistence.Repository<com.marketour.domain.Promocion> repository= new com.marketour.persistence.Repository<com.marketour.domain.Promocion>(com.marketour.domain.Promocion.class);
		lstdPromos=repository.FindAll();
		for (com.marketour.domain.Promocion promo : lstdPromos) 
		{
			Promocion prom=new Promocion();
			prom=Promocion.ConvertToBPromocion(promo);		
			//com.marketour.persistence.Repository<com.marketour.domain.Paquete> repository1= new com.marketour.persistence.Repository<com.marketour.domain.Paquete>(com.marketour.domain.Paquete.class);
			//repository1.FindByColumn("promo = " + promo.getId());
			lstPromos.add(prom);
		}
		return lstPromos;
	}	
	
	public static Promocion ConsultarPromocion(int id)
	{
		com.marketour.persistence.Repository<com.marketour.domain.Promocion> repository;
		repository = new com.marketour.persistence.Repository<com.marketour.domain.Promocion>(com.marketour.domain.Promocion.class);
		com.marketour.domain.Promocion promo=repository.FindById(id);
		if(promo!=null){
			return Promocion.ConvertToBPromocion(promo);
		} else {
			return null;
		}
		
	}

}
