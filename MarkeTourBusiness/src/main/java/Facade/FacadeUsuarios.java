package Facade;


import com.marketour.persistence.*;
import com.marketour.business.Cliente;
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

}
