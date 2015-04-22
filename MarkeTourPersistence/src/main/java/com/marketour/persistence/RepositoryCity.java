package com.marketour.persistence;

import java.util.List;

import com.marketour.domain.Ciudad;

public class RepositoryCity extends Repository<Ciudad> {

	public RepositoryCity() {
		super(Ciudad.class);
	}

	public List<Ciudad> FindByDepartment(int id) {
		return this.FindByColumn("departamento = " + id);
	}
}
