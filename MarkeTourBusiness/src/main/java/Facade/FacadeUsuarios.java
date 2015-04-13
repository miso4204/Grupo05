package Facade;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.marketour.persistence.*;
import com.marketour.business.Administrador;
import com.marketour.business.Cliente;
import com.marketour.business.Proveedor;
import com.marketour.domain.Compra;
import com.marketour.domain.Usuario;
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
		
	
}
