
package com.marketour.business;

import java.io.Serializable;


/**
 * @author Andres
 * @version 1.0
 * @created 13-abr.-2015 9:52:34 a. m.
 */
public class Departamento implements Serializable {

	private String nombre;

	public Departamento(){

	}

	public void finalize() throws Throwable {

	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public static Departamento ConvertToBDepartamento(com.marketour.domain.Departamento departamento)
    {
		Departamento dep=new Departamento();
		dep.setNombre(departamento.getDescripcion());
		return dep;
    	
    }
	public static com.marketour.domain.Departamento ConvertToDBDepartamento(Departamento departamento)
    {
		com.marketour.domain.Departamento dep=new com.marketour.domain.Departamento();
		dep.setDescripcion(departamento.getNombre());
		
		return dep;
    	
    }

}
