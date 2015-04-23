
package com.marketour.business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.marketour.domain.Producto;

/**
 * @author Andres
 * @version 1.0
 * @created 13-abr.-2015 9:52:38 a. m.
 */
public class Paquete implements Serializable {

	private int activo;
	private String descripcion;
	private String nombre;
	private List<Producto> lstProducto;

	public Paquete(){

	}

	public int getActivo() {
		return activo;
	}

	public void setActivo(int activo) {
		this.activo = activo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Producto> getlstProducto() {
		return lstProducto;
	}

	public void setlstProducto(List<Producto>  lstProducto) {
		this.lstProducto = lstProducto;
	}

	public void finalize() throws Throwable {
		super.finalize();
	}
	public static Paquete ConvertToBProduct(com.marketour.domain.Paquete paquete)
    {
    	Paquete pac=new Paquete();
		pac.setActivo(paquete.getEstado());
		pac.setDescripcion(paquete.getDescripcion());
		pac.setNombre(paquete.getNombre());
		pac.lstProducto=new ArrayList<Producto>();
    	return pac;
    }
	public static com.marketour.domain.Paquete ConvertToDBProduct(Paquete paquete)
    {
		com.marketour.domain.Paquete pac=new com.marketour.domain.Paquete();
		pac.setEstado(paquete.getActivo());
		pac.setDescripcion(paquete.getDescripcion());
		pac.setNombre(paquete.getNombre());
    	return pac;
    }
	
}
