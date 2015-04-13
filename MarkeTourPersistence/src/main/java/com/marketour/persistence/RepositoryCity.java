package com.marketour.persistence;

import java.util.List;

import javax.persistence.Persistence;

import com.marketour.domain.Ciudad;
import com.marketour.domain.Departamento;
import com.marketour.domain.Paquete;
import com.marketour.domain.PaqueteProducto;
import com.marketour.domain.Producto;

public class RepositoryCity extends Repository<Ciudad> {

	public RepositoryCity() {
		super(Ciudad.class);
	}

	public List<Ciudad> FindByDepartment(int id) {
		return this.FindByColumn("departamento = " + id);
	}
}
