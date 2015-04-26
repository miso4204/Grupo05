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
		List<T> list = null;
		try {
			session.beginTransaction();
			list = session.createCriteria(this.entityClass).list();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return list;
	}

	public List<T> FindByColumn(String filter) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<T> list = null;
		try {
			session.beginTransaction();
			list = session.createQuery(
					"FROM "
							+ this.entityClass.toString().substring(
									this.entityClass.toString()
											.lastIndexOf(".") + 1,
									this.entityClass.toString().length())
							+ " WHERE " + filter).list();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return list;
	}

	public T FindById(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		T entity = null;
		try {
			session.beginTransaction();
			entity = (T) session.get(this.entityClass, id);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return entity;
	}

	public T Persist(T entity) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(entity);
			session.getTransaction().commit();
			session.flush();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}

		return entity;
	}

	public T Save(T entity) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.save(entity);
			session.getTransaction().commit();
			session.flush();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}

		return entity;
	}

	public T Update(T entity) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.merge(entity);
			session.getTransaction().commit();
			session.flush();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}

		return entity;
	}

	public void Delete(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			T entity = FindById(id);
			session.beginTransaction();
			session.delete(entity);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	}
}
