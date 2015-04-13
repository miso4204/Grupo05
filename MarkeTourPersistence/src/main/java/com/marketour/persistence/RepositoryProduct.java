package com.marketour.persistence;

import java.util.List;

import org.hibernate.Session;

import com.marketour.domain.*;
import com.marketour.hibernate.HibernateUtil;

public class RepositoryProduct extends Repository<Producto> {

	public RepositoryProduct() {
		super(Producto.class);
	}

	public List<Producto> FindByPackage(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		List<Producto> list = session
				.createSQLQuery(
						"SELECT prod.* FROM Producto prod INNER JOIN PaqueteProducto pp ON prod.id = pp.producto INNER JOIN Paquete pqt ON pqt.id = pp.paquete WHERE pqt.id = "
								+ id).addEntity(Producto.class).list();
		session.getTransaction().commit();
		return list;
	}

}
