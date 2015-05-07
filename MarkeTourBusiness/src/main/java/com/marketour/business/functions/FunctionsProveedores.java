package com.marketour.business.functions;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.marketour.business.Proveedor;
import com.marketour.hibernate.HibernateUtil;
import com.marketour.persistence.RepositoryUser;

class FunctionsProveedores extends FunctionsUsers
{

	public Object Consultar(int id) {
		com.marketour.persistence.Repository<com.marketour.domain.Proveedor> repository;
		repository = new com.marketour.persistence.Repository<com.marketour.domain.Proveedor>(
				com.marketour.domain.Proveedor.class);
		com.marketour.domain.Proveedor proveedor = repository.FindById(id);
		Proveedor bProveedor = new Proveedor();
		bProveedor.setDescripcion(proveedor.getDescripcion());
		bProveedor.setId(proveedor.getId());
		bProveedor.setCuenta(proveedor.getCuenta());
		bProveedor.setNit(proveedor.getNit());
		return bProveedor;

	}

	public List<Object> ConsultarLista() {
		com.marketour.persistence.Repository<com.marketour.domain.Proveedor> repository;
		repository = new com.marketour.persistence.Repository<com.marketour.domain.Proveedor>(
				com.marketour.domain.Proveedor.class);
		List<com.marketour.domain.Proveedor> lstProveedor = repository.FindAll();
		List<Object> ListaProveedor = new ArrayList<Object>();
		for (com.marketour.domain.Proveedor proveedor2 : lstProveedor) {
			ListaProveedor.add(Proveedor.ConvertToBProveedor(proveedor2));
		}
		return ListaProveedor;
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

}
