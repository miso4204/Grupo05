package com.marketour.business.functions;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.marketour.business.Cliente;
import com.marketour.domain.Usuario;
import com.marketour.hibernate.HibernateUtil;
import com.marketour.persistence.Repository;
import com.marketour.persistence.RepositoryUser;

 class FunctionsClientes extends FunctionsUsers 
{
	public Object Consultar(int id) 
	{
		com.marketour.persistence.Repository<com.marketour.domain.Cliente> repository;
		repository = new com.marketour.persistence.Repository<com.marketour.domain.Cliente>(
				com.marketour.domain.Cliente.class);
		com.marketour.domain.Cliente cliente = repository.FindById(id);
		Cliente bCliente = new Cliente();
		bCliente.setDescripcion(cliente.getDescripcion());
		bCliente.setId(cliente.getId());

		return Cliente.ConvertToBCliente(cliente);
	}

	public List<Object> ConsultarLista() 
	{
		com.marketour.persistence.Repository<com.marketour.domain.Cliente> repository;
		repository = new com.marketour.persistence.Repository<com.marketour.domain.Cliente>(
				com.marketour.domain.Cliente.class);
		List<com.marketour.domain.Cliente> lstCliente = repository.FindAll();
		List<Object> ListaCliente = new ArrayList<Object>();
		for (com.marketour.domain.Cliente cliente2 : lstCliente) {
			ListaCliente.add(Cliente.ConvertToBCliente(cliente2));
		}
		return ListaCliente;
	}
	
	public static com.marketour.persistence.Repository<com.marketour.domain.Cliente> RegistrarCliente(
			Cliente cliente) {

		RepositoryUser repositoryUser = new com.marketour.persistence.RepositoryUser();
		com.marketour.persistence.Repository<com.marketour.domain.Cliente> repositoryClient = new com.marketour.persistence.Repository<com.marketour.domain.Cliente>(
				com.marketour.domain.Cliente.class);

		com.marketour.domain.Cliente dbCliente = new com.marketour.domain.Cliente();
		com.marketour.domain.Usuario dbUsuario = new com.marketour.domain.Usuario();

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
		try {
			session.beginTransaction();
			dbCliente.setId(repositoryUser.Persist(dbUsuario).getId());
			repositoryClient.Persist(dbCliente);
			session.getTransaction().commit();

		} catch (Exception ex) {

		}
		return (Repository<com.marketour.domain.Cliente>) repositoryClient;

	}

	
	

	
}
