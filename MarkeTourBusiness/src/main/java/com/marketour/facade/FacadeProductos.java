package com.marketour.facade;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import com.marketour.persistence.*;
import com.marketour.business.Producto;
import com.marketour.business.Paquete;
import com.marketour.hibernate.HibernateUtil;
import com.marketour.persistence.Repository;
import com.marketour.persistence.RepositoryUser;

public class FacadeProductos 
{	
	
	public FacadeProductos() 
	{
		
	}
	
	public static List<Paquete> ConsultarPaquetesTop(int cantidad)
	{
		List<Paquete> lstPaquete=new ArrayList<Paquete>();
		
		return lstPaquete;
	}
	public static List<Producto> ConsultarProductosXPaquetes(int idPaquete)
	{
		List<Producto> lstProducto=new ArrayList<Producto>();
		
		List<com.marketour.domain.Producto> lstdProducto=new ArrayList<com.marketour.domain.Producto>();
		
		com.marketour.persistence.Repository<com.marketour.domain.Producto> repository= new com.marketour.persistence.Repository<com.marketour.domain.Producto>(com.marketour.domain.Producto.class);
		lstdProducto=repository.FindByColumn("paquetes = " + idPaquete);
		for (com.marketour.domain.Producto producto : lstdProducto) 
		{
			Producto pac=new Producto();
			pac=Producto.ConvertToBProduct(producto);			
			
			
			lstProducto.add(pac);
		}
		
		return lstProducto;
	}
	public static List<Producto> ConsultarProductosXProveedor(int idProveedor)
	{
		List<Producto> lstProducto=new ArrayList<Producto>();
		
		List<com.marketour.domain.Producto> lstdProducto=new ArrayList<com.marketour.domain.Producto>();
		
		com.marketour.persistence.Repository<com.marketour.domain.Producto> repository= new com.marketour.persistence.Repository<com.marketour.domain.Producto>(com.marketour.domain.Producto.class);
		lstdProducto=repository.FindByColumn("proveedor = " + idProveedor);
		for (com.marketour.domain.Producto producto : lstdProducto) 
		{
			Producto pac=new Producto();
			pac=Producto.ConvertToBProduct(producto);			
			
			
			lstProducto.add(pac);
		}
		
		return lstProducto;
	}
	public static Paquete ConsultarPaquete(int id)
	{
		Paquete paquete=new Paquete();
		com.marketour.persistence.Repository<com.marketour.domain.Paquete> repository= new com.marketour.persistence.Repository<com.marketour.domain.Paquete>(com.marketour.domain.Paquete.class);
		return Paquete.ConvertToBPaquete(repository.FindById(id));
	}
	public static List<Paquete> ConsultarPaquetesXNombre(String nombre)
	{
		List<Paquete> lstPaquete=new ArrayList<Paquete>();
		List<com.marketour.domain.Paquete> lstdPaquete=new ArrayList<com.marketour.domain.Paquete>();
		
		com.marketour.persistence.Repository<com.marketour.domain.Paquete> repository= new com.marketour.persistence.Repository<com.marketour.domain.Paquete>(com.marketour.domain.Paquete.class);
		lstdPaquete=repository.FindByColumn("nombre LIKE '%" + nombre + "%'");
		for (com.marketour.domain.Paquete paquete : lstdPaquete) 
		{
			Paquete pac=new Paquete();
			pac=Paquete.ConvertToBPaquete(paquete);			
			com.marketour.persistence.Repository<com.marketour.domain.Producto> repository1= new com.marketour.persistence.Repository<com.marketour.domain.Producto>(com.marketour.domain.Producto.class);
			//repository1.FindByColumn("paquete = " + paquete.getId());
			lstPaquete.add(pac);
		}
		return lstPaquete;
	}
	public static List<Producto> ConsultarProductoXNombre(String nombre)
	{
		List<Producto> lstProducto=new ArrayList<Producto>();
		List<com.marketour.domain.Producto> lstdProducto=new ArrayList<com.marketour.domain.Producto>();
		
		com.marketour.persistence.Repository<com.marketour.domain.Producto> repository= new com.marketour.persistence.Repository<com.marketour.domain.Producto>(com.marketour.domain.Producto.class);
		lstdProducto=repository.FindByColumn("nombre LIKE '%" + nombre + "%'");
		for (com.marketour.domain.Producto producto : lstdProducto) 
		{
			Producto pac=new Producto();
			pac=Producto.ConvertToBProduct(producto);			
			lstProducto.add(pac);
		}
		return lstProducto;
	}
	public static Producto ConsultarProducto(int id)
	{
				
		com.marketour.persistence.Repository<com.marketour.domain.Producto> repository= new com.marketour.persistence.Repository<com.marketour.domain.Producto>(com.marketour.domain.Producto.class);
		return Producto.ConvertToBProduct(repository.FindById(id));
		
	}
	public static List<Paquete> ConsultarPaquetesXProveedor(int idProveedor)
	{
		List<Paquete> lstPaquete=new ArrayList<Paquete>();
		List<com.marketour.domain.Paquete> lstdPaquete=new ArrayList<com.marketour.domain.Paquete>();
		
		com.marketour.persistence.Repository<com.marketour.domain.Paquete> repository= new com.marketour.persistence.Repository<com.marketour.domain.Paquete>(com.marketour.domain.Paquete.class);
		lstdPaquete=repository.FindByColumn("proveedor = " + idProveedor);
		for (com.marketour.domain.Paquete paquete : lstdPaquete) 
		{
			Paquete pac=new Paquete();
			pac=Paquete.ConvertToBPaquete(paquete);			
			com.marketour.persistence.Repository<com.marketour.domain.Producto> repository1= new com.marketour.persistence.Repository<com.marketour.domain.Producto>(com.marketour.domain.Producto.class);
			//repository1.FindByColumn("paquete = " + paquete.getId());
			lstPaquete.add(pac);
		}
		return lstPaquete;
	}
	public static List<Paquete> ConsultarPaquetesTodos()
	{
		List<Paquete> lstPaquete=new ArrayList<Paquete>();
		List<com.marketour.domain.Paquete> lstdPaquete=new ArrayList<com.marketour.domain.Paquete>();
		
		com.marketour.persistence.Repository<com.marketour.domain.Paquete> repository= new com.marketour.persistence.Repository<com.marketour.domain.Paquete>(com.marketour.domain.Paquete.class);
		lstdPaquete=repository.FindAll();
		for (com.marketour.domain.Paquete paquete : lstdPaquete) 
		{
			Paquete pac=new Paquete();
			pac=Paquete.ConvertToBPaquete(paquete);			
			com.marketour.persistence.Repository<com.marketour.domain.Producto> repository1= new com.marketour.persistence.Repository<com.marketour.domain.Producto>(com.marketour.domain.Producto.class);
			//repository1.FindByColumn("paquete = " + paquete.getId());
			lstPaquete.add(pac);
		}
		return lstPaquete;
	}
}
