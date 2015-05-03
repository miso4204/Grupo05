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
	
	public List<Producto> FindProducts(String fechaInicio,String fechaFin,double precio1,double precio2,int idCiudad) {
		String rangoFechas="";
		String innerDisponibilidad="";
		if (!fechaInicio.equals("0") && !fechaFin.equals("0")){
			innerDisponibilidad=" INNER JOIN Disponibilidad dp ON dp.producto=prod.id ";
			rangoFechas=" AND dp.fechaInicio>='"+fechaInicio+"' AND dp.fechaFin<='"+fechaFin+"' ";
		}
		String rangoPrecios="";
		if (precio1>0 && precio2>0){
			rangoPrecios=" AND prod.valor>="+precio1+" and prod.valor<="+precio2;
		}
		String ciudad="";
		if (idCiudad>0){
			ciudad=" and prod.ubicacion="+idCiudad;
		}
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		List<Producto> list = session
				.createSQLQuery(
						"SELECT prod.* FROM Producto prod "+innerDisponibilidad+" WHERE 1=1 "+rangoFechas+
						rangoPrecios+ciudad).addEntity(Producto.class).list();
		session.getTransaction().commit();
		return list;
	}
	
	
	public List<Producto> FindProductsPorPaquete(String fechaInicio,String fechaFin,double precio1,double precio2,int idCiudad,int idPaquete) {
		String rangoFechas="";
		String innerDisponibilidad="";
		if (!fechaInicio.equals("0") && !fechaFin.equals("0")){
			innerDisponibilidad=" INNER JOIN Disponibilidad dp ON dp.producto=prod.id ";
			rangoFechas=" AND dp.fechaInicio>='"+fechaInicio+"' AND dp.fechaFin<='"+fechaFin+"' ";
		}
		String rangoPrecios="";
		if (precio1>0 && precio2>0){
			rangoPrecios=" AND prod.valor>="+precio1+" and prod.valor<="+precio2;
		}
		rangoPrecios=""; //El filtro sel valor se hace en el paquete, por ahora esta variable se deja vacia con posibles modificaciones en el futuro.
		String ciudad="";
		if (idCiudad>0){
			ciudad=" and prod.ubicacion="+idCiudad;
		}
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		List<Producto> list = session
				.createSQLQuery(
						"SELECT prod.* FROM Producto prod "+innerDisponibilidad+" INNER JOIN Paquete_Producto pp ON prod.id = pp.producto "+
				        "INNER JOIN Paquete pqt ON pqt.id = pp.paquete WHERE pqt.id = "+idPaquete+rangoFechas+
						rangoPrecios+ciudad).addEntity(Producto.class).list();
		session.getTransaction().commit();
		return list;
	}
}
