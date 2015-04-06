package com.marketour.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.marketour.domain.Moneda;
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
		Repository<Moneda> rep = new Repository<Moneda>(Moneda.class);
		assertTrue(rep.FindAll().size() > 0);
	}

	public void testFindByColumn() {
		Repository<Moneda> rep = new Repository<Moneda>(Moneda.class);
		assertTrue(rep.FindByColumn("descripcion = 'TEST'").size() > 0);
	}

	public void testFindById() {
		Repository<Moneda> rep = new Repository<Moneda>(Moneda.class);
		assertTrue(rep.FindById(2).getId() > 0);
	}

	public void testPersist() {
		Repository<Moneda> rep = new Repository<Moneda>(Moneda.class);
		Moneda moneda = new Moneda();
		moneda.setDescripcion("TEST");
		assertTrue(rep.Persist(moneda).getId() > 0);
	}

	public void testDelete() {
		Repository<Moneda> rep = new Repository<Moneda>(Moneda.class);
		rep.Delete(1);
		assertTrue(true);
	}

}
