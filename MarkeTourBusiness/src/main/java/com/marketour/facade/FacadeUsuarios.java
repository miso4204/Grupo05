package com.marketour.facade;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.hibernate.Session;

import com.marketour.business.Administrador;
import com.marketour.business.Cliente;
import com.marketour.business.Credenciales;
import com.marketour.business.Moneda;
import com.marketour.business.Proveedor;
import com.marketour.business.Usuario;
import com.marketour.business.functions.FactoryUsers;
import com.marketour.domain.Compra;
import com.marketour.domain.MedioPago;
import com.marketour.hibernate.HibernateUtil;
import com.marketour.persistence.Repository;
import com.marketour.persistence.RepositoryUser;

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

	public static Proveedor ConsultarProveedor(int id) {
		FactoryUsers users = new FactoryUsers("Proveedor");
		return (Proveedor) users.Consultar(id);
	}

	public static List<Usuario> ConsultarUsuarios() {
		FactoryUsers users = new FactoryUsers("");
		return (List<Usuario>) (List<?>) users.ConsultarLista();
	}

	public static boolean RegistrarCliente(Cliente cliente) {

		RepositoryUser repositoryUser = new com.marketour.persistence.RepositoryUser();
		com.marketour.persistence.Repository<com.marketour.domain.Cliente> repositoryClient = new com.marketour.persistence.Repository<com.marketour.domain.Cliente>(
				com.marketour.domain.Cliente.class);

		com.marketour.domain.Cliente dbCliente = new com.marketour.domain.Cliente();
		com.marketour.domain.Usuario dbUsuario = new com.marketour.domain.Usuario();

		Session session = HibernateUtil.getSessionFactory().openSession();

		dbCliente.setDescripcion(cliente.getDescripcion());
		dbCliente.setId(cliente.getId());
		dbCliente.setMedioPagos(new HashSet<MedioPago>(0));
		dbCliente.setCompras(new HashSet<Compra>(0));

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
			dbCliente.setUsuario(repositoryUser.Persist(dbUsuario));

			repositoryClient.Persist(dbCliente);
			session.getTransaction().commit();
			return true;

		} catch (Exception ex) {
			return false;
		}
	}

	public static Boolean RegistrarProveedor(Proveedor proveedor) {
		Boolean registro = false;

		RepositoryUser repositoryUser = new com.marketour.persistence.RepositoryUser();
		com.marketour.persistence.Repository<com.marketour.domain.Proveedor> repositoryProveedor = new com.marketour.persistence.Repository<com.marketour.domain.Proveedor>(
				com.marketour.domain.Proveedor.class);

		com.marketour.domain.Proveedor dbProveedor = new com.marketour.domain.Proveedor();
		com.marketour.domain.Usuario dbUsuario = new com.marketour.domain.Usuario();

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
		try {
			session.beginTransaction();
			repositoryUser.Persist(dbUsuario);
			repositoryProveedor.Persist(dbProveedor);
			session.getTransaction().commit();
			registro = true;
		} catch (Exception ex) {
			registro = false;
		}
		return registro;
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
}