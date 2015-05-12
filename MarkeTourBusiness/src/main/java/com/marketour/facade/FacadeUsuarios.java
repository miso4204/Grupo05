package com.marketour.facade;

import java.util.ArrayList;
import java.util.List;

import com.marketour.business.Administrador;
import com.marketour.business.Cliente;
import com.marketour.business.Credenciales;
import com.marketour.business.Moneda;
import com.marketour.business.Proveedor;
import com.marketour.business.Usuario;
import com.marketour.business.functions.FactoryUsers;
import com.marketour.persistence.Annotation;
import com.marketour.persistence.Repository;

@Annotation(tipo = "Moneda")
public class FacadeUsuarios {

	public static Boolean Autenticar(Credenciales credenciales) {
		FactoryUsers users = new FactoryUsers("Usuario");
		return users.Autenticar(credenciales.getUsuario(),
				credenciales.getContrasena());
	}

	public static Boolean CambiarContrasena(Credenciales credenciales) {
		FactoryUsers users = new FactoryUsers("Usuario");
		return users.CambiarContrasena(credenciales.getUsuario(),
				credenciales.getContrasena());
	}

	public static Boolean CambiarDireccion(
			com.marketour.business.Usuario usuario) {
		FactoryUsers users = new FactoryUsers("Usuario");
		return users.CambiarDireccion(usuario);
	}

	public static Administrador ConsultarAdministrador(int id) {
		FactoryUsers users = new FactoryUsers("Administrador");
		return (Administrador) users.Consultar(id);
	}

	public static Cliente ConsultarCliente(int id) {
		FactoryUsers users = new FactoryUsers("Cliente");
		return (Cliente) users.Consultar(id);
	}

	public static List<Cliente> ConsultarClientes() {
		FactoryUsers users = new FactoryUsers("Cliente");
		return (List<Cliente>) (List<?>) users.ConsultarLista();
	}

	public static Object ConsultarMonedaTodos() {
		List<Moneda> business = new ArrayList<Moneda>();
		List<com.marketour.domain.Moneda> domain = new ArrayList<com.marketour.domain.Moneda>();
		Repository<com.marketour.domain.Moneda> repository = new Repository<com.marketour.domain.Moneda>(
				com.marketour.domain.Moneda.class);
		domain = repository.FindAll();
		for (com.marketour.domain.Moneda item : domain) {

			business.add(Moneda.ConvertToBMoneda(item));
		}
		return business;
	}

	public static Proveedor ConsultarProveedor(int id) {
		FactoryUsers users = new FactoryUsers("Proveedor");
		return (Proveedor) users.Consultar(id);
	}

	public static Usuario ConsultarUsuario(int id) {
		FactoryUsers users = new FactoryUsers("");
		return (Usuario) users.Consultar(id);
	}

	public static List<Usuario> ConsultarUsuarios() {
		FactoryUsers users = new FactoryUsers("");
		return (List<Usuario>) (List<?>) users.ConsultarLista();
	}

	public static Cliente RegistrarCliente(Cliente business) {
		com.marketour.persistence.Repository<com.marketour.domain.Cliente> repository = new com.marketour.persistence.Repository<com.marketour.domain.Cliente>(
				com.marketour.domain.Cliente.class);

		com.marketour.persistence.Repository<com.marketour.domain.Usuario> repositoryUser = new com.marketour.persistence.Repository<com.marketour.domain.Usuario>(
				com.marketour.domain.Usuario.class);

		com.marketour.domain.Usuario domainUser = repositoryUser
				.FindById(business.getId());
		com.marketour.domain.Cliente domain = domainUser.getCliente();
		boolean update = domain != null;
		domain = repository.FindById(business.getId());
		domain = Cliente.ConvertToBDCliente(business);
		domain.setUsuario(domainUser);
		if (update)
			repository.Update(domain);
		else
			repository.Save(domain);
		return ConsultarCliente(domain.getId());
	}

	public static Object RegistrarProveedor(Proveedor business) {
		com.marketour.persistence.Repository<com.marketour.domain.Proveedor> repository = new com.marketour.persistence.Repository<com.marketour.domain.Proveedor>(
				com.marketour.domain.Proveedor.class);

		com.marketour.persistence.Repository<com.marketour.domain.Usuario> repositoryUser = new com.marketour.persistence.Repository<com.marketour.domain.Usuario>(
				com.marketour.domain.Usuario.class);

		com.marketour.domain.Usuario domainUser = repositoryUser
				.FindById(business.getId());
		com.marketour.domain.Proveedor domain = domainUser.getProveedor();
		boolean update = domain != null;
		domain = repository.FindById(business.getId());
		domain = Proveedor.ConvertToBDProveedor(business);
		domain.setUsuario(domainUser);
		if (update)
			repository.Update(domain);
		else
			repository.Save(domain);
		return ConsultarProveedor(domain.getId());
	}

	public static Usuario RegistrarUsuario(Usuario business) {
		com.marketour.persistence.Repository<com.marketour.domain.Usuario> repository = new com.marketour.persistence.Repository<com.marketour.domain.Usuario>(
				com.marketour.domain.Usuario.class);
		com.marketour.persistence.Repository<com.marketour.domain.Moneda> repoCurrency = new com.marketour.persistence.Repository<com.marketour.domain.Moneda>(
				com.marketour.domain.Moneda.class);
		com.marketour.domain.Usuario domain = null;
		if (business.getId() > 0) {
			domain = repository.FindById(business.getId());
			domain = Usuario.ConvertToBDUsuario(business);
			if (business.getIdMoneda() > 0) {
				domain.setMoneda(repoCurrency.FindById(business.getIdMoneda()));
			}
			repository.Update(domain);
		} else {
			domain = Usuario.ConvertToBDUsuario(business);
			if (business.getIdMoneda() > 0) {
				domain.setMoneda(repoCurrency.FindById(business.getIdMoneda()));
			}
			repository.Save(domain);
		}
		return ConsultarUsuario(domain.getId());
	}
}