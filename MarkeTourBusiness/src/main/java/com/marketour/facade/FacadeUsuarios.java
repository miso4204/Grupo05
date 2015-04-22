package com.marketour.facade;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import com.marketour.persistence.*;
import com.marketour.business.Administrador;
import com.marketour.business.Cliente;
import com.marketour.business.Proveedor;
import com.marketour.domain.Compra;
import com.marketour.domain.Usuario;
import com.marketour.hibernate.HibernateUtil;
import com.marketour.persistence.Repository;
import com.marketour.persistence.RepositoryUser;

public class FacadeUsuarios 
{
	
	
	public FacadeUsuarios() 
	{
		
	}
	
	public static Cliente ConsultarCliente(int id)
	{
		com.marketour.persistence.Repository<com.marketour.domain.Cliente> repository;
		repository = new com.marketour.persistence.Repository<com.marketour.domain.Cliente>(com.marketour.domain.Cliente.class);
		com.marketour.domain.Cliente cliente=repository.FindById(id);
		Cliente bCliente=new Cliente();
		bCliente.setDescripcion(cliente.getDescripcion());
		bCliente.setId(cliente.getId());
		
		return bCliente;
		
	}
	public static List<Cliente> ConsultarClientes()
	{
		com.marketour.persistence.Repository<com.marketour.domain.Cliente> repository;
		repository = new com.marketour.persistence.Repository<com.marketour.domain.Cliente>(com.marketour.domain.Cliente.class);
		List<com.marketour.domain.Cliente> lstCliente=repository.FindAll();
		List<com.marketour.business.Cliente> ListaCliente=new ArrayList<Cliente>();
		for (com.marketour.domain.Cliente cliente2 : lstCliente) 
		{	
			
			Cliente bCliente=new Cliente();
			bCliente.setDescripcion(cliente2.getDescripcion());
			bCliente.setId(cliente2.getId());
			bCliente.setCelular(cliente2.getUsuario().getCelular());
			bCliente.setCorreo(cliente2.getUsuario().getCorreo());
			bCliente.setDireccion(cliente2.getUsuario().getCorreo());
			bCliente.setEstado(cliente2.getUsuario().getEstado());
			bCliente.setLogin(cliente2.getUsuario().getLogin());
			bCliente.setNombre(cliente2.getUsuario().getNombre());
			bCliente.setTelefono(cliente2.getUsuario().getTelefono());
			ListaCliente.add(bCliente);
		}
		return ListaCliente;
		
	}
	public static Proveedor ConsultarProveedor(int id)
	{
		com.marketour.persistence.Repository<com.marketour.domain.Proveedor> repository;
		repository = new com.marketour.persistence.Repository<com.marketour.domain.Proveedor>(com.marketour.domain.Proveedor.class);
		com.marketour.domain.Proveedor proveedor=repository.FindById(id);
		Proveedor bProveedor=new Proveedor();
		bProveedor.setDescripcion(proveedor.getDescripcion());
		bProveedor.setId(proveedor.getId());
		bProveedor.setCuenta(proveedor.getCuenta());
		bProveedor.setNit(proveedor.getNit());
		return bProveedor;
		
	}
	public  static Administrador ConsultarAdministrador(int id)
	{
		com.marketour.persistence.Repository<com.marketour.domain.Administrador> repository;
		repository = new com.marketour.persistence.Repository<com.marketour.domain.Administrador>(com.marketour.domain.Administrador.class);
		com.marketour.domain.Administrador administrador=repository.FindById(id);
		Administrador bAdministrador=new Administrador();
		bAdministrador.setDescripcion(administrador.getDescripcion());
		bAdministrador.setId(administrador.getId());
		
		return bAdministrador;
		
	}
	

	public static Map<String, String> authenticate(String login, String pass)
	{
		RepositoryUser repository=new com.marketour.persistence.RepositoryUser();
		return repository.authenticate(login, pass);
	}
		
	public  static com.marketour.persistence.Repository<com.marketour.domain.Cliente> RegistrarCliente(Cliente cliente)
	{
		
		
		RepositoryUser repositoryUser=new com.marketour.persistence.RepositoryUser();
		com.marketour.persistence.Repository<com.marketour.domain.Cliente> repositoryClient=new com.marketour.persistence.Repository<com.marketour.domain.Cliente>(com.marketour.domain.Cliente.class);
				
		com.marketour.domain.Cliente dbCliente= new com.marketour.domain.Cliente();
		com.marketour.domain.Usuario dbUsuario=new com.marketour.domain.Usuario();
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		dbCliente.setDescripcion(cliente.getDescripcion());
		dbCliente.setId(cliente.getId());
		
		dbUsuario.setCelular(cliente.getCelular());
		dbUsuario.setCorreo(cliente.getCorreo());
		dbUsuario.setDireccion(cliente.getDireccion());
		dbUsuario.setEstado(cliente.getEstado());
		dbUsuario.setLogin(cliente.getLogin());
		dbUsuario.setNombre(cliente.getNombre());
		dbUsuario.setPassword(cliente.getPassword());
		dbUsuario.setTelefono(cliente.getTelefono());
		try
		{
			session.beginTransaction();
			dbCliente.setId(repositoryUser.Persist(dbUsuario).getId());
			repositoryClient.Persist(dbCliente);
			session.getTransaction().commit();
			
		}
		catch( Exception ex)
		{
			
		}
		return (Repository<com.marketour.domain.Cliente>) repositoryClient;
		
	}
	
	
	public  static Boolean RegistrarProveedor(Proveedor proveedor)
	{
		Boolean registro=false;
		
		RepositoryUser repositoryUser=new com.marketour.persistence.RepositoryUser();
		com.marketour.persistence.Repository<com.marketour.domain.Proveedor> repositoryProveedor=new com.marketour.persistence.Repository<com.marketour.domain.Proveedor>(com.marketour.domain.Proveedor.class);
				
		com.marketour.domain.Proveedor dbProveedor= new com.marketour.domain.Proveedor();
		com.marketour.domain.Usuario dbUsuario=new com.marketour.domain.Usuario();
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		dbProveedor.setDescripcion(proveedor.getDescripcion());
		dbProveedor.setId(proveedor.getId());
		
		dbUsuario.setCelular(proveedor.getCelular());
		dbUsuario.setCorreo(proveedor.getCelular());
		dbUsuario.setDireccion(proveedor.getCelular());
		dbUsuario.setEstado(proveedor.getEstado());
		dbUsuario.setLogin(proveedor.getLogin());
		dbUsuario.setNombre(proveedor.getNombre());
		dbUsuario.setPassword(proveedor.getPassword());
		dbUsuario.setTelefono(proveedor.getTelefono());
		try
		{
			session.beginTransaction();
			repositoryUser.Persist(dbUsuario);
			repositoryProveedor.Persist(dbProveedor);
			session.getTransaction().commit();
			registro=true;
		}
		catch( Exception ex)
		{
			registro=false;
		}
		return registro;
	
	}
	
}
