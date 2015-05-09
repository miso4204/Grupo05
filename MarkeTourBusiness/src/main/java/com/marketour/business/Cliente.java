
package com.marketour.business;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Andres
 * @version 1.0
 * @created 13-abr.-2015 9:52:32 a. m.
 */
@Entity
@Table(name="Cliente"
    ,catalog="grupocre_marketour"
)
public class Cliente  extends Usuario implements java.io.Serializable {

	private CarritoCompra m_CarritoCompra;
	private MedioPago m_MedioPago;
	private Compra m_Compra;
    private int id;
    private String descripcion;

	public Cliente(){

	}

	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public CarritoCompra getCarritoCompra(){
		return m_CarritoCompra;
	}

	public Compra getCompra(){
		return m_Compra;
	}

	public MedioPago getMedioPago(){
		return m_MedioPago;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setCarritoCompra(CarritoCompra newVal){
		m_CarritoCompra = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setCompra(Compra newVal){
		m_Compra = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setMedioPago(MedioPago newVal){
		m_MedioPago = newVal;
	}

	
	public static Cliente ConvertToBCliente(com.marketour.domain.Cliente cliente)
    {
		
		Cliente bCliente=new Cliente();
		bCliente.setDescripcion(cliente.getDescripcion());
		bCliente.setId(cliente.getId());
		
		bCliente.setCelular(cliente.getUsuario().getCelular());
		bCliente.setCorreo(cliente.getUsuario().getCorreo());
		bCliente.setDireccion(cliente.getUsuario().getCorreo());
		bCliente.setEstado(cliente.getUsuario().getEstado());
		bCliente.setLogin(cliente.getUsuario().getLogin());
		bCliente.setNombre(cliente.getUsuario().getNombre());
		bCliente.setTelefono(cliente.getUsuario().getTelefono());
		return bCliente;
    	
    }
	public static com.marketour.domain.Cliente ConvertToDBCliente(Cliente cliente)
    {
		com.marketour.domain.Cliente dbcliente=new com.marketour.domain.Cliente();
		
		dbcliente.setDescripcion(cliente.getDescripcion());
		dbcliente.setId(cliente.getId());
		return dbcliente;
    	
    }
	

}
