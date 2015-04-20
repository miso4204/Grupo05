package com.marketour.persistence;

import java.util.List;

import org.hibernate.Session;

import com.marketour.hibernate.HibernateUtil;

@SuppressWarnings("unchecked")
public class Repository<T> {

	private Class<T> entityClass;

	public Repository(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	public List<T> FindAll() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		List<T> list = session.createCriteria(this.entityClass).list();
		session.getTransaction().commit();
		return list;
	}

	public List<T> FindByColumn(String filter) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		List<T> list = session.createQuery(
				"FROM "
						+ this.entityClass.toString()
								.substring(
										this.entityClass.toString()
												.lastIndexOf(".") + 1,
										this.entityClass.toString().length())
						+ " WHERE " + filter).list();
		session.getTransaction().commit();
		return list;
	}

	public T FindById(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		T entity = (T) session.get(this.entityClass, id);
		session.getTransaction().commit();
		return entity;
	}

	public T Persist(T entity) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.saveOrUpdate(entity);
		session.getTransaction().commit();
		return entity;
	}

	public void Delete(int id) {
		T entity = FindById(id);
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.delete(entity);
		session.getTransaction().commit();
	}
}
