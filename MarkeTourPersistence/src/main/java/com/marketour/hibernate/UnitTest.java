package com.marketour.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.marketour.domain.TipoContenido;
import com.marketour.persistence.Repository;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class UnitTest extends TestCase {

	public UnitTest(String testName) {
		super(testName);
	}

	public static Test suite() {
		return new TestSuite(UnitTest.class);
	}

	public void testFindAll() {
		Repository<TipoContenido> rep = new Repository<TipoContenido>(
				TipoContenido.class);
		assertTrue(rep.FindAll().size() > 0);
	}

	public void testFindByColumn() {
		Repository<TipoContenido> rep = new Repository<TipoContenido>(
				TipoContenido.class);
		assertTrue(rep.FindByColumn("descripcion = 'TEST'").size() > 0);
	}

	public void testFindById() {
		Repository<TipoContenido> rep = new Repository<TipoContenido>(
				TipoContenido.class);
		assertTrue(rep.FindById(2).getId() > 0);
	}

	public void testPersist() {
		Repository<TipoContenido> rep = new Repository<TipoContenido>(
				TipoContenido.class);
		TipoContenido TipoContenido = new TipoContenido();
		TipoContenido.setDescripcion("TEST");
		assertTrue(rep.Persist(TipoContenido).getId() > 0);
	}

	public void testDelete() {
		Repository<TipoContenido> rep = new Repository<TipoContenido>(
				TipoContenido.class);
		rep.Delete(1);
		assertTrue(true);
	}

}
