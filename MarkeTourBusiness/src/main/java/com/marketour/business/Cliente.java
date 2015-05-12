package com.marketour.business;

public class Cliente extends Usuario implements java.io.Serializable {

	private String descripcion;
	private int id;
	private CarritoCompra m_CarritoCompra;
	private Compra m_Compra;
	private MedioPago m_MedioPago;

	public static Cliente ConvertToBCliente(com.marketour.domain.Cliente domain) {
		Cliente bCliente = new Cliente();
		if (domain != null) {
			bCliente.setId(domain.getId());
			bCliente.setDescripcion(domain.getDescripcion());
		}
		return bCliente;
	}

	public static com.marketour.domain.Cliente ConvertToDBCliente(
			Cliente cliente) {
		com.marketour.domain.Cliente dbcliente = new com.marketour.domain.Cliente();

		dbcliente.setDescripcion(cliente.getDescripcion());
		dbcliente.setId(cliente.getId());
		return dbcliente;
	}

	public CarritoCompra getCarritoCompra() {
		return m_CarritoCompra;
	}

	public Compra getCompra() {
		return m_Compra;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public Integer getId() {
		return id;
	}

	public MedioPago getMedioPago() {
		return m_MedioPago;
	}

	public void setCarritoCompra(CarritoCompra newVal) {
		m_CarritoCompra = newVal;
	}

	public void setCompra(Compra newVal) {
		m_Compra = newVal;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setMedioPago(MedioPago newVal) {
		m_MedioPago = newVal;
	}
}
