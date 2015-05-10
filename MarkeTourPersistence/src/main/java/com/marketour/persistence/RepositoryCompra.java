package com.marketour.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import com.marketour.domain.Compra;
import com.marketour.domain.Producto;
import com.marketour.hibernate.HibernateUtil;


public class RepositoryCompra extends Repository<Compra>
{
	public RepositoryCompra() 
	{
		super(Compra.class);
	}
	public List<Compra> FindByUbicacion(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		List<Compra> list = session
				.createSQLQuery(
						"SELECT t1.* FROM Compra AS t1 INNER JOIN ItemCompra AS t2 ON t1.id = t2.compra INNER JOIN Producto AS t3 ON t2.producto = t3.id where t3.ubicacion="+ id).addEntity(Compra.class).list();
		session.getTransaction().commit();
		return list;
	}
}
