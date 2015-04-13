package Facade;


import com.marketour.persistence.*;
import com.marketour.business.Cliente;
import com.marketour.business.Proveedor;
import com.marketour.domain.Compra;
import com.marketour.persistence.Repository;
import com.marketour.persistence.RepositoryUser;

public class FacadeUsuarios 
{
	
	
	public FacadeUsuarios() 
	{
		
	}
	
	public Cliente ConsultarCliente(int id)
	{
		com.marketour.persistence.Repository<com.marketour.domain.Cliente> repository;
		repository = new com.marketour.persistence.Repository<com.marketour.domain.Cliente>(com.marketour.domain.Cliente.class);
		com.marketour.domain.Cliente cliente=repository.FindById(id);
		Cliente bCliente=new Cliente();
		bCliente.setDescripcion(cliente.getDescripcion());
		bCliente.setId(cliente.getId());
		
		return bCliente;
		
	}
	public Proveedor ConsultarProveedor(int id)
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

}
