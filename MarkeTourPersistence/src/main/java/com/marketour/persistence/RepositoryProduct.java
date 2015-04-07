package com.marketour.persistence;

import com.marketour.domain.*;

public class RepositoryProduct extends Repository<Producto> {

	public RepositoryProduct() {
		super(Producto.class);
	}
}
